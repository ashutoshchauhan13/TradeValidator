
package com.gft.tradevalidator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.tradevalidator.validators.DateBoundaryConstraint;
import com.gft.tradevalidator.validators.NonWorkingDayConstraint;
import com.gft.tradevalidator.validators.ValidCurrencyCode;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@DateBoundaryConstraint.List(
        {@DateBoundaryConstraint(field = "valueDate", fieldMatch = "tradeDate", message = "Value date cannot be before trade date")})
public abstract class BaseTradeRequest {

    @NotNull(message = "Customer field can not be null")
    @Pattern(regexp = "YODA1|YODA2", message = "Customer filed can be only YODA1 or YODA2")
    private String customer;
    @ValidCurrencyCode
    private String ccyPair;
    @NotNull(message = "Type field can not be null")
    private String type;
    @NotNull(message = "Direction field can not be null")
    private String direction;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Trade Date field can not be null")
    private LocalDate tradeDate;
    @NotNull(message = "amount1 field can not be null")
    private BigDecimal amount1;
    @NotNull(message = "amount2 field can not be null")
    private BigDecimal amount2;
    @NotNull(message = "rate field can not be null")
    private Double rate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Value Date field can not be null")
    @NonWorkingDayConstraint
    private LocalDate valueDate;
    @NotNull(message = "LegalEntity field can not be null")
    @Pattern(regexp = "UBS AG", message = "This value of legalEntity is not supported")
    private String legalEntity;
    @NotNull(message = "Trader field can not be null")
    private String trader;
}
