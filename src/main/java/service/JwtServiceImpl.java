package service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import dto.LoginResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;



// Criacao do TOKEN para login
@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(LoginResponseDTO dto) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        // exemplo para teste
        Set<String> roles = new HashSet<String>();
        roles.add("User");
        // Momento da criação do TOKEN configurado em application.properties
        return Jwt.issuer("unitins-jwt")
                .subject(dto.id().toString()) //Algo unico para que o usuario possa fazer login
                .groups(roles) //Um tipo de usuário, ADM, user etc
                .expiresAt(expiryDate) //Data em que o Token expira
                .sign();
    }

}
