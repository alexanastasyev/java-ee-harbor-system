package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

public class HandleUserBlockingValidatorDbImpl implements HandleUserBlockingValidator {
    private final static long ADMIN_ROLE_ID = 1L;
    private final static long MODERATOR_ROLE_ID = 2L;

    private final UserDao userDao;

    public HandleUserBlockingValidatorDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValidBlocking(String idParameter) {
        try {
            User blockingUser = this.userDao.findByLogin(idParameter).orElseThrow(IllegalArgumentException::new);
            return !(blockingUser.getRole().getId().equals(ADMIN_ROLE_ID) ||
                    blockingUser.getRole().getId().equals(MODERATOR_ROLE_ID));
        } catch (NumberFormatException | NullPointerException exception) {
            return false;
        }
    }
}
