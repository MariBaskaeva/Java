import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

class JImageDisplay extends JComponent
{
    //переменная для изображения
    private BufferedImage image;
    
    public JImageDisplay(int width, int height)
    {
        //инициализируем изображение
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //создаем экземпляр класса Dimension
        Dimension imageDimension = new Dimension(width, height);
        //вызываем метод родительского класса
        super.setPreferredSize(imageDimension);
    }
    
    @Override
    //переопределяем метод родительского класса
    public void paintComponent(Graphics g)
    {
        //вызываем метод родительского класса
        super.paintComponent(g);
        //рисуем изображение
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }
    //метод все пиксели изображения делает черными
    public void clearImage()
    {
        int[] arr = new int[getWidth() * getHeight()];
        image.setRGB(0, 0, getWidth(), getHeight(), arr, 0, 1);
    }
    //закрасить конкретный пиксель цветом
    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x, y, rgbColor);
    }
}