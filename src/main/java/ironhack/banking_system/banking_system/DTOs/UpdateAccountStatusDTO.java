package ironhack.banking_system.banking_system.DTOs;

import ironhack.banking_system.banking_system.enums.AccountStatus;

public class UpdateAccountStatusDTO {

    private Long accountNumber;

    private AccountStatus accountStatus;

    public UpdateAccountStatusDTO(Long accountNumber, AccountStatus accountStatus) {
        this.accountNumber = accountNumber;
        this.accountStatus = accountStatus;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
