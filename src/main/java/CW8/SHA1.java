package CW8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 extends DigestFunction {

    public SHA1() throws  NoSuchAlgorithmException {
        super();
        this.setDigestType(MessageDigest.getInstance("SHA-1"));
    }

    @Override
    public byte[] generateShortcut(String msg) {
        return new byte[0];
    }
}
