package Services;

import Models.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenJJWT {
    private static String secretKey = "Asocijacije-53/2020";
    private static long expirationTimeInMillis = 86400000; // Vreme isteka tokena 1 dan

    public static String generateToken(Account account){
        String role;
        switch (account.getRole_id()){
            case 1:
                role = "admin";
                break;
            default:
                role = "user";
                break;
        }
        String jwt = Jwts.builder()
                .claim("id",account.getId())
                .claim("role",role)
                .claim("email",account.getEmail())
                .claim("username",account.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(SignatureAlgorithm.HS256, "AsocijacijeBogdanLukic53/2020AsocijacijeSigurnosniKod")
                .compact();
        return jwt;
    }
}
