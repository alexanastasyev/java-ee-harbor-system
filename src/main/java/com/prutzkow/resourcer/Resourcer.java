package com.prutzkow.resourcer;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Using initialization-on-demand and thread-safety.
 *
 * @version 2.0
 */
public class Resourcer {
    public static final String MISSING_PARAMETER_VALUE_MESSAGE_FORMAT = "Value for parameter \"%s\" is missing";

    private static final String DEFAULT_PROPERTY_NAME = "resources.text";
    private static String basename = DEFAULT_PROPERTY_NAME;

    private static class ResourcerHolder {
        private static String basename = Resourcer.basename;
        private static ResourceBundle resources = ResourceBundle.getBundle(
                Resourcer.basename, Locale.getDefault());
    }

    private Resourcer() {
    }

    public static void setBasename(String basename) {
        Resourcer.basename = basename;
    }

    private static ResourceBundle getInstance() {
        synchronized (Resourcer.class) {
            if ((Resourcer.isBasenameChanged())
                    && (Resourcer.isLocaleChanged())) {
                ResourcerHolder.basename = Resourcer.basename;
                ResourcerHolder.resources = ResourceBundle.getBundle(
                        Resourcer.basename, Locale.getDefault());
            }
        }
        return ResourcerHolder.resources;
    }

    private static boolean isLocaleChanged() {
        Locale systemLocale = Locale.getDefault();
        Locale resourcerLocale = ResourcerHolder.resources.getLocale();

        return (!resourcerLocale.equals(systemLocale));
    }

    private static boolean isBasenameChanged() {
        return (!Resourcer.basename.equals(ResourcerHolder.basename));
    }

    public static String getString(String resourceKey) {
        ResourceBundle resources = Resourcer.getInstance();
        String resourceValue;

        try {
            resourceValue = resources.getString(resourceKey);
        } catch (MissingResourceException e) {
            resourceValue = String.format(
                    Resourcer.MISSING_PARAMETER_VALUE_MESSAGE_FORMAT,
                    resourceKey);
        }

        return resourceValue;
    }
}