package br.com.wells.core.util;

import lombok.NonNull;
import org.apache.commons.validator.routines.EmailValidator;

public final class EmailUtil {

	private EmailUtil() {
	}

	/**
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(@NonNull String email) {

		return EmailValidator.getInstance().isValid(email);
	}

}
