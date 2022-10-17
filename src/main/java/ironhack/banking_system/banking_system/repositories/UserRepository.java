package ironhack.banking_system.banking_system.repositories;

import ironhack.banking_system.banking_system.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
