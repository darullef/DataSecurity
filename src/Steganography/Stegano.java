package Steganography;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Stegano {

    private String imageName;
    private BufferedImage imgIN;
    private BufferedImage imgOUT;
    private String message;
    private int[][] XY;

    private Random rand = new Random();

    public Stegano(String imageName, String message) throws IOException
    {
        this.imageName = imageName;
        this.message =  message;
        this.imgIN = readImage(imageName);
        this.imgOUT = readImage(imageName);
        this.XY = generateXYCoords();
    }

    private BufferedImage readImage(String imageName) throws IOException
    {
        return ImageIO.read(new File("images\\" + this.imageName));
    }

    private String getMessageBinaryForm()
    {
        char[] lettersFromMessage = this.message.toCharArray();
        StringBuilder lettersInBinary = new StringBuilder();
        for(int i = 0; i < this.message.length(); i++)
        {
            lettersInBinary.append("0").append(Integer.toBinaryString(lettersFromMessage[i])).append("0");
        }
        return lettersInBinary.toString();
    }

    private int randomPixel()
    {
        int pixel = rand.nextInt(this.imgOUT.getWidth()) + 1;
        if(pixel > this.imgOUT.getWidth() && pixel > this.imgOUT.getHeight())
        {
            pixel = rand.nextInt();
        }
        return pixel;
    }

    private int[][] generateXYCoords()
    {
        int[][] xy = new int [this.message.length() * 4][2];
        for(int i = 0; i < this.message.length() * 4; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                xy[i][j] = randomPixel();
            }
        }
        return xy;
    }

    private int[] getPixelValues(int x, int y)
    {
        int[] rgbValues = new int[3];
        int color = this.imgOUT.getRGB(x, y);
        rgbValues[0]   = (color >>> 16) & 0xFF;
        rgbValues[1] = (color >>>  8) & 0xFF;
        rgbValues[2]  = (color) & 0xFF;
        return rgbValues;
    }

    private char[] checkBinaryLength(char[] arr)
    {
        StringBuilder num = new StringBuilder((String.valueOf(arr)));
        int l = num.length();
        if(l < 8)
        {
            while(num.length() < 8)
            {
                num.append("0");
            }
            return num.toString().toCharArray();
        }
        else return arr;
    }

    private Color changePixelValues(int[] rgbValues, String messageInBinaryForm, int counter)
    {
        char[] messageInBinaryFormCharArr = messageInBinaryForm.toCharArray();

        int red = rgbValues[0];
        int green = rgbValues[1];
        int blue = rgbValues[2];

        char[] newRed = checkBinaryLength(Integer.toBinaryString(red).toCharArray());
        char[] newGreen = checkBinaryLength(Integer.toBinaryString(green).toCharArray());
        char[] newBlue = checkBinaryLength(Integer.toBinaryString(blue).toCharArray());

        newRed[7] = messageInBinaryFormCharArr[counter - 2];
        newGreen[7] = messageInBinaryFormCharArr[counter - 1];
        newBlue[7] = messageInBinaryFormCharArr[counter];

        return new Color(Integer.parseInt(String.valueOf(newRed), 2), Integer.parseInt(String.valueOf(newGreen), 2), Integer.parseInt(String.valueOf(newBlue), 2));
    }

    public void print()
    {
        System.out.println(getMessageBinaryForm() + ", length: " + getMessageBinaryForm().length());
        System.out.println("Coords of changed pixels: " + Arrays.deepToString(this.XY));
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


    public void encryprion()
    {
        String message = getMessageBinaryForm();
        for(int x = 2; x < this.message.length() * 4; x++)
        {
            Color color = changePixelValues(getPixelValues(this.XY[x][0], this.XY[x][1]), message, x);
            imgOUT.setRGB(this.XY[x][0], this.XY[x][1], color.getRGB());
        }
        printImg();
    }
}
