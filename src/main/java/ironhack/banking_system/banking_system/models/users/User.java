package ironhack.banking_system.banking_system.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Role> roles;


    public User() {
    }
//no se si funcionará(password encoder con autowired
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}



