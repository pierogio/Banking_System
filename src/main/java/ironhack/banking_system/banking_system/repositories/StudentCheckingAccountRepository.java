package ironhack.banking_system.banking_system.repositories;

import ironhack.banking_system.banking_system.models.accounts.StudentCheckingAccount;
import ironhack.banking_system.banking_system.services.StudentCheckingAccountService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingAccountRepository extends JpaRepository<StudentCheckingAccount, Long> {

}
