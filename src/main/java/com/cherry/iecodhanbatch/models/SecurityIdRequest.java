package com.cherry.iecodhanbatch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;


@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityIdRequest {
    private String semExmExchangeId;
    private String semTradingSymbol;
}
