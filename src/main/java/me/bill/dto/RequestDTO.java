package me.bill.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class RequestDTO {
    private String ip;
    private ZonedDateTime dateTime;
    private String url;
    private String currencyCode;
}
