package med.vall.api.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vall.api.entity.EntityMedico;
import med.vall.api.enuns.EnumEspecialidades;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface RepositoryMedico extends JpaRepository<EntityMedico, Long> {

    Page<EntityMedico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select m from EntityMedico m
            where
            m.ativo = true
            and
            m.especialidade = :especialidades
            and
            m.id not in(
              select c.medico.id from EntityConsulta c
              where
              c.data = :data
            )
            order by rand()
            limit 1
            """)
    EntityMedico escolherMedicoAleatorio(EnumEspecialidades especialidades, @NotNull @Future LocalDateTime data);

    @Query("""
            select m.ativo
            from EntityMedico m
            where
            m.id = :id
            """)
Boolean findAtivoById(Long id);
}
