package br.com.wells.app.wellsproduto.exception;

import java.util.List;
import java.util.Map;

public class ValidationError {

	Map<String, List<String>> fieldErrors;

	List<String> globalErrors;

	public ValidationError(Map<String, List<String>> fieldErrors, List<String> globalErrors) {
		this.fieldErrors = fieldErrors;
		this.globalErrors = globalErrors;
	}

	public Map<String, List<String>> getFieldErrors() {
		return fieldErrors;
	}

	public List<String> getGlobalErrors() {
		return globalErrors;
	}

}
