package ironhack.banking_system.banking_system.services;

import ironhack.banking_system.banking_system.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;
}
