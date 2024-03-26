package br.com.wells.usuario.app.presentation.rest.controller.auth.dto.response;

public record TokenResponseDTO(String token, boolean valid, boolean expired) {

}
