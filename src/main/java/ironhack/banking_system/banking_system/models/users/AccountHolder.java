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

    private String mail;

    private String phone;

    /* esperar a implementar la parte de seguridad
    private String password;

    private roles?
     */
    private String userName;

    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "address", column = @Column(name = "mailingAddress")),
            @AttributeOverride( name = "postalCode", column = @Column(name = "mailingPostalCode")),
            @AttributeOverride( name = "country", column = @Column(name = "mailingCountry"))})
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> accounts;

    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> accounts2;


    public AccountHolder() {
    }

    public AccountHolder(Address primaryAddress, Address mailingAddress) {
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String name, Address primaryAddress, Address mailingAddress) {
        super(name);
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String name, String userName, LocalDate dateOfBirth) {
        super(name);
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
    }

    public AccountHolder(String mail, String phone) {
        this.mail = mail;
        this.phone = phone;
    }

    public AccountHolder(String name, String mail, String phone) {
        super(name);
        this.mail = mail;
        this.phone = phone;
    }

    public AccountHolder(String name) {
        super(name);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
