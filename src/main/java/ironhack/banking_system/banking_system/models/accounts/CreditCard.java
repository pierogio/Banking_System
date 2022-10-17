package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CreditCard extends Account {
    @Embedded
    private Money creditLimit;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currency", column = @Column(name = "cardCurrency")),
            @AttributeOverride( name = "amount", column = @Column(name = "cardAmount"))})
    private Money cardInterestRate;

    private LocalDate cardLastInterestDay;

    public CreditCard() {
    }

    public CreditCard(Money creditLimit, Money cardInterestRate, LocalDate cardLastInterestDay) {
        this.creditLimit = creditLimit;
        this.cardInterestRate = cardInterestRate;
        this.cardLastInterestDay = cardLastInterestDay;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, String owner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus, Money creditLimit, Money cardInterestRate, LocalDate cardLastInterestDay) {
        super(balance, primaryOwner, owner, penaltyFee, creationDate, accountStatus);
        this.creditLimit = creditLimit;
        this.cardInterestRate = cardInterestRate;
        this.cardLastInterestDay = cardLastInterestDay;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public CreditCard(AccountHolder secondaryOwner, Money creditLimit, Money cardInterestRate, LocalDate cardLastInterestDay) {
        super(secondaryOwner);
        this.creditLimit = creditLimit;
        this.cardInterestRate = cardInterestRate;
        this.cardLastInterestDay = cardLastInterestDay;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Money getCardInterestRate() {
        return cardInterestRate;
    }

    public void setCardInterestRate(Money cardInterestRate) {
        this.cardInterestRate = cardInterestRate;
    }

    public LocalDate getCardLastInterestDay() {
        return cardLastInterestDay;
    }

    public void setCardLastInterestDay(LocalDate cardLastInterestDay) {
        this.cardLastInterestDay = cardLastInterestDay;
    }
}
