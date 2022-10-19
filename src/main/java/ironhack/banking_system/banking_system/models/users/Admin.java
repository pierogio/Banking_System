package ironhack.banking_system.banking_system.models.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    private String adminUserName;

     /* esperar a implementar la parte de seguridad
    private String password;

    private roles?
     */

    public Admin() {
    }

    public Admin(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public Admin(String name, String adminUserName) {
        super(name);
        this.adminUserName = adminUserName;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }
}
