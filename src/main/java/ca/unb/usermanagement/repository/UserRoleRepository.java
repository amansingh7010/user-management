package ca.unb.usermanagement.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.unb.usermanagement.model.EUserRole;
import ca.unb.usermanagement.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	Optional<UserRole> findByName(EUserRole name);
}
