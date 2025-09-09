package com.showhive.auth.api.dto;

public record LoginRequest(

        String email,
        String password

) {
}
