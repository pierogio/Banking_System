package ironhack.banking_system.banking_system.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String address;

    private String postalCode;

    private String country;

    public Address() {
    }

    public Address(String address, String postalCode, String country) {
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
