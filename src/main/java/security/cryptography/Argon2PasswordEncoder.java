package security.cryptography;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.Arrays;


public class Argon2PasswordEncoder {

    public String hashPassword(final char[] password, final byte[] salt){
        Argon2 argon2 = Argon2Factory.create();

        try {
            // Hash password
            String hash = argon2.hash(4, 1024 * 1024, 8, password) + Arrays.toString(salt);

            // Verify password
            if(argon2.verify(hash, password)) {
                return hash;
            } else {
                return "Hash didn't matched password!";
            }
        } finally {
            argon2.wipeArray(password);
        }
    }
}
