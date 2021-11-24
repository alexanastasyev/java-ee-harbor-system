package ru.rsreu.harbor.controller.filter.role;

import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.datalayer.model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActionCommandSupportedRolesDefiner {
    private final List<Role> allRoles;

    public ActionCommandSupportedRolesDefiner(List<Role> allRoles) {
        this.allRoles = allRoles;
    }

    public List<Role> getSupportedRoles(Class<? extends ActionCommand> actionCommandClass) {
        CommandSupportedRolesTitles commandSupportedRolesTitles =
                actionCommandClass.getAnnotation(CommandSupportedRolesTitles.class);
        List<Role> result = new ArrayList<>();
        if (!isSupportAllRoles(commandSupportedRolesTitles)) {
            result = formSupportedRoles(commandSupportedRolesTitles);
        }
        return result;
    }

    private List<Role> formSupportedRoles(CommandSupportedRolesTitles commandSupportedRolesTitles) {
        List<String> supportedRolesTitles = Arrays.asList(commandSupportedRolesTitles.titles());
        return allRoles.stream().filter(role ->
                (supportedRolesTitles.contains(role.getTitle()))).collect(Collectors.toList());
    }

    private boolean isSupportAllRoles(CommandSupportedRolesTitles commandSupportedRolesTitles) {
        return commandSupportedRolesTitles == null;
    }
}
