package com.github.kosmateus.shinden.i18n;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Utility class for handling translations and internationalization.
 * <p>
 * The {@code TranslationUtil} class provides methods to initialize translation resources,
 * retrieve individual translations by key, and fetch groups of translations based on a prefix.
 * It supports localization by allowing the application to set a specific {@link Locale} and loads
 * translations from resource bundles using UTF-8 encoding.
 * </p>
 *
 * @version 1.0.0
 */
public final class TranslationUtil {

    private static Locale locale = Locale.getDefault();
    private static boolean initialized = false;

    /**
     * Initializes the translation utility with the specified locale.
     * <p>
     * This method sets the locale and loads the corresponding translations.
     * It must be called before attempting to retrieve translations. If initialization
     * is attempted more than once, an {@link IllegalStateException} is thrown.
     * </p>
     *
     * @param locale the {@link Locale} to use for loading translations.
     * @throws IllegalStateException if the utility is already initialized.
     */
    public static void init(Locale locale) {
        if (initialized) {
            throw new IllegalStateException("TranslationUtil already initialized");
        }
        TranslationUtil.locale = locale;
        loadTranslations();
        initialized = true;
    }

    /**
     * Retrieves the translation associated with the given key.
     * <p>
     * If the utility has not been initialized, it will initialize with the default locale
     * before retrieving the translation.
     * </p>
     *
     * @param key the translation key.
     * @return the translated string associated with the key.
     */
    public static String getTranslation(String key) {
        if (!initialized) {
            init(Locale.getDefault());
        }
        return TRANSLATIONS.get(key);
    }

    private static Map<String, String> TRANSLATIONS;

    /**
     * Retrieves a group of translations that share a common prefix.
     * <p>
     * This method filters the loaded translations by the specified prefix and returns
     * a map of translations with the prefix removed from the keys.
     * </p>
     *
     * @param prefix the prefix used to filter the translation keys.
     * @return a map of translations with keys stripped of the prefix.
     */
    public static Map<String, String> getTranslationGroup(String prefix) {
        if (!initialized) {
            init(Locale.getDefault());
        }
        return TRANSLATIONS.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .collect(Collectors.toMap(
                        entry -> entry.getKey().substring(prefix.length() + 1),
                        Map.Entry::getValue
                ));
    }

    /**
     * Loads the translations for the current locale.
     * <p>
     * This method loads translations from a resource bundle named "translations"
     * using the current locale and UTF-8 encoding.
     * </p>
     */
    private static void loadTranslations() {
        ResourceBundle translations = ResourceBundle.getBundle("translations", locale, new UTF8Control());
        TRANSLATIONS = translations.keySet().stream()
                .collect(Collectors.toMap(
                        key -> key,
                        translations::getString
                ));
    }
}
