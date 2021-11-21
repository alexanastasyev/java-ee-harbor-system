package ru.rsreu.harbor.controller.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandSupportedRolesTitles {
    String[] titles();
}
