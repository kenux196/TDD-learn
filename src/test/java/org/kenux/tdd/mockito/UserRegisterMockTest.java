package org.kenux.tdd.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.tdd.exception.WeakPasswordException;
import org.kenux.tdd.repository.MemoryUserRepository;
import org.kenux.tdd.service.EmailNotifier;
import org.kenux.tdd.service.UserRegister;
import org.kenux.tdd.service.WeakPasswordChecker;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterMockTest {

    private UserRegister userRegister;
    private final WeakPasswordChecker mockPasswordChecker = mock(WeakPasswordChecker.class);
    private final MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    private final EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setup() {
        userRegister = new UserRegister(mockPasswordChecker, memoryUserRepository, mockEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void registerFailed_when_weakPassword() {
        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () ->
                userRegister.register("id", "pw", "email"));
    }

    @Test
    @DisplayName("회원 가입시 암호 검사 수행함")
    void checkPassword_when_registerUser() {
        userRegister.register("id", "pw", "email");

        // 대역 객체가 기대하는 대로 상호작용했는지 확인한다.
        then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(anyString());
    }

    @Test
    @DisplayName("가입하면 메일을 전송함")
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "test@test.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("test@test.com", realEmail);
    }
}
