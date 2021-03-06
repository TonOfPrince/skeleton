/**
 * This class is generated by jOOQ
 */
package generated;


import generated.tables.ReceiptTags;
import generated.tables.Receipts;
import generated.tables.records.ReceiptTagsRecord;
import generated.tables.records.ReceiptsRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>public</code> 
 * schema
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final Identity<ReceiptsRecord, Integer> IDENTITY_RECEIPTS = Identities0.IDENTITY_RECEIPTS;
	public static final Identity<ReceiptTagsRecord, Integer> IDENTITY_RECEIPT_TAGS = Identities0.IDENTITY_RECEIPT_TAGS;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<ReceiptsRecord> CONSTRAINT_F = UniqueKeys0.CONSTRAINT_F;
	public static final UniqueKey<ReceiptTagsRecord> CONSTRAINT_3 = UniqueKeys0.CONSTRAINT_3;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------

	public static final ForeignKey<ReceiptTagsRecord, ReceiptsRecord> CONSTRAINT_3A = ForeignKeys0.CONSTRAINT_3A;

	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends AbstractKeys {
		public static Identity<ReceiptsRecord, Integer> IDENTITY_RECEIPTS = createIdentity(Receipts.RECEIPTS, Receipts.RECEIPTS.ID);
		public static Identity<ReceiptTagsRecord, Integer> IDENTITY_RECEIPT_TAGS = createIdentity(ReceiptTags.RECEIPT_TAGS, ReceiptTags.RECEIPT_TAGS.ID);
	}

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<ReceiptsRecord> CONSTRAINT_F = createUniqueKey(Receipts.RECEIPTS, Receipts.RECEIPTS.ID);
		public static final UniqueKey<ReceiptTagsRecord> CONSTRAINT_3 = createUniqueKey(ReceiptTags.RECEIPT_TAGS, ReceiptTags.RECEIPT_TAGS.ID);
	}

	private static class ForeignKeys0 extends AbstractKeys {
		public static final ForeignKey<ReceiptTagsRecord, ReceiptsRecord> CONSTRAINT_3A = createForeignKey(generated.Keys.CONSTRAINT_F, ReceiptTags.RECEIPT_TAGS, ReceiptTags.RECEIPT_TAGS.RECEIPT_ID);
	}
}
