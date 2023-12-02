package br.com.luis.financial.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "Campo não pode ser vazio!")
        @Email
        String login,
        @NotBlank(message = "Campo não pode ser vazio!")
        String password) {
}
