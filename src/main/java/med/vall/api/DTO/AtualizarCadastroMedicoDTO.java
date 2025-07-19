package med.vall.api.DTO;

import jakarta.validation.constraints.NotNull;

public record AtualizarCadastroMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco dadosEndereco) {
}
