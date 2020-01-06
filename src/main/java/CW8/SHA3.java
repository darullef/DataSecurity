package CW8;

public class SHA3 extends DigestFunction {

    @Override
    public byte[] generateShortcut(String msg) {
        org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3 digestSHA3 = new org.bouncycastle.jcajce.provider.digest.SHA3.Digest512();
        byte[] digest = digestSHA3.digest(msg.getBytes());
        return digest;
    }
}
