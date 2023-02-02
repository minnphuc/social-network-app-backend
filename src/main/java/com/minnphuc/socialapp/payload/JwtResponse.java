package com.minnphuc.socialapp.payload;

public class JwtResponse {
    private String token;

    private Long id;
    private int expiresIn;

    public JwtResponse(String accessToken, Long id, int expiresIn) {
        this.token = accessToken;
        this.id = id;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
