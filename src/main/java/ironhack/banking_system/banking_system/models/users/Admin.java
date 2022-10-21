package ironhack.banking_system.banking_system.models.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin() {
    }

    public Admin(String name, String password) {
        super(name, password);
    }
}
