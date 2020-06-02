package auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.enterprise.context.ApplicationScoped;
import java.security.KeyPair;
import java.util.Date;
import java.util.List;


@ApplicationScoped
public class JwtUtils {

    private final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    final long seconds = 3600;


    public String generateAccessToken(List<String> roles){
        return Jwts.builder()
                 .claim("groups", roles)
                .setIssuedAt(new Date())
                .setSubject("Authentication")
                .setExpiration(Date.from(new Date().toInstant().plusSeconds(seconds)))
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public void readAccessToken(String jws){
        Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic()) // <---- publicKey, not privateKey
                .build()
                .parseClaimsJws(jws);
    }


}
