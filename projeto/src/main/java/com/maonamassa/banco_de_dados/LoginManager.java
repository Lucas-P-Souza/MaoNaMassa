package com.maonamassa.banco_de_dados;

import java.util.prefs.Preferences;

public class LoginManager {

    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";

    private Preferences prefs;

    public LoginManager() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    // Salva as preferências
    public void savePreferences(String email, String password, boolean rememberMe) {
        prefs.put(EMAIL_KEY, email);
        prefs.put(PASSWORD_KEY, password);
        prefs.putBoolean(REMEMBER_ME_KEY, rememberMe);
    }

    // Carrega as preferências
    public String[] loadPreferences() {
        String email = prefs.get(EMAIL_KEY, "");
        String password = prefs.get(PASSWORD_KEY, "");
        boolean rememberMe = prefs.getBoolean(REMEMBER_ME_KEY, false);

        return new String[] { email, password, String.valueOf(rememberMe) };
    }

    // Limpa as preferências
    public void clearPreferences() {
        prefs.remove(EMAIL_KEY);
        prefs.remove(PASSWORD_KEY);
        prefs.remove(REMEMBER_ME_KEY);
    }
}
