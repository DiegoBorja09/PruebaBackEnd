package com.test.diego.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Clase que representa un objeto de transferencia de datos (DTO) para la
 * entidad "UserEntity". Este DTO se utiliza para transportar información sobre
 * parámetros entre diferentes capas de la aplicación.
 *
 * @Data                 Anotación de Lombok que genera automáticamente getters,
 *                       setters, toString y otros métodos.
 * @JsonIgnoreProperties Anotación que indica que se deben ignorar propiedades
 *                       desconocidas durante la serialización/deserialización
 *                       JSON.
 * @JsonInclude          Anotación que especifica que las propiedades nulas no
 *                       se deben incluir en la serialización JSON.
 * @author
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

	private Long id;

	private String name;

	private String identificarionNumber;

	private String email;

	private String address;
}
