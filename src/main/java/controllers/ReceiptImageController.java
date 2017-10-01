package controllers;

import api.ReceiptSuggestionResponse;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Collections;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.hibernate.validator.constraints.NotEmpty;

import static java.lang.System.out;

@Path("/images")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptImageController {
    private final AnnotateImageRequest.Builder requestBuilder;

    public ReceiptImageController() {
        // DOCUMENT_TEXT_DETECTION is not the best or only OCR method available
        Feature ocrFeature = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        this.requestBuilder = AnnotateImageRequest.newBuilder().addFeatures(ocrFeature);

    }

    /**
     * This borrows heavily from the Google Vision API Docs.  See:
     * https://cloud.google.com/vision/docs/detecting-fulltext
     *
     * YOU SHOULD MODIFY THIS METHOD TO RETURN A ReceiptSuggestionResponse:
     *
     * public class ReceiptSuggestionResponse {
     *     String merchantName;
     *     String amount;
     * }
     */
    @POST
    public ReceiptSuggestionResponse parseReceipt(@NotEmpty String base64EncodedImage) throws Exception {
        Image img = Image.newBuilder().setContent(ByteString.copyFrom(Base64.getDecoder().decode(base64EncodedImage))).build();
        AnnotateImageRequest request = this.requestBuilder.setImage(img).build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse responses = client.batchAnnotateImages(Collections.singletonList(request));
            AnnotateImageResponse res = responses.getResponses(0);

            String merchantName = null;
            BigDecimal amount = null;
            Integer count = 0;
            Integer x1 = Integer.MAX_VALUE;
            Integer y1 = Integer.MAX_VALUE;
            Integer x2 = 0;
            Integer y2 = 0;
            // Your Algo Here!!
            // Sort text annotations by bounding polygon.  Top-most non-decimal text is the merchant
            // bottom-most decimal text is the total amount
            for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                String text = annotation.getDescription();
                String noSpace = text.replaceAll("\\s+","");

                if (count == 0) {
                    for (int i=0; i<4; i++) {
                        Integer x = annotation.getBoundingPoly().getVertices(i).getX();
                        Integer y = annotation.getBoundingPoly().getVertices(i).getY();
                        if (x < x1) {
                            x1 = x;
                        }
                        if (x > x2) {
                            x2 = x;
                        }
                        if (y < y1) {
                            y1 = y;
                        }
                        if (y > y2) {
                            y2 = y;
                        }
                    }
                } else {
                    if (noSpace.matches("\\d+(\\.\\d{1,2})?")) {
                        out.println("decimal");
                        amount = new BigDecimal(noSpace);
                    } else if (merchantName == null) {
                        out.println("merchant");
                        merchantName = text;
                    }
                }
                out.printf("Merchant: %s\n", merchantName);
                out.printf("Text: %s\n", text);
                out.printf("Position : %s\n", annotation.getBoundingPoly());
                count += 1;
            }

            //TextAnnotation fullTextAnnotation = res.getFullTextAnnotation();
            return new ReceiptSuggestionResponse(merchantName, amount, x1, y1, x2, y2);
        }
    }
}