package ironhack.banking_system.banking_system.services;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.models.accounts.Account;
import ironhack.banking_system.banking_system.models.users.AccountHolder;
import ironhack.banking_system.banking_system.repositories.AccountHolderRepository;
import ironhack.banking_system.banking_system.repositories.AccountRepository;
import ironhack.banking_system.banking_system.repositories.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountHolderService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingsRepository savingsRepository;

    public AccountHolder addAccountHolderUser(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }
    public List<Account> getAccountHolderAccount (Long userId) {
        AccountHolder accountHolder = accountHolderRepository.findById(userId).get();
        List<Account> accountHolderList = accountRepository.findByPrimaryOwner(accountHolder);
        List<Account> accountHolderList2 = accountRepository.findBySecondaryOwner(accountHolder);
        accountHolderList.addAll(accountHolderList2);
       return accountHolderList;
    }

    public Money getBalanceAccountHolder(Long accountNumber) {
        return accountRepository.findById(accountNumber).get().getBalance();
    }

    public Money transferBalanceAccountHolder(Long accountNumber, BigDecimal amount, Long accountNumber2) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        Account account2 = accountRepository.findById(accountNumber2).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        if(account.getBalance().getAmount().compareTo(amount)<0) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not enough funds");
        account.setBalance(new Money(account.getBalance().decreaseAmount(amount)));
        account2.setBalance(new Money(account2.getBalance().increaseAmount(amount)));
        accountRepository.saveAll(List.of(account, account2));
        return account.getBalance();
    }


}
