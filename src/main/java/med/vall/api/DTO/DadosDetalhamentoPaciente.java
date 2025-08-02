package med.vall.api.DTO;

import med.vall.api.entity.EntityEndereco;
import med.vall.api.entity.EntityPaciente;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String cpf, String telefone, EntityEndereco endereco) {

    public DadosDetalhamentoPaciente(EntityPaciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
