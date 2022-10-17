package ironhack.banking_system.banking_system.controllers;

import ironhack.banking_system.banking_system.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
}
