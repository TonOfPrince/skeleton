package controllers;

import dao.ReceiptTagDao;
import dao.ReceiptDao;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class ReceiptTagControllerTest {

    public static org.jooq.Configuration setupJooq() {
        // For now we are just going to use an H2 Database.  We'll upgrade to mysql later
        // This connection string tells H2 to initialize itself with our schema.sql before allowing connections
        final String jdbcUrl = "jdbc:h2:mem:test;MODE=MySQL;INIT=RUNSCRIPT from 'classpath:schema.sql'";
        JdbcConnectionPool cp = JdbcConnectionPool.create(jdbcUrl, "sa", "sa");

        // This sets up jooq to talk to whatever database we are using.
        org.jooq.Configuration jooqConfig = new DefaultConfiguration();
        jooqConfig.set(SQLDialect.MYSQL);   // Lets stick to using MySQL (H2 is OK with this!)
        jooqConfig.set(cp);
        return jooqConfig;
    }

    @Test
    public void testEmptyInitialReceiptTag() {
        org.jooq.Configuration jooqConfig = setupJooq();
        ReceiptTagDao receiptTagDao = new ReceiptTagDao(jooqConfig);
        ReceiptDao receiptDao = new ReceiptDao(jooqConfig);
        ReceiptTagController receiptTagController = new ReceiptTagController(receiptTagDao);
        ReceiptController receiptController = new ReceiptController(receiptDao);

        assertThat(receiptTagController.getReceipts("hello"), empty());
    }
}
