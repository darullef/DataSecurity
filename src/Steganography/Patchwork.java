package Steganography;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;


public class Patchwork {

    private String imageName;
    private BufferedImage imgIN;
    private BufferedImage imgOUT = null;
    private int i;

    public Patchwork(String imageName, int key) throws IOException
    {
        this.imageName = imageName;
        Random rand = new Random();
        this.i = rand.nextInt(key) + 1;
        this.imgIN = readImage(imageName);
        this.imgOUT = readImage(imageName);
    }

    private BufferedImage readImage(String imageName) throws IOException
    {
        return ImageIO.read(new File("images\\" + this.imageName));
    }

    private Color getCurrentColor(BufferedImage img, int x, int y)
    {
        int color = img.getRGB(x, y);
        int red   = (color >>> 16) & 0xFF;
        int green = (color >>>  8) & 0xFF;
        int blue  = (color) & 0xFF;
        return new Color(red, green, blue);
    }

    private float newColorValue(float colorValue, float brightnessValue)
    {
        if(colorValue + brightnessValue > 255)
        {
            return 255;
        }
        else return colorValue + brightnessValue;
    }

    private Color createNewColor(Color color, float brightnessPercentage)
    {
        float red = newColorValue(color.getRed(), brightnessPercentage);
        float green = newColorValue(color.getGreen(), brightnessPercentage);
        float blue = newColorValue(color.getBlue(), brightnessPercentage);
        return new Color((int)red, (int)green, (int)blue);
    }

    private void changeBrightness(BufferedImage img, float brightnessValue)
    {
        for(int x = this.i; x < img.getWidth(); x++)
        {
            for(int y = this.i; y < img.getHeight(); y++)
            {
                Color currentColor = getCurrentColor(img, x, y);
                Color newAiColor = createNewColor(currentColor, brightnessValue);
                img.setRGB(x, y, newAiColor.getRGB());
            }
        }
    }

    private void printImg()
    {
        ImageIcon icon1 = new ImageIcon(this.imgIN);
        ImageIcon icon2 = new ImageIcon(this.imgOUT);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(2 * imgIN.getWidth() + 50, imgIN.getHeight() + 50);
        frame.setTitle(this.imageName);
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setIcon(icon1);
        label2.setIcon(icon2);
        frame.add(label1);
        frame.add(label2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void d00pa()
    {
        changeBrightness(this.imgOUT, 50);
        printImg();
    }
}
