package med.vall.api.repository;

import jakarta.validation.constraints.NotNull;
import med.vall.api.entity.EntityPaciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPaciente extends JpaRepository<EntityPaciente, Long> {
    Page<EntityPaciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m.ativo
            from EntityMedico m
            where
            m.id = :id
            """)
    boolean findAtivoById(Long id);
}
