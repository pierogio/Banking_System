package ironhack.banking_system.banking_system.controllers;

import ironhack.banking_system.banking_system.DTOs.TransferBalanceDTO;
import ironhack.banking_system.banking_system.embeddable.Money;
import ironhack.banking_system.banking_system.services.ThirdPartyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class ThirdPartyController {

    @Autowired
    ThirdPartyUserService thirdPartyUserService;

    @PatchMapping("/thirdparty/transfer")
    @ResponseStatus(HttpStatus.OK)
    public Money transferThirdParty(@RequestHeader String hashedKey, @RequestBody TransferBalanceDTO transferBalanceDTO) {
        return thirdPartyUserService.transferThirdParty(transferBalanceDTO.getAccountNumber(),transferBalanceDTO.getAmount(), transferBalanceDTO.getAccountNumber2(),hashedKey);
    }
}
