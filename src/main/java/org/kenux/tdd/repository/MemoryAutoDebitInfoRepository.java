package org.kenux.tdd.repository;

import org.kenux.tdd.domain.AutoDebitInfo;
import org.kenux.tdd.repository.AutoDebitInfoRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {

    private final Map<String, AutoDebitInfo> autoDebitInfoList = new HashMap<>();

    public AutoDebitInfo findOne(String userId) {
        return autoDebitInfoList.get(userId);
    }

    public void save(AutoDebitInfo autoDebitInfo) {
        autoDebitInfoList.put(autoDebitInfo.getUserId(), autoDebitInfo);
    }
}
