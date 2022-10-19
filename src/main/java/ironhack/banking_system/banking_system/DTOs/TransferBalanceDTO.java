package ironhack.banking_system.banking_system.DTOs;

import java.math.BigDecimal;

public class TransferBalanceDTO {

    private Long accountNumber;

    private BigDecimal amount;

    private Long accountNumber2;

    public TransferBalanceDTO(Long accountNumber, BigDecimal amount, Long accountNumber2) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.accountNumber2 = accountNumber2;
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

    public Long getAccountNumber2() {
        return accountNumber2;
    }

    public void setAccountNumber2(Long accountNumber2) {
        this.accountNumber2 = accountNumber2;
    }
}
