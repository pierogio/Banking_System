package ironhack.banking_system.banking_system.controllers;

import ironhack.banking_system.banking_system.repositories.AccountRepository;
import ironhack.banking_system.banking_system.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;
}
