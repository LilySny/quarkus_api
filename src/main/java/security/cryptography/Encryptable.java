package security.cryptography;

public interface Encryptable {
    public String hashPassword(final char[] password, byte[] salt);
}
