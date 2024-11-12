package com.revature.Project1;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.Services.UserService;
import com.revature.models.DTOs.UserDTO;
import com.revature.models.DTOs.UserRegisterDTO;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
class Project1ApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testCreateUser(){
		TestRestTemplate restTemplate=new TestRestTemplate();
         UserRegisterDTO newUserToCreate=new UserRegisterDTO("user","restTest","TestfromTestClass","test");

		 ResponseEntity<UserDTO> response =restTemplate.postForEntity("http://localhost:7777/users/signup",newUserToCreate, UserDTO.class );
	     assertEquals("201 CREATED",response.getStatusCode().toString());
	}
	@Spy
	UserService userService;

	@Mock
	UserDAO uDAO;

	@Mock
	ReimbursementDAO rDAO;

	UserRegisterDTO userDTO=new UserRegisterDTO("kalp","mock","mockTest","test");

	UserDTO outUserDTO=new UserDTO(1,"mockTest","kalp","mock","User");
    @BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
		userService=spy(new UserService(uDAO));

	}
	@Test
	public void testCreateNewUserwithMockito(){
		//when(uDAO.findById(1)).thenReturn(outUserDTO);
	}


}
