package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class StudentCheckingAccount extends Account {

    public StudentCheckingAccount() {
    }

    public StudentCheckingAccount(Money balance, AccountHolder primaryOwner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus) {
        super(balance, primaryOwner, penaltyFee, creationDate, accountStatus);
    }

    public StudentCheckingAccount(AccountHolder secondaryOwner) {
        super(secondaryOwner);
    }


}
