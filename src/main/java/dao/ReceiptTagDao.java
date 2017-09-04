package dao;

import generated.tables.records.ReceiptTagsRecord;
import generated.tables.records.ReceiptsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.Map;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.RECEIPT_TAGS;

public class ReceiptTagDao {
    DSLContext dsl;

    public ReceiptTagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public void insert(String tag, Integer receiptId) {
        ReceiptTagsRecord receiptTagsRecord = dsl
                .insertInto(RECEIPT_TAGS, RECEIPT_TAGS.TAG, RECEIPT_TAGS.RECEIPT_ID)
                .values(tag, receiptId)
                .returning(RECEIPT_TAGS.ID)
                .fetchOne();

        checkState(receiptTagsRecord != null && receiptTagsRecord.getId() != null, "Insert failed");
    }

    public void delete(String tag, Integer receiptId) {
        dsl.delete(RECEIPT_TAGS)
                .where(RECEIPT_TAGS.TAG.eq(tag).and(RECEIPT_TAGS.RECEIPT_ID.eq(receiptId)))
                .execute();
    }

    public void toggle(String tag, Integer receiptId) {
        Boolean receiptTagDoesExist = dsl.fetchExists(RECEIPT_TAGS, RECEIPT_TAGS.TAG.eq(tag).and(RECEIPT_TAGS.RECEIPT_ID.eq(receiptId)));
        if (receiptTagDoesExist) {
            this.delete(tag, receiptId);
        } else {
            this.insert(tag, receiptId);
        }
    }

    public List<ReceiptsRecord> getAllReceiptsByTags(String tagName) {
        List<ReceiptsRecord> receiptTags = dsl.select().from(RECEIPTS).join(RECEIPT_TAGS).on(RECEIPT_TAGS.RECEIPT_ID.eq(RECEIPTS.ID).and(RECEIPT_TAGS.TAG.eq(tagName))).fetchInto(ReceiptsRecord.class);
        return receiptTags;

    }
}
