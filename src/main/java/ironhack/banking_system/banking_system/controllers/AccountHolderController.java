package ironhack.banking_system.banking_system.controllers;

import ironhack.banking_system.banking_system.DTOs.TransferBalanceDTO;
import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.models.accounts.Account;
import ironhack.banking_system.banking_system.models.users.AccountHolder;
import ironhack.banking_system.banking_system.repositories.AccountHolderRepository;
import ironhack.banking_system.banking_system.services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    //Add AccountHolder
    public AccountHolder addAccountHolderUser(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    @RequestMapping(value = "/accountHolder/account", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccountHolderAccount(@PathVariable Long accountHolderId) {
        return accountHolderService.getAccountHolderAccount(accountHolderId);
    }

    @GetMapping("/balance/accountHolder")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceAccountHolder(@RequestBody Long accountNumber) {
        return accountHolderService.getBalanceAccountHolder(accountNumber);
    }
    @PatchMapping("/balance/transfer")
    @ResponseStatus(HttpStatus.OK)
    public Money transferBalanceAccountHolder(@RequestBody TransferBalanceDTO transferBalanceDTO){
        return accountHolderService.transferBalanceAccountHolder(transferBalanceDTO.getAccountNumber(),transferBalanceDTO.getAmount(),transferBalanceDTO.getAccountNumber2());}

}