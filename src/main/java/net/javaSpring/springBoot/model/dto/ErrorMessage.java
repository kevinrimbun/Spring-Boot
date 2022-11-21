package net.javaSpring.springBoot.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage<T> {
    private Integer status;
    private LocalDateTime time;
    private String message;
    private T errors;
}
