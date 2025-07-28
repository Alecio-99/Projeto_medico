package med.vall.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.vall.api.entity.EntityUsuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(EntityUsuario entityUsuario){
        try {
            var algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                    .withIssuer("Api Voll.med")
                    .withSubject(entityUsuario.getLogin())
                    .withClaim("id", entityUsuario.getId())
                   .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String getSubject(String tokenJWt){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Api Voll.med")
                    .build()
                    .verify(tokenJWt)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT expirado ou invalido!" + tokenJWt);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
