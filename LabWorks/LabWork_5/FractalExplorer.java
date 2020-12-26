import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO.*;
import java.awt.image.*;
import javax.swing.JOptionPane;


public class FractalExplorer
{
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    
    public FractalExplorer(int size)
    {
        displaySize = size;
        
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        display = new JImageDisplay(displaySize, displaySize);
        fractal.getInitialRange(range); 
    }
    
    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        
        JFrame my_frame = new JFrame("Fractal");
        my_frame.add(display, BorderLayout.CENTER);
        //добавила 2 кнопки
        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save");
        
        ButtonHandler handler = new ButtonHandler();
        //обработчик событий на 2 кнопки
        resetButton.addActionListener(handler);
        saveButton.addActionListener(handler);
        
        JPanel my_panel_bottom = new JPanel();
        //поместила 2 кнопки на панель
        my_panel_bottom.add(resetButton);
        my_panel_bottom.add(saveButton);
        //добавила все в окно
        my_frame.add(my_panel_bottom, BorderLayout.SOUTH);
        //создала выпадающий список
        JComboBox my_box = new JComboBox();
        JLabel my_label = new JLabel("Fractal:");
        JPanel my_panel_top = new JPanel();
        //создаю экземпляры фракталов
        Mandelbrot md = new Mandelbrot();
        Tricorn tr = new Tricorn();
        BurningShip bs = new BurningShip();
        //добавляю в выпадающий список
        my_box.addItem(md);
        my_box.addItem(tr);
        my_box.addItem(bs);
        //добавляю обработчик событий на список
        my_box.addActionListener(handler);
        //добавляю все на панель
        my_panel_top.add(my_label);
        my_panel_top.add(my_box);
        //панель добавляю в окно
        my_frame.add(my_panel_top, BorderLayout.NORTH);
        
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        
        my_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        my_frame.pack();
        my_frame.setVisible(true);
        my_frame.setResizable(false);
    }
    
    public void drawFractal()
    {
        for(int x = 0; x < displaySize; x++){
            for(int y = 0; y < displaySize; y++){
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
                
                int i = fractal.numIterations(xCoord, yCoord);
                
                if(i == -1){
                    display.drawPixel(x, y, 0);
                }else{
                    float hue = 0.7f + (float) i / 700f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }
    
   private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();
            //обрабтчик событий, если это выпадающий список
            if (e.getSource() instanceof JComboBox) {
                //получаю экземпляр класса JComboBox
                JComboBox mySource = (JComboBox) e.getSource();
                //получаю экземпляр фрактала, который был выбран
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal(); 
            }//если нажали на кнопку reset
             else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }//если нажали на кнопку save
             else if (command.equals("Save")) {
                JFileChooser chooser = new JFileChooser();
                //настраиваю фильтры
                FileFilter extensionFilter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(extensionFilter);

                chooser.setAcceptAllFileFilterUsed(false);

                int userSelection = chooser.showSaveDialog(display);
                //здесь происходит сохранение
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = chooser.getSelectedFile();
                    String file_name = file.toString();
                    
                    try {
                        BufferedImage displayImage = display.image;
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(display, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                   return; 
                }
            }
        }
    }
    
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
            
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
            
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            drawFractal();
        }
    }
    
    public static void main(String[] args)
    {
        FractalExplorer fe = new FractalExplorer(800);
        fe.createAndShowGUI();
        fe.drawFractal();
    }
}