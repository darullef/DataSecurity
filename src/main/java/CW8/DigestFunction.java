package CW8;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public abstract class DigestFunction {

    private MessageDigest digestType;

    public MessageDigest getDigestType()
    {
        return digestType;
    }

    public void setDigestType(MessageDigest md)
    {
        this.digestType = md;
    }

    public byte[] generateDigest(String str)
    {
        this.getDigestType().update(str.getBytes());
        return getDigestType().digest();
    }

    public void generateAndPrint(String str)
    {
        String msg = DatatypeConverter.printHexBinary(generateDigest(str)).toUpperCase();
        System.out.println(msg);
    }

    public void checkTime(String msg)
    {
        double start, stop, counter = 0, duration;
        generateAndPrint(msg);
        int n = 10000;

        for(int i = 0; i < n; i++)
        {
            start = System.currentTimeMillis();
            generateDigest(msg);
            stop = System.currentTimeMillis();
            duration = stop - start;
            counter += duration;
        }
        double average = counter/n;
        System.out.println("Average time of " + n + " executions: " + average);
    }

    public abstract byte[] generateShortcut(String msg);
}
