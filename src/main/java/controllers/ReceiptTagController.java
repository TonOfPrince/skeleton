package controllers;

import api.CreateReceiptTagRequest;
import api.ReceiptResponse;
import dao.ReceiptTagDao;
import generated.tables.records.ReceiptsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags/{tag}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptTagController {
    final ReceiptTagDao receiptTags;

    public ReceiptTagController(ReceiptTagDao receiptTags) {
        this.receiptTags = receiptTags;
    }

    @POST
    public void toggleTag(@PathParam("tag") String tagName, @NotNull CreateReceiptTagRequest receiptTag) {
        receiptTags.toggle(tagName, receiptTag.receipt_id);
    }

    @GET
    public List<ReceiptResponse> getReceipts(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = receiptTags.getAllReceiptsByTags(tagName);
        System.out.println(receiptRecords);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }
}
