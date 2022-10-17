package ironhack.banking_system.banking_system.models.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

     /* esperar a implementar la parte de seguridad
    private String password;

    private String userName;

    private roles?
     */

    public Admin() {
    }

    public Admin(String name) {
        super(name);
    }



}
