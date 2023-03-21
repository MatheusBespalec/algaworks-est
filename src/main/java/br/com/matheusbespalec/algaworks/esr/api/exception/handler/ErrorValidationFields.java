package br.com.matheusbespalec.algaworks.esr.api.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorValidationFields {
    private LocalDateTime timestamps = LocalDateTime.now();
    private Integer status;
    private String message;
    private List<Field> fields;

    @AllArgsConstructor
    @Getter
    public static class Field {
        private String field;
        private String validationError;
    }
}
