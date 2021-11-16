package org.kenux.tdd.repository;

import org.kenux.tdd.domain.AutoDebitInfo;


public interface AutoDebitInfoRepository {

    AutoDebitInfo findOne(String userId);

    void save(AutoDebitInfo autoDebitInfo);
}
