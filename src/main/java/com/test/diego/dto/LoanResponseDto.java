package com.test.diego.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.diego.entity.LoanStatus;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO para representar la respuesta de un préstamo.
 * Incluye todos los datos que el cliente debe ver tras una operación.
 *
 * @Data                 Genera getters, setters, toString, equals y hashCode.
 * @JsonIgnoreProperties Ignora propiedades desconocidas en el JSON entrante.
 * @JsonInclude          No incluye propiedades nulas en el JSON de salida.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponseDto {

    /**
     * Identificador único del préstamo.
     */
    private Long id;

    /**
     * Monto aprobado o solicitado.
     */
    private BigDecimal amount;

    /**
     * Plazo en meses.
     */
    private Integer termMonths;

    /**
     * Estado actual del préstamo: PENDING, APPROVED o REJECTED.
     */
    private LoanStatus status;

    /**
     * Identificador del usuario propietario del préstamo.
     */
    private Long userId;
}

