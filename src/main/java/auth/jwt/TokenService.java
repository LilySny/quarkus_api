package auth.jwt;

import groups.model.Group;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.jwt.Claims;
import user.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class TokenService {

    public String generateAccessToken(User user) {
        try {
            String privateKeyLocation = "/privateKey.pem";
            PrivateKey pk = readPrivateKey(privateKeyLocation);
            return generateTokenString(pk, privateKeyLocation, user.getUsername(), user.getGroups());
        } catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            throw new RuntimeException("Erro gerando o Access Token");
        }
    }

    public String generateTokenString(PrivateKey privateKey, String kid, String username, List<Group> groups) {

        try {
            JwtClaimsBuilder claims = Jwt.claims();
            long currentTimeInSecs = currentTimeInSecs();

            Set<String> claimGroups = new HashSet<>();
            groups.forEach((g) -> claimGroups.add(g.getName()));

            claims.issuedAt(currentTimeInSecs);
            claims.subject(username);
            claims.groups(claimGroups);
            claims.issuer("https://localhost:8080");
            claims.claim(Claims.auth_time.name(), currentTimeInSecs);
            claims.expiresAt(200000);

            return claims.jws().signatureKeyId(kid).sign(privateKey);
        } catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            throw new RuntimeException("Erro gerando a String do Token");
        }
    }

       public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
        try (InputStream contentIS = TokenService.class.getResourceAsStream(pemResName)) {
            byte[] tmp = new byte[4096];
            int length = contentIS.read(tmp);
            return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
        }
    }

    /**
     * Decode a PEM encoded private key string to an RSA PrivateKey
     *
     * @param pemEncoded - PEM string for private key
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }

    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    /**
     * @return the current time in seconds since epoch
     */
    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }

}