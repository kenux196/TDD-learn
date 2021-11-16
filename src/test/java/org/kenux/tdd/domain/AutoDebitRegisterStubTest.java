package org.kenux.tdd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kenux.tdd.domain.enums.CardValidity.INVALID;
import static org.kenux.tdd.domain.enums.CardValidity.THEFT;

public class AutoDebitRegisterStubTest {

    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private MemoryAutoDebitInfoRepository memoryRepository;

    @BeforeEach
    void setup() {
        stubValidator = new StubCardNumberValidator();
        memoryRepository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, memoryRepository);
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        memoryRepository.save(
                new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = memoryRepository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    @Test
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "123412341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = memoryRepository.findOne("user1");
        assertEquals("1234123412341234", saved.getCardNumber());
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = register.register(req);

        assertEquals(INVALID, result.getValidity());
    }

    @Test
    void theftCard() {
        stubValidator.setTheftNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);

        assertEquals(THEFT, result.getValidity());
    }

}
