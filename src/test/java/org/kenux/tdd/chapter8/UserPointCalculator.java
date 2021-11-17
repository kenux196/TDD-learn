package org.kenux.tdd.chapter8;

import org.kenux.tdd.domain.User;

import java.time.LocalDate;

public class UserPointCalculator {

    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;
    private PointRule pointRule = new PointRule();

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public void setPointRule(PointRule pointRule) {
        this.pointRule = pointRule;
    }

    public int calculatePoint(User user) {
        Subscription s = subscriptionDao.selectByUser(user.getId());
        if (s == null) {
            throw new NoSubscriptionException();
        }
        Product p = productDao.selectById(s.getProductId());
        LocalDate now = LocalDate.now();
        return pointRule.calculate(s, p, now);
    }
}
