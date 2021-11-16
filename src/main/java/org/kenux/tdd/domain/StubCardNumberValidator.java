package org.kenux.tdd.domain;

import org.kenux.tdd.domain.enums.CardValidity;

public class StubCardNumberValidator extends CardNumberValidator {

    private String invalidNo;
    private String theftNo;

    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }

    @Override
    public CardValidity validate(String carNumber) {
        if (invalidNo != null && invalidNo.equals(carNumber)) {
            return CardValidity.INVALID;
        }

        if (theftNo != null && theftNo.equals(carNumber)) {
            return CardValidity.THEFT;
        }

        return CardValidity.VALID;
    }
}
