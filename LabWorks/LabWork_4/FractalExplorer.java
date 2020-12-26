import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

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
        
        JButton resetButton = new JButton("Reset Display");
        //добавляем обработчик событий
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);
        my_frame.add(resetButton, BorderLayout.SOUTH);
        
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
                //перевод из экранных координат в координаты фракталов
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
                //получаю кол-во итераций
                int i = fractal.numIterations(xCoord, yCoord);
                //в этом случае окрашиваю в черный
                if(i == -1){
                    display.drawPixel(x, y, 0);
                }else{
                    float hue = 0.7f + (float) i / 700f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        //перерисовываем окно
        display.repaint();
    }
    
    private class ResetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fractal.getInitialRange(range);
            drawFractal();
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