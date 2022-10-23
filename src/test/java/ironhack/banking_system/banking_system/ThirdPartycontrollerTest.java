package ironhack.banking_system.banking_system;


import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ThirdPartycontrollerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
    @Test //Ok
    @DisplayName("check if transferThirdParty works")
    void transfer_Third_Party_OK() throws Exception {
        TransferBalanceDTO transferBalanceDTO = new TransferBalanceDTO(1L, BigDecimal.valueOf(26.00), 2L);
        String body = objectMapper.writeValueAsString(transferBalanceDTO);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(patch("/balance/transfer").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        //System.out.println(mvcResult.getResolvedException().toString());
        //System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1094.00"));

    }
}
