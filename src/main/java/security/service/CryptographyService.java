package security.service;

public interface CryptographyService {
     void encrypt(char[] databasePassword, char[] password, byte[] salt);
}
