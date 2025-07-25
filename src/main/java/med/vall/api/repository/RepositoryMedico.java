package med.vall.api.repository;

import med.vall.api.entity.EntityMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RepositoryMedico extends JpaRepository<EntityMedico, Long> {

    Page<EntityMedico> findAllByAtivoTrue(Pageable pageable);
}
