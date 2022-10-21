package ironhack.banking_system.banking_system.controllers;

import ironhack.banking_system.banking_system.DTOs.AddDecreaseBalanceDTO;
import ironhack.banking_system.banking_system.DTOs.AddNewAccountDTO;
import ironhack.banking_system.banking_system.DTOs.UpdateAccountStatusDTO;
import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.models.accounts.Account;
import ironhack.banking_system.banking_system.models.users.ThirdPartyUser;
import ironhack.banking_system.banking_system.repositories.AccountRepository;
import ironhack.banking_system.banking_system.repositories.AdminRepository;
import ironhack.banking_system.banking_system.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminService adminService;
    @Autowired
    AccountRepository accountRepository;

    //get balance as Admin ok
    @GetMapping("/balance/accountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceAsAdmin(@PathVariable Long accountNumber){
        return adminService.getBalanceAsAdmin(accountNumber);
    }
    //add balance
    @PatchMapping("/balance/add")
    @ResponseStatus(HttpStatus.OK)
    public Money addBalanceAsAdmin(@RequestBody AddDecreaseBalanceDTO addDecreaseBalanceDTO){
        return adminService.addBalanceAsAdmin(addDecreaseBalanceDTO.getAccountNumber(), addDecreaseBalanceDTO.getAmount());
    }
    //add new account
    @PostMapping("/account/add")
    @ResponseStatus(HttpStatus.OK)
    public Account addNewAccount(@RequestBody AddNewAccountDTO addNewAccountDTO){
        return adminService.addNewAccount(addNewAccountDTO.getUserId(),addNewAccountDTO.getAccountType());
    }
    //change status
    @PatchMapping("/account/status")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccountStatus(@RequestBody UpdateAccountStatusDTO updateAccountStatusDTO){
        return adminService.updateAccountStatus(updateAccountStatusDTO.getAccountNumber(),updateAccountStatusDTO.getAccountStatus());
    }
    //delete account
    @DeleteMapping("/account/delete/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long accountNumber){
        adminService.deleteAccount(accountNumber);
    }

    //decrease balance
    @PatchMapping("/balance/decrease")
    @ResponseStatus(HttpStatus.OK)
    public Money decreaseBalanceAsAdmin(@RequestBody AddDecreaseBalanceDTO addDecreaseBalanceDTO){
        return adminService.addBalanceAsAdmin(addDecreaseBalanceDTO.getAccountNumber(), addDecreaseBalanceDTO.getAmount());
    }
    //add thir-dparty
    @PatchMapping("/thirdparty/add")
    @ResponseStatus(HttpStatus.OK)
    public ThirdPartyUser addNewThirdParty(ThirdPartyUser thirdPartyUser) {
        return adminService.addNewThirdParty(thirdPartyUser);
    }


    }



