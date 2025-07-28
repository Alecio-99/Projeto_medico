package med.vall.api.repository;

import med.vall.api.entity.EntityUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsuario extends JpaRepository<EntityUsuario, Long> {

    UserDetails findByLogin(String login);
}
