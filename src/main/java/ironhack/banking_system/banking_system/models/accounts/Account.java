package ironhack.banking_system.banking_system.models.accounts;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.users.AccountHolder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    @Embedded
    private Money balance;

    @ManyToOne
    @JoinColumn(name = "primary_owner_user_id")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_user_id")
    private AccountHolder secondaryOwner;

    public String owner;

    //esperando implementar parte de seguridad
    // public String secretKey;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currency", column = @Column(name = "penaltyCurrency")),
            @AttributeOverride( name = "amount", column = @Column(name = "penaltyAmount"))})
    public Money penaltyFee;

    public LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    public AccountStatus accountStatus;

    public Account() {
    }

    public Account(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Account(Money balance, AccountHolder primaryOwner, String owner, Money penaltyFee, LocalDate creationDate, AccountStatus accountStatus) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.owner = owner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.accountStatus = accountStatus;

    }

    public Long getAccountNumber() {
        return accountNumber;
    }


    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }
}
