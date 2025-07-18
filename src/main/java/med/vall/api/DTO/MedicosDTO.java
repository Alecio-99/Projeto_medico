package med.vall.api.DTO;

import med.vall.api.enuns.EnumEspecialidades;

public record MedicosDTO(String nome, String email, String rm, EnumEspecialidades enumEspecialidades, DadosEndereco endereco
) {
}
