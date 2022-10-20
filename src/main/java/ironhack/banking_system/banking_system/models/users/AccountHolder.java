package ironhack.banking_system.banking_system.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ironhack.banking_system.banking_system.embeddable.Address;
import ironhack.banking_system.banking_system.models.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends User{

    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;
   /* @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "street", column = @Column(name = "mailingStreet")),
            //@AttributeOverride( name = "city", column = @Column(name = "mailingCity")),
            @AttributeOverride( name = "postalCode", column = @Column(name = "mailingPostalCode")),
            @AttributeOverride( name = "country", column = @Column(name = "mailingCountry"))})
    private Address mailingAddress;*/

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> accounts;

    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> accounts2;


    public AccountHolder() {
    }

    public AccountHolder(String name, String password, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress, List<Account> accounts, List<Account> accounts2) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
       // this.mailingAddress = mailingAddress;
        this.accounts = accounts;
        this.accounts2 = accounts2;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

   /* public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }*/

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts2() {
        return accounts2;
    }

    public void setAccounts2(List<Account> accounts2) {
        this.accounts2 = accounts2;
    }
}
