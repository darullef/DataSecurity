package Steganography;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Stegano {

    private String imageName;
    private BufferedImage imgIN;
    private BufferedImage imgOUT;
    private String message;
    private int[][] XY;

    private Random rand = new Random();

    public Stegano(String imageName, String message) throws IOException {
        this.imageName = imageName;
        this.message = message;
        this.imgIN = readImage(imageName);
        this.imgOUT = readImage(imageName);
        this.XY = generateXYCoords();
    }

    public void encryprion() throws FileNotFoundException {
        String message = getMessageBinaryForm();
        int counter = 2;
        for (int x = 0; x < this.message.length() * 3; x++) {
            Color color = changePixelValues(getPixelValues(this.XY[x][0], this.XY[x][1]), message, counter);
            imgOUT.setRGB(this.XY[x][0], this.XY[x][1], color.getRGB());
            counter+=3;
        }
        printImg();
        createXYTxt();
    }

    private BufferedImage readImage(String imageName) throws IOException {
        return ImageIO.read(new File("images/" + this.imageName));
    }

    private String getMessageBinaryForm() {
        char[] lettersFromMessage = this.message.toCharArray();
        StringBuilder lettersInBinary = new StringBuilder();
        for (int i = 0; i < this.message.length(); i++) {
            lettersInBinary.append("0").append(Integer.toBinaryString(lettersFromMessage[i])).append("0");
        }
        return lettersInBinary.toString();
    }

    private int randomPixel() {
        int pixel = rand.nextInt(this.imgOUT.getWidth()) + 1;
        if (pixel > this.imgOUT.getWidth() && pixel > this.imgOUT.getHeight()) {
            pixel = rand.nextInt();
        }
        return pixel;
    }

    private int[][] generateXYCoords() {
        int[][] xy = new int[this.message.length() * 3][2];
        for (int i = 0; i < this.message.length() * 3; i++) {
            for (int j = 0; j < 2; j++) {
                xy[i][j] = randomPixel();
            }
        }
        return xy;
    }

    private int[] getPixelValues(int x, int y) {
        int[] rgbValues = new int[3];
        int color = this.imgOUT.getRGB(x, y);
        rgbValues[0] = (color >>> 16) & 0xFF;
        rgbValues[1] = (color >>> 8) & 0xFF;
        rgbValues[2] = (color) & 0xFF;
        return rgbValues;
    }

    public char[] fillWithZero(String str){
        String temp = "";
        if(str.length() != 8)
        {
            for(int i = 0; i < 8 - str.length(); i++)
            {
                temp += 0;
            }
            temp += str;
            return temp.toCharArray();
        }
        else return str.toCharArray();
    }

    private Color changePixelValues(int[] rgbValues, String messageInBinaryForm, int counter) {
        char[] messageInBinaryFormCharArr = messageInBinaryForm.toCharArray();

        int red = rgbValues[0];
        int green = rgbValues[1];
        int blue = rgbValues[2];

        char[] newRed = fillWithZero(Integer.toBinaryString(red));
        char[] newGreen = fillWithZero(Integer.toBinaryString(green));
        char[] newBlue = fillWithZero(Integer.toBinaryString(blue));

        newRed[newRed.length - 1] = messageInBinaryFormCharArr[counter - 2];
        newGreen[newGreen.length - 1] = messageInBinaryFormCharArr[counter - 1];
        newBlue[newBlue.length - 1] = messageInBinaryFormCharArr[counter];

        return new Color(Integer.parseInt(String.valueOf(newRed), 2), Integer.parseInt(String.valueOf(newGreen), 2), Integer.parseInt(String.valueOf(newBlue), 2));
    }

    public void print() {
        System.out.println(getMessageBinaryForm() + ", length: " + getMessageBinaryForm().length());
        System.out.println("Coords of changed pixels: " + Arrays.deepToString(this.XY));
    }

    private void printImg() {
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

    private void createXYTxt() throws FileNotFoundException {
        PrintStream out = new PrintStream("txt\\XY.txt");
        for (int x = 0; x < this.message.length() * 3; x++) {
            for (int y = 0; y < 2; y++) {
                out.print(String.valueOf(this.XY[x][y]) + " ");
            }
        }
        System.out.println("Text file with coords of changed pixels saved!");
    }

    public void decryption(String path) throws FileNotFoundException {
        int[][] xy = createXTMatrix(path);
        String binaryMessage = "";
        for (int x = 0; x < getXSize(path); x++) {
            binaryMessage += (readBinaryValuesFromPixel(getPixelValues(xy[x][0], xy[x][1])));
        }
        System.out.println(binaryMessage + ", length: " + binaryMessage.length());
    }

    private int[][] createXTMatrix(String path) throws FileNotFoundException {
        String fileContent = readTxt(path);
        String[] numbers = fileContent.split(" ");
        int[][] coords = new int[numbers.length / 2][2];
        int counter = 0;
        for (int x = 0; x < numbers.length / 2; x++) {
            for (int y = 0; y < 2; y++) {
                coords[x][y] = Integer.parseInt(numbers[counter]);
                counter++;
            }
        }
        return coords;
    }

    private int getXSize(String path) throws FileNotFoundException {
        String fileContent = readTxt(path);
        String[] numbers = fileContent.split(" ");
        return numbers.length / 2;
    }

    private String readTxt(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        return scanner.nextLine();
    }

    private String readBinaryValuesFromPixel(int[] rgbValues) {
        int red = rgbValues[0];
        int green = rgbValues[1];
        int blue = rgbValues[2];

        char[] newRed = Integer.toBinaryString(red).toCharArray();
        char[] newGreen = Integer.toBinaryString(green).toCharArray();
        char[] newBlue = Integer.toBinaryString(blue).toCharArray();

        char[] arr = new char[3];

        arr[0] = newRed[newRed.length - 1];
        arr[1] = newGreen[newGreen.length - 1];
        arr[2] = newBlue[newBlue.length - 1];

        return String.valueOf(arr);
    }
}
