package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.ReceiptTagsRecord;


public class ReceiptTagResponse {
    @JsonProperty
    Integer receipt_id;

    @JsonProperty
    String tag;

    public ReceiptTagResponse(ReceiptTagsRecord dbRecord) {
        this.receipt_id = dbRecord.getReceiptId();
        this.tag = dbRecord.getTag();
    }
}
