package spring_boot.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_boot.dto.AuthenticationRequest;
import spring_boot.dto.IntrospectRequest;
import spring_boot.exception.AddException;
import spring_boot.exception.ErrorCode;
import spring_boot.repository.UserRepository;
import spring_boot.response.AuthenticationResponse;
import spring_boot.response.IntrospectResponse;

import java.text.ParseException;
import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    protected static final String SIGNER_KEY = "z6ib0M3pi3lJhMbcOyV09s1Gl3YiDCb8E+JRzxEsoL9X3OggQ02EK+K1ZPf2ijYn\n";

    //introspect xac minh token
    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
            var token = request.getToken();
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            // xem token nay co het han hay chua
            Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            var verified = signedJWT.verify(verifier); // ham nay se tra true hoac fall
            // kiem tra token da het han hay chua, thoi gian het han phai sau thoi diem hien tai
            return IntrospectResponse.builder()
                    .valid(verified && expityTime.after(new Date()))
                    .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AddException(ErrorCode.USER_NOT_EXISTED));


        boolean authenticated =  passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated)
            throw new AddException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(request.getUserName());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }
    private String generateToken(String userName) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("userId", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can't create Token", e);
            throw new RuntimeException(e);
        }
    }


}
