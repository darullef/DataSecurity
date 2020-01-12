package CW10;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class VisualCryptography {

    private BufferedImage greyScaleImage;
    private int sizeX;
    private int sizeY;
    private BufferedImage outputImage1;
    private BufferedImage outputImage2;
    private Random random = new Random();

    public VisualCryptography(String path) throws IOException
    {
        this.greyScaleImage = createBWImage(readImage(path));
        this.sizeX = this.greyScaleImage.getWidth();
        this.sizeY = this.greyScaleImage.getHeight();
        this.outputImage1 = new BufferedImage(this.sizeX *2, this.sizeY *2, BufferedImage.TYPE_INT_RGB);
        this.outputImage2 = new BufferedImage(this.sizeX *2, this.sizeY *2, BufferedImage.TYPE_INT_RGB);
    }

    private static BufferedImage readImage(String path) throws IOException {
        return ImageIO.read(new File("images/" + path));
    }

    public static BufferedImage createBWImage(BufferedImage image) throws IOException {
        int threshold = 25;
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        result.getGraphics().drawImage(image, 0, 0, null);
        WritableRaster raster = result.getRaster();
        int[] pixels = new int[image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
            raster.getPixels(0, y, image.getWidth(), 1, pixels);
            for (int i = 0; i < pixels.length; i++) {
                if (pixels[i] < threshold) pixels[i] = 0;
                else pixels[i] = 255;
            }
            raster.setPixels(0, y, image.getWidth(), 1, pixels);
        }
        ImageIO.write(result, "png", new File("images/bw.png"));
        return result;
    }

    public static void leftWhite(BufferedImage img, int x , int y) {
        int a = x*2, b=y*2, c=x*2+1, d=y*2+1;
        img.setRGB(a, b, Color.white.getRGB());
        img.setRGB(c, b, Color.white.getRGB());
        img.setRGB(a, d, Color.black.getRGB());
        img.setRGB(c, d, Color.black.getRGB());
    }

    public static void rightWhite(BufferedImage img, int x , int y) {
        int a = x*2, b=y*2, c=x*2+1, d=y*2+1;
        img.setRGB(a, b, Color.black.getRGB());
        img.setRGB(c, b, Color.black.getRGB());
        img.setRGB(a, d, Color.white.getRGB());
        img.setRGB(c, d, Color.white.getRGB());
    }

    public void createShares() throws IOException {
        for(int x = 0; x < this.sizeX; x++)
        {
            for(int y = 0; y < this.sizeY; y++)
            {
                int pixel = this.greyScaleImage.getRGB(x, y);
                int rand = random.nextInt(10);
                if(pixel == Color.black.getRGB())
                {
                    if(rand % 2 == 0)
                    {
                        leftWhite(this.outputImage1, x, y);
                        rightWhite(this.outputImage2, x, y);
                    }
                    else
                    {
                        rightWhite(this.outputImage1, x, y);
                        leftWhite(this.outputImage2, x, y);
                    }
                }
                if(pixel == Color.white.getRGB())
                {
                    if(rand % 2 == 0)
                    {
                        leftWhite(this.outputImage1, x, y);
                        leftWhite(this.outputImage2, x, y);
                    }
                    else
                    {
                        rightWhite(this.outputImage1, x, y);
                        rightWhite(this.outputImage2, x, y);
                    }
                }
            }
        }
        File output1 = new File("images/out1.png");
        ImageIO.write(this.outputImage1, "png", output1);
        File output2 = new File("images/out2.png");
        ImageIO.write(this.outputImage2, "png", output2);
    }

    public void mergeShares() throws IOException {
        BufferedImage o1 = readImage("out1.png");
        BufferedImage o2 = readImage("out2.png");
        int sizeX = o1.getWidth() / 2;
        int sizeY = o1.getHeight() / 2;
        BufferedImage result = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < sizeX; x++)
        {
           for(int y = 0; y < sizeY; y++)
           {
                int[] pixel1 = {o1.getRGB( x * 2, y * 2),
                                o1.getRGB(x * 2 + 1, y * 2),
                                o1.getRGB(x * 2, y * 2 + 1),
                                o1.getRGB(x * 2 + 1, y * 2 + 1)};

                int[] pixel2 = {o2.getRGB(x * 2, y * 2),
                                o2.getRGB(x * 2 + 1, y * 2),
                                o2.getRGB(x * 2, y * 2 + 1),
                                o2.getRGB(x * 2 + 1, y * 2 + 1)};

               if (Arrays.equals(pixel1, pixel2)) {
                   result.setRGB(x, y, Color.white.getRGB());
               } else {
                   result.setRGB(x, y, Color.black.getRGB());
               }
           }
        }
        for(int x = sizeX - 1; x < sizeX - 1; x++)
        {
            for(int y = sizeY - 1; y < sizeY + 1; y++)
            {
                int[] pixel1 = {o1.getRGB( x * 2, y * 2),
                        o1.getRGB(x * 2 + 1, y * 2),
                        o1.getRGB(x * 2, y * 2 + 1),
                        o1.getRGB(x * 2 + 1, y * 2 + 1)};

                int[] pixel2 = {o2.getRGB(x * 2, y * 2),
                        o2.getRGB(x * 2 + 1, y * 2),
                        o2.getRGB(x * 2, y * 2 + 1),
                        o2.getRGB(x * 2 + 1, y * 2 + 1)};

                if (Arrays.equals(pixel1, pixel2)) {
                    result.setRGB((x / 2), (y / 2), Color.white.getRGB());
                } else {
                    result.setRGB((x / 2), (y / 2), Color.black.getRGB());
                }
            }
        }
        ImageIO.write(result, "png", new File("images/result.png"));
    }
}
