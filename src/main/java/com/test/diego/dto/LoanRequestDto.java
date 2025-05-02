package com.test.diego.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO para crear o solicitar un préstamo.
 * Contiene solo los campos necesarios para la petición de un nuevo préstamo.
 *
 * @Data                 Genera getters, setters, toString, equals y hashCode.
 * @JsonIgnoreProperties Ignora propiedades desconocidas en el JSON entrante.
 * @JsonInclude          No incluye propiedades nulas en el JSON de salida.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanRequestDto {

    /**
     * Monto solicitado para el préstamo. Debe ser positivo.
     */
    private BigDecimal amount;

    /**
     * Plazo del préstamo en meses. Mínimo 1.
     */
    private Integer termMonths;

    /**
     * Identificador del usuario que solicita el préstamo.
     */
    private Long userId;
}

