package med.vall.api.DTO;

import med.vall.api.entity.EntityEndereco;
import med.vall.api.entity.EntityMedico;
import med.vall.api.enuns.EnumEspecialidades;

public record DadosDetalhamentosDTO(Long id, String nome, String email, String crm, String telefone, EnumEspecialidades especialidade, EntityEndereco endereco) {

    public DadosDetalhamentosDTO(EntityMedico entityMedico){
        this(entityMedico.getId(), entityMedico.getNome(), entityMedico.getEmail(), entityMedico.getRm(), entityMedico.getTelefone(), entityMedico.getEspecialidade(), entityMedico.getEntityEndereco());
    }
}
