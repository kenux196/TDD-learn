package org.kenux.tdd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kenux.tdd.repository.AutoDebitInfoRepository;
import org.kenux.tdd.repository.MemoryAutoDebitInfoRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kenux.tdd.domain.enums.CardValidity.THEFT;
import static org.kenux.tdd.domain.enums.CardValidity.VALID;

class AutoDebitRegisterTest {

    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new MemoryAutoDebitInfoRepository();
        AutoDebitInfo autoDebitInfo = new AutoDebitInfo("user1", "1234123412341234", LocalDateTime.now());
        repository.save(autoDebitInfo);
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValidity());
    }

}