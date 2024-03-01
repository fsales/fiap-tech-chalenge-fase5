package br.com.fsales.wells.core.util;

import java.util.regex.Pattern;

import lombok.NonNull;

public final class EmailUtil {

    private EmailUtil() {
    }

    /**
     * @param email
     * @return
     */
    public static boolean isValidEmail(@NonNull String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, email);
    }
}
