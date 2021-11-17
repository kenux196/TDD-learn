package org.kenux.tdd.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.tdd.repository.MemoryUserRepository;
import org.kenux.tdd.repository.UserRepository;
import org.kenux.tdd.service.*;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterMockTest {

    private UserRegister userRegister;
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private final MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    private final EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setup() {
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, mockEmailNotifier);
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMain() {
        userRegister.register("id", "pw", "test@test.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        Assertions.assertEquals("test@test.com", realEmail);
    }
}
