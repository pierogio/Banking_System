package ironhack.banking_system.banking_system.models.users;

import javax.persistence.Entity;

@Entity
public class ThirdPartyUser extends User {

    private String ThirdPartyName;

    private String hashedKey;

    public ThirdPartyUser() {
    }

    public ThirdPartyUser(String thirdPartyName, String hashedKey) {
        ThirdPartyName = thirdPartyName;
        this.hashedKey = hashedKey;
    }

    public ThirdPartyUser(String name, String password, String thirdPartyName, String hashedKey) {
        super(name, password);
        ThirdPartyName = thirdPartyName;
        this.hashedKey = hashedKey;
    }

    public String getThirdPartyName() {
        return ThirdPartyName;
    }

    public void setThirdPartyName(String thirdPartyName) {
        ThirdPartyName = thirdPartyName;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
