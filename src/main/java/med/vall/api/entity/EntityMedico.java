package med.vall.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.vall.api.DTO.MedicosDTO;
import med.vall.api.enuns.EnumEspecialidades;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "medico")
@EqualsAndHashCode(of = "id")
public class EntityMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String rm;

    @Enumerated(EnumType.STRING)
    private EnumEspecialidades enumEspecialidades;
    @Embedded
    private EntityEndereco entityEndereco;

    public EntityMedico(MedicosDTO medicosDTO){
        this.nome = medicosDTO.nome();
        this.email = medicosDTO.email();
        this.rm = medicosDTO.rm();
        this.enumEspecialidades = medicosDTO.enumEspecialidades();
        this.entityEndereco = new EntityEndereco(medicosDTO.endereco());
    }
}
