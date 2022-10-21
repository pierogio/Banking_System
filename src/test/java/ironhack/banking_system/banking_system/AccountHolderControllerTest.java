package ironhack.banking_system.banking_system;

import com.fasterxml.jackson.databind.ObjectMapper;
import ironhack.banking_system.banking_system.DTOs.TransferBalanceDTO;
import ironhack.banking_system.banking_system.models.accounts.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        //getAccountHolderAccount, getBalanceAccountHolder, transferBalanceAccountHolder

    }
    @Test
    @DisplayName("check if getAccountHolder works")
    void get_AccountHolder_Account_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accountHolder/account/2")).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("SuperOscar"));
    }

    @Test
    @DisplayName("check if getBalanceAccountHolder works")
    void get_Balance_AccountHolder_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/balance/accountHolder/3")).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("860.00"));
    }
    @Test //No Funciona
    @DisplayName("check if transferBalanceAccountHolder works")
    void transfer_Balance_AccountHolder_OK() throws Exception {
        TransferBalanceDTO transferBalanceDTO = new TransferBalanceDTO(100L, BigDecimal.valueOf(25.00), 500L);
        String body = objectMapper.writeValueAsString(transferBalanceDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(patch("/balance/transfer").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        //assertTrue(mvcResult.getResponse().getContentAsString().contains("10.00"));

    }
}
