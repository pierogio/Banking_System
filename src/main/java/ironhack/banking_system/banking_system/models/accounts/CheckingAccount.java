package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CheckingAccount  extends Account {
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);

    private BigDecimal monthlyMaintenance;

    private LocalDate lastInterestDay;

    public CheckingAccount() {
    }

    public CheckingAccount(BigDecimal minimumBalance, BigDecimal monthlyMaintenance, LocalDate lastInterestDay) {
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenance = monthlyMaintenance;
        this.lastInterestDay = lastInterestDay;
    }

    public CheckingAccount(Money balance, AccountHolder primaryOwner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus, BigDecimal minimumBalance, BigDecimal monthlyMaintenance, LocalDate lastInterestDay) {
        super(balance, primaryOwner, penaltyFee, creationDate, accountStatus);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenance = monthlyMaintenance;
        this.lastInterestDay = lastInterestDay;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenance() {
        return monthlyMaintenance;
    }

    public void setMonthlyMaintenance(BigDecimal monthlyMaintenance) {
        this.monthlyMaintenance = monthlyMaintenance;
    }

    public LocalDate getLastInterestDay() {
        return lastInterestDay;
    }

    public void setLastInterestDay(LocalDate lastInterestDay) {
        this.lastInterestDay = lastInterestDay;
    }

    public CheckingAccount(AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal monthlyMaintenance, LocalDate lastInterestDay) {
        super(secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenance = monthlyMaintenance;
        this.lastInterestDay = lastInterestDay;
    }

    @Override
    public void setBalance(Money balance) {
        if (minimumBalance == null) {
            minimumBalance = BigDecimal.valueOf(1000);
        }
        super.setBalance(balance);
        if (minimumBalance.compareTo(balance.getAmount()) < 0)
            super.setBalance(new Money(super.getBalance().decreaseAmount(super.getPenaltyFee())));

    }

    public void accountMonthlyMaintenance() {
        if (Period.between(lastInterestDay, LocalDate.now()).getMonths() >= 1) {
            setBalance(new Money(getBalance().decreaseAmount(monthlyMaintenance)));
            lastInterestDay = LocalDate.now();
        }
    }
}
