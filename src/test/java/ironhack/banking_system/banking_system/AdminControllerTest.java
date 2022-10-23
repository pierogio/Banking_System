package ironhack.banking_system.banking_system;
import com.fasterxml.jackson.databind.ObjectMapper;
import ironhack.banking_system.banking_system.DTOs.AddDecreaseBalanceDTO;
import ironhack.banking_system.banking_system.DTOs.AddNewAccountDTO;
import ironhack.banking_system.banking_system.DTOs.TransferBalanceDTO;
import ironhack.banking_system.banking_system.DTOs.UpdateAccountStatusDTO;
import ironhack.banking_system.banking_system.enums.AccountStatus;
import ironhack.banking_system.banking_system.models.accounts.Account;
import ironhack.banking_system.banking_system.models.users.AccountHolder;
import ironhack.banking_system.banking_system.models.users.ThirdPartyUser;
import ironhack.banking_system.banking_system.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test //Ok
    @DisplayName("check if getBalanceAsAdmin works")
    void get_Balance_As_Admin_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/balance/accountNumber/2")).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.00"));
    }

    @Test //Ok
    @DisplayName("check if deleteAccount works")
    void delete_Account_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/account/delete/3")).andExpect(status().isOk()).andReturn();
        assertFalse(accountRepository.findById(3L).isPresent());
    }

    @Test //Ok
    @DisplayName("check if addBalance works")
    void add_Balance_As_Admin_OK() throws Exception {
        AddDecreaseBalanceDTO addDecreaseBalanceDTO = new AddDecreaseBalanceDTO(1L, BigDecimal.valueOf(500));
        String body = objectMapper.writeValueAsString(addDecreaseBalanceDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(patch("/balance/add").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1620.00"));
    }

    @Test //Ok
    @DisplayName("check if addNewAccount works")
    void add_New_Account_OK() throws Exception {
        AddNewAccountDTO addNewAccountDTO = new AddNewAccountDTO(3L, "savings");
        String body = objectMapper.writeValueAsString(addNewAccountDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/account/add").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5"));
    }

    @Test //Ok
    @DisplayName("check if updateAccountStatus works")
    void update_Account_Status_OK() throws Exception {
        UpdateAccountStatusDTO updateAccountStatusDTO = new UpdateAccountStatusDTO(3L, AccountStatus.ACTIVE);
        String body = objectMapper.writeValueAsString(updateAccountStatusDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(patch("/account/status").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("ACTIVE"));
    }

    @Test //Ok
    @DisplayName("check if decreaseBalance works")
    void add_decrease_Balance_AsAdmin_OK() throws Exception {
        AddDecreaseBalanceDTO addDecreaseBalanceDTO = new AddDecreaseBalanceDTO(2L, BigDecimal.valueOf(500));
        String body = objectMapper.writeValueAsString(addDecreaseBalanceDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(patch("/balance/decrease").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1460.00"));
    }

    @Test //Ok
    @DisplayName("check if addNewThirdParty works")
    void add_New_ThirdParty_OK() throws Exception {
        ThirdPartyUser thirdPartyUser = new ThirdPartyUser("Quim", "1234", "ciao");
        String body = objectMapper.writeValueAsString(thirdPartyUser);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/thirdparty/add").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResolvedException().toString());
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("4"));
    }

}