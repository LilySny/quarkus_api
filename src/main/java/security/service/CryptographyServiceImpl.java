package security.service;

import security.cryptography.Encryptable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CryptographyServiceImpl implements CryptographyService{

    public CryptographyServiceImpl(Encryptable encryptable){
       this.encoder = encryptable;
    }

    @Inject
    Encryptable encoder;


    @Override
    public void encrypt(final char[] databasePassword, final char[] password, byte[] salt)
    {
        encoder.hashPassword(databasePassword, salt);
        encoder.hashPassword(password, salt);
    }
}




/*
// dao = gato
// password = gato

aghz + salt
aghz + salt


 */
