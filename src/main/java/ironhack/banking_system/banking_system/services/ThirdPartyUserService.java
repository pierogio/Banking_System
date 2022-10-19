package ironhack.banking_system.banking_system.services;

import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.models.accounts.Account;
import ironhack.banking_system.banking_system.models.users.ThirdPartyUser;
import ironhack.banking_system.banking_system.repositories.AccountRepository;
import ironhack.banking_system.banking_system.repositories.ThirdPartyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ThirdPartyUserService {

    @Autowired
    ThirdPartyUserRepository thirdPartyUserRepository;

    @Autowired
    AccountRepository accountRepository;

    public Money transferThirdParty(Long accountNumber, BigDecimal amount, Long accountNumber2, String hashedKey) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        if(!thirdPartyUserRepository.findByHashedKey(hashedKey).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"No está disponible");
        Account account2 = accountRepository.findById(accountNumber2).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
        if (account.getBalance().getAmount().compareTo(amount) < 0)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not enough funds");
        account.setBalance(new Money(account.getBalance().decreaseAmount(amount)));
        account2.setBalance(new Money(account2.getBalance().increaseAmount(amount)));
        accountRepository.saveAll(List.of(account, account2));
        return account.getBalance();
    }
}