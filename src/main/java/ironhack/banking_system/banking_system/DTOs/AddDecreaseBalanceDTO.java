package ironhack.banking_system.banking_system.DTOs;

import java.math.BigDecimal;

public class AddDecreaseBalanceDTO {

    private Long accountNumber;

    private BigDecimal amount;

    public AddDecreaseBalanceDTO(Long accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
