package controllers;

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

    @PUT
    public void toggleTag(@PathParam("tag") String tagName, Integer receiptTag) {
        receiptTags.toggle(tagName, receiptTag);
    }

    @GET
    public List<ReceiptResponse> getReceipts(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = receiptTags.getAllReceiptsByTags(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }
}
