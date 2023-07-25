package io.github.pedromeerholz.stock.api.model.user.dto;

public class AuthorizationTokenDto extends UserResponseDto {
    private String authorizationToken;

    public AuthorizationTokenDto(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
