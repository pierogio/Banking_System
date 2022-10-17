package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Savings extends Account {

    private Money minimumBalance;

    private BigDecimal savingInterestRate;

    private LocalDate lastInterestDate;

    public Savings() {
    }

    public Savings(Money balance, String primaryOwner, String owner, double penaltyFee, LocalDate creationDate, AccountStatus accountStatus) {
        super(balance, primaryOwner, owner, penaltyFee, creationDate, accountStatus);
    }

    public Savings(Money minimumBalance, BigDecimal interestRate, LocalDate lastInterestDate) {
        this.minimumBalance = minimumBalance;
        this.savingInterestRate = interestRate;
        this.lastInterestDate = lastInterestDate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, String owner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus, Money minimumBalance, BigDecimal interestRate, LocalDate lastInterestDate) {
        super(balance, primaryOwner, owner, penaltyFee, creationDate, accountStatus);
        this.minimumBalance = minimumBalance;
        this.savingInterestRate = interestRate;
        this.lastInterestDate = lastInterestDate;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return savingInterestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.savingInterestRate = interestRate;
    }

    public LocalDate getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(LocalDate lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }

    public Savings(AccountHolder secondaryOwner, Money minimumBalance, BigDecimal savingInterestRate, LocalDate lastInterestDate) {
        super(secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.savingInterestRate = savingInterestRate;
        this.lastInterestDate = lastInterestDate;
    }
}
