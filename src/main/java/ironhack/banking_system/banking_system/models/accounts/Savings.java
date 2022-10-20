package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Savings extends Account {
    @DecimalMin(value = "100")
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));
    @DecimalMax(value = "0.5")
    private BigDecimal savingInterestRate = new BigDecimal(0.0025);

    private LocalDate lastInterestDate;

    public Savings() {
    }

    public Savings(Money balance, AccountHolder primaryOwner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus) {
        super(balance, primaryOwner, penaltyFee, creationDate, accountStatus);
    }

    public Savings(Money minimumBalance, BigDecimal interestRate, LocalDate lastInterestDate) {
        this.minimumBalance = minimumBalance;
        this.savingInterestRate = interestRate;
        this.lastInterestDate = lastInterestDate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus, Money minimumBalance, BigDecimal interestRate, LocalDate lastInterestDate) {
        super(balance, primaryOwner, penaltyFee, creationDate, accountStatus);
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

    @Override
    public void setBalance(Money balance) {
        if(minimumBalance == null){
          minimumBalance =  new Money(BigDecimal.valueOf(1000));
        }
        super.setBalance(balance);
        if(minimumBalance.getAmount().compareTo(balance.getAmount())>0)
            super.setBalance(new Money(super.getBalance().decreaseAmount(super.getPenaltyFee())));

    }
    public void checkInterestRate(){
        if(Period.between(lastInterestDate, LocalDate.now()).getYears() >= 1) {
            BigDecimal bigDecimal = getBalance().getAmount().multiply(savingInterestRate);
            setBalance(new Money(getBalance().increaseAmount(bigDecimal)));
            lastInterestDate = LocalDate.now();
        }

    }
}
