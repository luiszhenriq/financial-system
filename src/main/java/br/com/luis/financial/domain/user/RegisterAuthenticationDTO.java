package br.com.luis.financial.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterAuthenticationDTO(
        @NotBlank(message = "Nome do usuário não pode ser vazio!")
        String username,
        @NotBlank(message = "Login não pode ser vazio!")
        @Email
        String login,
        @NotBlank(message = "Senha não pode ser vazia!")
        String password) {
}
