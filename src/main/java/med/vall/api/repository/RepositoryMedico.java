package med.vall.api.repository;

import med.vall.api.entity.EntityMedico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMedico extends JpaRepository<EntityMedico, Long> {
}
