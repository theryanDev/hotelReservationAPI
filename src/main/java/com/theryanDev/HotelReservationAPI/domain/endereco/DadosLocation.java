package com.theryanDev.HotelReservationAPI.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosLocation(
        @NotBlank(message = "Logradouro is required")
        String logradouro,
        @NotNull(message = "number cannot be null")
        Integer numero,
        String complemento,
        @NotBlank(message = "Bairro is required")
        String bairro,
        @NotBlank(message = "Cidade is required")
        String cidade,
        @NotBlank(message = "UF is required")
        String uf,
        @NotBlank(message = "CEP is required")
        @Pattern(regexp = "\\d{8}")
        String cep) {
}