package me.bill.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {

    private Integer code;
    private String message;

}
