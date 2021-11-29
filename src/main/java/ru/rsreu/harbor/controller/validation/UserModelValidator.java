package ru.rsreu.harbor.controller.validation;

public interface UserModelValidator {
    boolean isCreateUserFormValid(
            String login,
            String passwords,
            String roleIdParameter,
            String statusIdParameter);

    boolean isEditUserFormDataValid(
            String sessionLogin,
            String idParameter,
            String login,
            String password,
            String roleIdParameter,
            String statusIdParameter);
}
