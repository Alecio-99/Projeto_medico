package med.vall.api.DTO;

import med.vall.api.entity.EntityMedico;
import med.vall.api.enuns.EnumEspecialidades;

public record MedicosListaDTO(
        Long id,
        String nome,
        String email,
        String rm,
        EnumEspecialidades enumEspecialidades) {

    public MedicosListaDTO(EntityMedico entityMedico){
        this(entityMedico.getId(), entityMedico.getNome(), entityMedico.getEmail(), entityMedico.getRm(), entityMedico.getEnumEspecialidades());
    }
}
