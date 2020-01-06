package CW8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA2 extends DigestFunction {

    public SHA2(String type) throws NoSuchAlgorithmException {
        super();
        this.setDigestType(MessageDigest.getInstance(type));
    }

    @Override
    public byte[] generateShortcut(String msg) {
        return new byte[0];
    }
}
