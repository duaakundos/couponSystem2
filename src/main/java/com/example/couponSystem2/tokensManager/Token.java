package com.example.couponSystem2.tokensManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Token {
    private static int TOKEN_COUNTER = 1;
    private int sessionDurationInMinutes = 15; //30 minutes
    private String token = "token_" + (TOKEN_COUNTER++) + "_" + Math.random() * Math.random();
    private LocalDateTime startSessionTime = LocalDateTime.now();
    private LocalDateTime endSessionTime = startSessionTime.plusMinutes(sessionDurationInMinutes);
    private AuthDetails authDetails;

    public Token(AuthDetails authDetails) throws SQLException, InterruptedException {
        this.authDetails = authDetails;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getEndSessionTime() {
        return endSessionTime;
    }

    public AuthDetails getAuthDetails() {
        return authDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return token.equals(token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

}
