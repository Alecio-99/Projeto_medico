package med.vall.api.entity;

import jakarta.persistence.*;
import lombok.*;
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
    private String telefone;
    private String rm;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    private EnumEspecialidades enumEspecialidades;
    @Embedded
    private EntityEndereco entityEndereco;

    public EntityMedico(MedicosDTO medicosDTO){
        this.nome = medicosDTO.nome();
        this.email = medicosDTO.email();
        this.telefone = medicosDTO.telefone();
        this.rm = medicosDTO.rm();
        this.enumEspecialidades = medicosDTO.enumEspecialidades();
        this.entityEndereco = new EntityEndereco(medicosDTO.endereco());
    }
}
