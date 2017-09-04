package api;

import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;

import javax.validation.Validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class CreateReceiptTagRequestTest {
    private final Validator validator = Validators.newValidator();


    @Test
    public void testMissingReceiptId() {
        CreateReceiptTagRequest receiptTag = new CreateReceiptTagRequest();

        assertThat(validator.validate(receiptTag), empty());
    }

}
