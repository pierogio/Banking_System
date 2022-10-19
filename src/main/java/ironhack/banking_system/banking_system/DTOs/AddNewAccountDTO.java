package ironhack.banking_system.banking_system.DTOs;

public class AddNewAccountDTO {

    private Long userId;

    private String accountType;

    public AddNewAccountDTO(Long userId, String accountType) {
        this.userId = userId;
        this.accountType = accountType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
