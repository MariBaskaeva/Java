import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO.*;
import java.awt.image.*;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;


public class FractalExplorer
{
    private int display_size;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    
    private int rows_remaining;
    
    private JComboBox my_box;
    private JButton reset_button;
    private JButton save_button; 
    
    public FractalExplorer(int size)
    {
        display_size = size;       
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        display = new JImageDisplay(display_size, display_size);
        fractal.getInitialRange(range);
    }
    
    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        
        JFrame my_frame = new JFrame("Fractal");
        my_frame.add(display, BorderLayout.CENTER);
        
        reset_button = new JButton("Reset");
        save_button = new JButton("Save");
        
        ButtonHandler handler = new ButtonHandler();
        
        reset_button.addActionListener(handler);
        save_button.addActionListener(handler);
        
        JPanel my_panel_bottom = new JPanel();
        
        my_panel_bottom.add(reset_button);
        my_panel_bottom.add(save_button);
        
        my_frame.add(my_panel_bottom, BorderLayout.SOUTH);
        
        my_box = new JComboBox();
        JLabel my_label = new JLabel("Fractal:");
        JPanel my_panel_top = new JPanel();
        
        Mandelbrot md = new Mandelbrot();
        Tricorn tr = new Tricorn();
        BurningShip bs = new BurningShip();
        
        my_box.addItem(md);
        my_box.addItem(tr);
        my_box.addItem(bs);
        
        my_box.addActionListener(handler);
        
        my_panel_top.add(my_label);
        my_panel_top.add(my_box);
        
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
        enableUI(false);//выключаю GUI
        //кол-во строк равно размеру дисплея
        rows_remaining = display_size;
        //построчно просчитываю и отрисовываю
        for (int x = 0; x < display_size; x++){
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }
    }
    //включаю или выключаю работу кнопок
    private void enableUI(boolean val)
    {
        my_box.setEnabled(val);
        reset_button.setEnabled(val);
        save_button.setEnabled(val); 
    }
    
   private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();

            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
                
            } else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            } else if (command.equals("Save")) {
                JFileChooser chooser = new JFileChooser();

                FileFilter extensionFilter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(extensionFilter);

                chooser.setAcceptAllFileFilterUsed(false);

                int userSelection = chooser.showSaveDialog(display);
  
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
            if (rows_remaining != 0) {
                return;
            }
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, display_size, x);
            
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, display_size, y);
            
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            drawFractal();
        }
    }
    //вспомогательный класс
    public class FractalWorker extends SwingWorker<Object, Object>
    {
        int y;
        int[] rgbValues;
        //передаю количество строк
        public FractalWorker(int row)
        {
            y = row;
        }
        //то же, что и до этого было в методе DrawFractal
        protected Object doInBackground()
        {
            rgbValues = new int[display_size];
            for(int i = 0; i < display_size; i++){
                double xCoord = fractal.getCoord(range.x, range.x + range.width, display_size, i);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, display_size, y);

                int j = fractal.numIterations(xCoord, yCoord);

                if(j == -1){
                    rgbValues[i] = 0;
                }else{
                    float hue = 0.7f + (float) j / 700f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    rgbValues[i] = rgbColor;
                }
            }
            return null;
        }
        //построчно окрашиваем пиксели
        protected void done()
        {
            for(int i = 0; i < rgbValues.length; i++){
                display.drawPixel(i, y, rgbValues[i]);
            }
            display.repaint(0, 0, y, display_size, 1);
            
            rows_remaining--;
            if (rows_remaining == 0) {
                //включаю GUI
                enableUI(true);
            }
        }
    }
    
    public static void main(String[] args)
    {
        FractalExplorer fe = new FractalExplorer(800);
        fe.createAndShowGUI();
        fe.drawFractal();
    }
}