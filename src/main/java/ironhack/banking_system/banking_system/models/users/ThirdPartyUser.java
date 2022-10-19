package ironhack.banking_system.banking_system.models.users;

import javax.persistence.Entity;

@Entity
public class ThirdPartyUser extends User {

    private String hashedKey;

    public ThirdPartyUser() {
    }

    public ThirdPartyUser(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public ThirdPartyUser(String name, String hashedKey) {
        super(name);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
