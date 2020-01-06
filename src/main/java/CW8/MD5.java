package CW8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 extends DigestFunction{

    public MD5() throws NoSuchAlgorithmException {
        super();
        this.setDigestType(MessageDigest.getInstance("MD5"));
    }


    @Override
    public byte[] generateShortcut(String msg) {
        return new byte[0];
    }
}
