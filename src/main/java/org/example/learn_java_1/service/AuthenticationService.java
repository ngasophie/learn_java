package org.example.learn_java_1.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.learn_java_1.entity.User;
import org.example.learn_java_1.exception.AppException;
import org.example.learn_java_1.exception.ErrorCode;
import org.example.learn_java_1.repository.UserRepository;
import org.example.learn_java_1.request.AuthenticationRequest;
import org.example.learn_java_1.response.AuthenticationResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    @NonFinal
    protected static final  String SIGNER_KEY = "8n7l9oPzQxFvYH6U9tK3q5L0vJd2W8xR3yZc1mN4hT0=";
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .authenticate(authenticated)
                .token(token)
                .build();
    }
    private String generateToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username) //
                .issuer("tnpixel.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("customClaim", "custom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject object = new JWSObject(header, payload);
        try {
            object.sign(new MACSigner(SIGNER_KEY));
            return object.serialize();
        } catch (JOSEException e) {
            log.error("can not generate access token" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
