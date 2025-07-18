package med.vall.api.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vall.api.enuns.EnumEspecialidades;

public record MedicosDTO(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = ("\\d{4,6}"))
        String rm,
        @NotNull
        EnumEspecialidades enumEspecialidades,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
