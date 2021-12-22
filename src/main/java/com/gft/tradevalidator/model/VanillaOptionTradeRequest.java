package com.gft.tradevalidator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.tradevalidator.validators.DateBoundaryConstraint;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Data
@DateBoundaryConstraint.List(
        {@DateBoundaryConstraint(field = "deliveryDate", fieldMatch = "expiryDate", message = "Expiry date shall be before delivery date"),
        @DateBoundaryConstraint(field = "deliveryDate", fieldMatch = "premiumDate", message = "Premium date shall be before delivery date"),
        @DateBoundaryConstraint(field = "excerciseStartDate", fieldMatch = "tradeDate", message = "excerciseStartDate has to be after the trade date"),
        @DateBoundaryConstraint(field = "expiryDate", fieldMatch = "excerciseStartDate", message = "excerciseStartDate has to be before the expiry date")
        })
public class VanillaOptionTradeRequest extends BaseTradeRequest {
    @Pattern(regexp = "AMERICAN|EUROPEAN",message="Only EUROPEAN or AMERICAN value of style is allowed")
    private String style;
    @NotNull(message = "Strategy can not be null")
    @Pattern(regexp = "CALL", message="Only CALL value is allowed in strategy")
    String strategy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "deliveryDate field can not be null")
    private LocalDate deliveryDate;
    @NotNull(message = "expiryDate field can not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
    @NotNull(message = "excerciseStartDate field can not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate excerciseStartDate;
    @NotNull(message = "payCcy field can not be null")
    private String payCcy;
    private double premium;
    @NotNull(message = "Premium Ccy field can not be null")
    private String premiumCcy;
    @NotNull(message = "Premium Type field can not be null")
    private String premiumType;
    @NotNull(message = "Premium Date field can not be null")
    private LocalDate premiumDate;


}
