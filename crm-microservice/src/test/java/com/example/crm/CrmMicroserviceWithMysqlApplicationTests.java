package com.example.crm;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.crm.application.CrmApplication;
import com.example.crm.application.business.exception.CustomerNotFoundException;
import com.example.crm.dto.response.DetailedCustomerResponse;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = CrmMicroserviceWithMysqlApplication.class)
@AutoConfigureMockMvc
class CrmMicroserviceWithMysqlApplicationTests {
	
	@Autowired private MockMvc mvc;
	@MockBean
	private CrmApplication crmApplication;
	
	@Test
	@DisplayName("GET request to an exising customer returns OK(200)")
	void getCustomerByIdentity_then_return_success() throws Throwable {
		// 1. Test Fixture
		var customerResponse = new DetailedCustomerResponse();
		customerResponse.setIdentity("11111111110");
		customerResponse.setFullname("jack bauer");
		customerResponse.setEmail("jack.bauer@example.com");
		Mockito.when(crmApplication.findCustomerByIdentity("11111111110"))
		       .thenReturn(customerResponse);
		// 2. Call exercise method + 3. Verification
		mvc.perform(MockMvcRequestBuilders.get("/customers/11111111110"))
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("identity", is("11111111110")))
		   .andExpect(jsonPath("fullname", is("jack bauer")))
		   .andExpect(jsonPath("email", is("jack.bauer@example.com")));
		// 4. Tear-down
	}
	
	@Test
	@DisplayName("GET request to an unknown customer returns NOT_FOUND(404)")
	void getCustomerByIdentity_then_return_failure() throws Throwable {
		// 1. Test Fixture
		Mockito.when(crmApplication.findCustomerByIdentity("11111111110"))
		       .thenThrow(CustomerNotFoundException.class);
		// 2. Call exercise method + 3. Verification
		mvc.perform(MockMvcRequestBuilders.get("/customers/11111111110"))
		.andExpect(status().isNotFound());
		//.andExpect(jsonPath("identity", is("11111111110")))
		//.andExpect(jsonPath("email", is("jack.bauer@example.com")));
		// 4. Tear-down
	}

}
