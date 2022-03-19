package ca.unb.usermanagement.repository;

import ca.unb.usermanagement.model.EUserRole;
import ca.unb.usermanagement.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(EUserRole name);
}
