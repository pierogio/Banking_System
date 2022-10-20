package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account {
    @DecimalMax(value = "100000.00")
    @Embedded
    private Money creditLimit = new Money(BigDecimal.valueOf(100));
    @DecimalMin(value = "0.1")
  /*  @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currency", column = @Column(name = "cardCurrency")),
            @AttributeOverride( name = "amount", column = @Column(name = "cardAmount"))})*/
    private BigDecimal cardInterestRate = new BigDecimal(0.2);

    private LocalDate cardLastInterestDay;

    public CreditCard() {
    }

    public CreditCard(Money creditLimit, BigDecimal cardInterestRate, LocalDate cardLastInterestDay) {
        this.creditLimit = creditLimit;
        this.cardInterestRate = cardInterestRate;
        this.cardLastInterestDay = cardLastInterestDay;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus, Money creditLimit, BigDecimal cardInterestRate, LocalDate cardLastInterestDay) {
        super(balance, primaryOwner, penaltyFee, creationDate, accountStatus);
        this.creditLimit = creditLimit;
        this.cardInterestRate = cardInterestRate;
        this.cardLastInterestDay = cardLastInterestDay;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public CreditCard(AccountHolder secondaryOwner, Money creditLimit, BigDecimal cardInterestRate, LocalDate cardLastInterestDay) {
        super(secondaryOwner);
        this.creditLimit = creditLimit;
        this.cardInterestRate = cardInterestRate;
        this.cardLastInterestDay = cardLastInterestDay;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getCardInterestRate() {
        return cardInterestRate;
    }

    public void setCardInterestRate(BigDecimal cardInterestRate) {
        this.cardInterestRate = cardInterestRate;
    }

    public LocalDate getCardLastInterestDay() {
        return cardLastInterestDay;
    }

    public void setCardLastInterestDay(LocalDate cardLastInterestDay) {
        this.cardLastInterestDay = cardLastInterestDay;
    }

    public void cardInterestRate(){
        if(Period.between(cardLastInterestDay, LocalDate.now()).getMonths() >= 1) {
            BigDecimal bigDecimal = getBalance().getAmount().multiply(cardInterestRate);
            setBalance(new Money(getBalance().increaseAmount(bigDecimal)));
            cardLastInterestDay = LocalDate.now();
        }
}
}