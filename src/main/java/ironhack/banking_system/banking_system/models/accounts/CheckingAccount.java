package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CheckingAccount  extends Account {

    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));

    private BigDecimal monthlyMaintenance;

    private LocalDate lastInterestDay;

    public CheckingAccount() {
    }

    public CheckingAccount(Money minimumBalance, BigDecimal monthlyMaintenance, LocalDate lastInterestDay) {
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenance = monthlyMaintenance;
        this.lastInterestDay = lastInterestDay;
    }

    public CheckingAccount(Money balance, AccountHolder primaryOwner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus, Money minimumBalance, BigDecimal monthlyMaintenance, LocalDate lastInterestDay) {
        super(balance, primaryOwner, penaltyFee, creationDate, accountStatus);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenance = monthlyMaintenance;
        this.lastInterestDay = lastInterestDay;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
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

    public CheckingAccount(AccountHolder secondaryOwner, Money minimumBalance, BigDecimal monthlyMaintenance, LocalDate lastInterestDay) {
        super(secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenance = monthlyMaintenance;
        this.lastInterestDay = lastInterestDay;
    }

    @Override
    public void setBalance(Money balance) {
        if (minimumBalance == null) {
            minimumBalance = new Money(BigDecimal.valueOf(1000));
        }
        super.setBalance(balance);
        if (minimumBalance.getAmount().compareTo(balance.getAmount()) < 0)
            super.setBalance(new Money(super.getBalance().decreaseAmount(super.getPenaltyFee())));

    }

    public void accountMonthlyMaintenance() {
        if (Period.between(lastInterestDay, LocalDate.now()).getMonths() >= 1) {
            setBalance(new Money(getBalance().decreaseAmount(monthlyMaintenance)));
            lastInterestDay = LocalDate.now();
        }
    }
}
