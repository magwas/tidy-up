package com.kodekonveyor.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.webapp.AuthenticationStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Data access")
@TestedService("AuthenticatedUserService")
public class AuthenticatedUserServiceCreateUserTest {

	@InjectMocks
	private AuthenticatedUserService authenticatedUserService;
	@Mock
	private UserEntityRepository userEntityRepository;
	private UserTestData userTestData;

	@BeforeEach
	public void setUp() {
		userTestData = new UserTestData();
		UserEntityRepositoryStubs.behaviour(userEntityRepository, userTestData);
	}

	@Test
	@TestedBehaviour("Creates user if does not exist")
	@TestedService("AuthenticatedUserService")
	@DisplayName("When there is no user for the credential, we create it")
	public void test6() {
		AuthenticationStubs.badAuthenticated(userTestData);
		assertEquals(userTestData.BAD_USER, authenticatedUserService.call());
	}

}
