package ironhack.banking_system.banking_system.models.users;

import javax.persistence.Entity;

@Entity
public class ThirdPartyUser extends User {

    // hashed key

    private String thirdPartyUserName;

    public ThirdPartyUser() {
    }

    public ThirdPartyUser(String name) {
        super(name);
    }

    public ThirdPartyUser(String name, String thirdPartyUserName) {
        super(name);
        this.thirdPartyUserName = thirdPartyUserName;
    }

    public String getThirdPartyUserName() {
        return thirdPartyUserName;
    }

    public void setThirdPartyUserName(String thirdPartyUserName) {
        this.thirdPartyUserName = thirdPartyUserName;
    }
}
