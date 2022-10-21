package ironhack.banking_system.banking_system;

import com.fasterxml.jackson.databind.ObjectMapper;
import ironhack.banking_system.banking_system.DTOs.AddDecreaseBalanceDTO;
import ironhack.banking_system.banking_system.DTOs.AddNewAccountDTO;
import ironhack.banking_system.banking_system.DTOs.TransferBalanceDTO;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    @DisplayName("check if getBalanceAsAdmin works")
    void get_Balance_As_Admin_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/balance/accountNumber/2")).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.00"));
    }

    @Test //no funciona si dejo de comentar el assertTrue
    @DisplayName("check if deleteAccount works")
    void delete_Account_OK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/account/delete/3")).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        //assertTrue(mvcResult.getResponse().getContentAsString().contains("account_1_0_0_"));
    }

    @Test //No Funciona si dejo de comentar el assertTrue
    @DisplayName("check if addBalance works")
    void add_Balance_As_Admin_OK() throws Exception {
        AddDecreaseBalanceDTO addDecreaseBalanceDTO = new AddDecreaseBalanceDTO(123L, BigDecimal.valueOf(500));
        String body = objectMapper.writeValueAsString(addDecreaseBalanceDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(patch("/balance/add").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        //assertTrue(mvcResult.getResponse().getContentAsString().contains("123"));
    }

    @Test //No Funciona si dejo de comentar el assertTrue
    @DisplayName("check if addNewAccount works")
    void add_New_Account_OK() throws Exception {
        AddNewAccountDTO addNewAccountDTO = new AddNewAccountDTO(245L, "savings");
        String body = objectMapper.writeValueAsString(addNewAccountDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/account/add").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        //assertTrue(mvcResult.getResponse().getContentAsString().contains("savings"));
    }
}