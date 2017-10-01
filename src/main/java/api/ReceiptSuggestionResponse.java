package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * Represents the result of an OCR parse
 */
public class ReceiptSuggestionResponse {
    @JsonProperty
    public final String merchantName;

    @JsonProperty
    public final BigDecimal amount;

    @JsonProperty
    public final Integer x1;

    @JsonProperty
    public final Integer y1;

    @JsonProperty
    public final Integer x2;

    @JsonProperty
    public final Integer y2;


    public ReceiptSuggestionResponse(String merchantName, BigDecimal amount, Integer x1, Integer y1, Integer x2, Integer y2) {
        this.merchantName = merchantName;
        this.amount = amount;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}