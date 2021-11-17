package org.kenux.tdd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.tdd.exception.DuplicateIdException;
import org.kenux.tdd.exception.WeakPasswordException;
import org.kenux.tdd.repository.MemoryUserRepository;
import org.kenux.tdd.service.SpyEmailNotifier;
import org.kenux.tdd.service.StubWeakPasswordChecker;
import org.kenux.tdd.service.UserRegister;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {

    private UserRegister userRegister;
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private final MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    private final SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setup() {
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호이면 가입 실패")
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true); // 암호가 약하다고 응답하도록 설정

        assertThrows(WeakPasswordException.class, () -> userRegister.register("id", "pw", "email"));
    }

    @Test
    @DisplayName("동일한 아이디가 존재하면 가입 실패")
    void registerFailed_If_duplicateId_exist() {
        memoryUserRepository.save(new User("id", "pw", "email"));

        assertThrows(DuplicateIdException.class, () -> userRegister.register("id", "pw", "email"));
    }

    @Test
    @DisplayName("같은 ID가 없으면 가입 성공함")
    void registerSuccess_If_duplicatedId_notExist() {
        userRegister.register("id", "pw", "email");

        User savedUser = memoryUserRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @Test
    @DisplayName("회원 가입 성공하면 이메일 발송함")
    void sendEmailWhenRegisterSuccess() {
        // when
        userRegister.register("id", "pw", "test@test.com");

        // then
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("test@test.com", spyEmailNotifier.getEmail());
    }
}
