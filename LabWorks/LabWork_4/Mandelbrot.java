import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    //константа максимального числа итераций
    public static final int MAX_ITERATIONS = 2000;
    //инициализируем начальные значения
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }
    //возвращает кол-во итераций
    public int numIterations(double x, double y)
    {
        int i = 0;
        
        double zRe = 0;
        double zIm = 0;
        while(i < MAX_ITERATIONS && zRe * zRe + zIm * zIm < 4){
            double nextZRe = zRe*zRe - zIm * zIm + x;
            double nextZIm = 2 * zRe * zIm + y;
            
            zRe = nextZRe;
            zIm = nextZIm;
            i++;
        }
        
        if(i == MAX_ITERATIONS)
        {
            return -1;
        }
        return i;
    }
}