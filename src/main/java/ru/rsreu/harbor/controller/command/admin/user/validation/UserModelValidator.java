package ru.rsreu.harbor.controller.command.admin.user.validation;

public interface UserModelValidator {
    boolean isCreateUserFormValid(
            String login,
            String passwords,
            String roleIdParameter,
            String statusIdParameter);

    boolean isEditUserFormDataValid(
            String idParameter,
            String login,
            String password,
            String roleIdParameter,
            String statusIdParameter);
}
