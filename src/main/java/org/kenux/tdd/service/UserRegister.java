package org.kenux.tdd.service;

import org.kenux.tdd.domain.User;
import org.kenux.tdd.exception.DuplicateIdException;
import org.kenux.tdd.exception.WeakPasswordException;
import org.kenux.tdd.repository.UserRepository;

public class UserRegister {

    private final WeakPasswordChecker passwordChecker;
    private final UserRepository userRepository;
    private final EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker,
                        UserRepository userRepository,
                        EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }

        if (userRepository.findById(id) != null) {
            throw new DuplicateIdException();
        }

        userRepository.save(new User(id, pw, email));
        emailNotifier.sendRegisterEmail(email);
    }
}
