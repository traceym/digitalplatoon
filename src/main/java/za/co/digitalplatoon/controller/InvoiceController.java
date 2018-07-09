/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.digitalplatoon.controller;

import za.co.digitalplatoon.model.Invoice;;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import za.co.digitalplatoon.service.InvoiceService;
import za.co.digitalplatoon.util.CustomErrorType;

/**
 *
 * @author Mandla
 */
@RestController
@RequestMapping("/invoice")

public class InvoiceController {

    public static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    
    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = "/viewAllInvoices", produces =MediaType.APPLICATION_JSON_VALUE ,  method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> viewAllInvoices() {
        List<Invoice> invoices = invoiceService.viewAllInvoices();
        if (invoices.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",produces =MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.GET)
    public ResponseEntity<?> viewInvoice(@PathVariable("id") long id) {
        logger.info("Fetching Invoice with id {}", id);
        Invoice invoice = invoiceService.viewInvoice(id);
        if (invoice == null) {
            logger.error("Invoice with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Invoice with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<?> addInvoice(@RequestBody Invoice invoice, UriComponentsBuilder ucBuilder) {
        logger.info("Adding invoice : {}", invoice);

        invoiceService.addInvoice(invoice);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/invoice/").buildAndExpand(invoice.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

}
