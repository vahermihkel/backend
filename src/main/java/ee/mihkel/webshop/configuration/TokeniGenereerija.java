//package ee.mihkel.webshop.configuration;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.apache.commons.lang3.time.DateUtils;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//@Component //JWT --- Json Web Token
//public class TokeniGenereerija {
//
//    public String createAuthToken() {
//        Date expirationDate = DateUtils.addHours(new Date(),5);
//
////        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//        String token = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS512, "Minu-unikaalne-niemtus")
//                .setIssuer("Webshop")
//                .setExpiration(expirationDate)
//                .compact();
//        return token;
//    }
//}
