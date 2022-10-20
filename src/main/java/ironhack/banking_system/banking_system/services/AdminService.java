package ironhack.banking_system.banking_system.services;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.accounts.Account;
import ironhack.banking_system.banking_system.models.accounts.StudentCheckingAccount;
import ironhack.banking_system.banking_system.models.accounts.CheckingAccount;
import ironhack.banking_system.banking_system.models.accounts.CreditCard;
import ironhack.banking_system.banking_system.models.accounts.Savings;
import ironhack.banking_system.banking_system.models.users.AccountHolder;
import ironhack.banking_system.banking_system.models.users.ThirdPartyUser;
import ironhack.banking_system.banking_system.repositories.AccountHolderRepository;
import ironhack.banking_system.banking_system.repositories.AccountRepository;
import ironhack.banking_system.banking_system.repositories.AdminRepository;
import ironhack.banking_system.banking_system.repositories.ThirdPartyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AdminService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ThirdPartyUserRepository thirdPartyUserRepository;

    /* public AccountHolder addAccountHolderUser(AccountHolder accountHolder) {
         return accountHolderRepository.save(accountHolder);
     }*/
    public Money getBalanceAsAdmin(Long accountNumber) {
        return accountRepository.findById(accountNumber).get().getBalance();
    }

    public Money addBalanceAsAdmin(Long accountNumber, BigDecimal amount) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        account.setBalance(new Money(account.getBalance().increaseAmount(amount)));
        accountRepository.save(account);
        return account.getBalance();
    }

    public Account addNewAccount(Long userId, String accountType) {
        AccountHolder accountHolder = accountHolderRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe"));
        if (accountType.contains("credit card")) {
            CreditCard creditCard = new CreditCard();
            creditCard.setBalance(new Money(BigDecimal.valueOf(1000)));
            creditCard.setPrimaryOwner((accountHolder));
            creditCard.setCreationDate(LocalDate.now());
            creditCard.setCreditLimit(new Money(BigDecimal.valueOf(1000)));
            creditCard.setCardInterestRate(BigDecimal.valueOf(0.2));
            creditCard.setCardLastInterestDay(LocalDate.now());
            creditCard.setPenaltyFee(new Money(BigDecimal.valueOf(40)));
            return accountRepository.save(creditCard);
        } else if (accountType.contains("savings")) {
            Savings savings = new Savings();
            savings.setBalance(new Money(BigDecimal.valueOf(1000)));
            savings.setInterestRate(BigDecimal.valueOf(0.0025));
            savings.setLastInterestDate(LocalDate.now());
            savings.setPrimaryOwner((accountHolder));
            savings.setCreationDate(LocalDate.now());
            savings.setMinimumBalance(new Money(BigDecimal.valueOf(1000)));
            savings.setCreationDate(LocalDate.now());
            savings.setPenaltyFee(new Money(BigDecimal.valueOf(40)));
            return accountRepository.save(savings);
        } else {
            if (Period.between(accountHolder.getDateOfBirth(), LocalDate.now()).getYears() >= 24) {
                CheckingAccount checkingAccount = new CheckingAccount();
                checkingAccount.setBalance(new Money(BigDecimal.valueOf(1000)));
                checkingAccount.setPrimaryOwner(accountHolder);
                checkingAccount.setMinimumBalance(new Money(BigDecimal.valueOf(250)));
                checkingAccount.setPenaltyFee(new Money(BigDecimal.valueOf(40)));
                checkingAccount.setMonthlyMaintenance(BigDecimal.valueOf(12));
                checkingAccount.setCreationDate(LocalDate.now());
                return accountRepository.save(checkingAccount);
            } else {
                StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount();
                studentCheckingAccount.setBalance(new Money(BigDecimal.valueOf(1000)));
                studentCheckingAccount.setPrimaryOwner(accountHolder);
                studentCheckingAccount.setPenaltyFee(new Money(BigDecimal.valueOf(40)));
                studentCheckingAccount.setCreationDate(LocalDate.now());
                return accountRepository.save(studentCheckingAccount);
            }
        }
    }

    public Account updateAccountStatus(Long accountNumber, AccountStatus accountStatus) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        account.setAccountStatus(accountStatus);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        accountRepository.delete(account);
    }

    public Money decreaseBalanceAsAdmin(Long accountNumber, BigDecimal amount) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        account.setBalance(new Money(account.getBalance().decreaseAmount(amount)));
        accountRepository.save(account);
        return account.getBalance();
    }

    public ThirdPartyUser addNewThirdParty(ThirdPartyUser thirdPartyUser) {
        return thirdPartyUserRepository.save(thirdPartyUser);
    }
}