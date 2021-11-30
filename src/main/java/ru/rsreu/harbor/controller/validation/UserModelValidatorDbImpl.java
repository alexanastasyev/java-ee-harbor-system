package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;

public class UserModelValidatorDbImpl implements UserModelValidator {
    private final static String NOT_EMPTY_OR_WHITE_SPACE_REGEX_PATTERN = "^\\S+$";
    private final static long ADMIN_ROLE_ID = 1L;
    private final static long ACTIVE_STATUS_ID = 1L;


    private final UserDao userDao;
    private final RoleDao roleDao;
    private final StatusDao statusDao;

    public UserModelValidatorDbImpl(UserDao userDao, RoleDao roleDao, StatusDao statusDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public boolean isCreateUserFormValid(
            String login,
            String passwords,
            String roleIdParameter,
            String statusIdParameter) {
        return isUserWithLoginNotExists(login) &&
                isValidLogin(login) && isValidPassword(login) &&
                this.isValidRole(roleIdParameter) &&
                this.isValidStatus(statusIdParameter);
    }

    @Override
    public boolean isEditUserFormDataValid(
            String sessionLogin,
            String idParameter,
            String login,
            String password,
            String roleIdParameter,
            String statusIdParameter) {
        return (!isAdminSelfEditingValid(sessionLogin, idParameter) ||
                isAdminRole(roleIdParameter) && isActiveStatus(statusIdParameter)) &&
                (isUserWithLoginNotExists(login) || isOldLoginEqualsNew(login, idParameter)) &&
                isValidLogin(login) && isValidPassword(password) &&
                this.isValidRole(roleIdParameter) && this.isValidStatus(statusIdParameter);
    }

    private boolean isActiveStatus(String statusIdParameter) {
        try {
            return this.statusDao.findById(Long.valueOf(statusIdParameter)).orElseThrow(IllegalArgumentException::new)
                    .getId().equals(ACTIVE_STATUS_ID);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }

    }

    private boolean isAdminRole(String roleIdParameter) {
        try {
            return this.roleDao.findById(Long.valueOf(roleIdParameter)).orElseThrow(IllegalArgumentException::new)
                    .getId().equals(ADMIN_ROLE_ID);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isAdminSelfEditingValid(String sessionLogin, String idParameter) {
        try {
            return this.userDao.findByLogin(sessionLogin).orElseThrow(IllegalArgumentException::new).getId().equals(
                    this.userDao.findById(Long.valueOf(idParameter)).orElseThrow(IllegalArgumentException::new).getId()
            );
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isUserWithLoginNotExists(String login) {
        return !this.userDao.findByLogin(login).isPresent();
    }

    private boolean isOldLoginEqualsNew(String login, String idParameter) {
        try {
            return this.userDao.findByLogin(login).orElseThrow(IllegalArgumentException::new).getId()
                    .equals(
                            userDao.findById(
                                            Long.valueOf(idParameter)).orElseThrow(IllegalArgumentException::new)
                                    .getId());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }

    }

    private boolean isValidRole(String roleIdParameter) {
        try {
            return this.roleDao.findById(Long.valueOf(roleIdParameter)).isPresent();
        } catch (NumberFormatException exception) {
            return false;
        }

    }

    private boolean isValidStatus(String statusIdParameter) {
        try {
            return this.statusDao.findById(Long.valueOf(statusIdParameter)).isPresent();
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private static boolean isValidLogin(String login) {
        return login != null && login.matches(NOT_EMPTY_OR_WHITE_SPACE_REGEX_PATTERN);
    }

    private static boolean isValidPassword(String password) {
        return password != null && password.matches(NOT_EMPTY_OR_WHITE_SPACE_REGEX_PATTERN);
    }
}