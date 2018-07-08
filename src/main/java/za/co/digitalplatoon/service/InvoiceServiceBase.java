/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.digitalplatoon.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.digitalplatoon.model.Invoice;
import za.co.digitalplatoon.model.LineItem;

/**
 *
 * @author Mandla
 */
@Service("invoiceService")
public class InvoiceServiceBase implements InvoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceServiceBase.class);
    private static List<Invoice> invoices;
    public static AtomicLong counter = new AtomicLong();

    static {
        invoices = populateDummyInvoices();
        LOG.debug("Calloing Populate Dummy Invoices");
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        if (invoice == null) {
            invoice = new Invoice();
        }
        invoice.setClient("Clinton Saxon");
        invoice.setId(1L);
        invoice.setInvoiceDate(new Date());
        invoice.setVatRate(15L);
        
        LineItem item = new LineItem();
        item.setId(1L);
        item.setDescription("Rugby Jersey");
        item.setQuantity(2L);
        item.setUnitPrice(new BigDecimal("15.75"));
        invoice.setItems((List<LineItem>) item);
        return invoice;
    }

    @Override
    public List<Invoice> viewAllInvoices() {
        return invoices;
    }

    @Override
    public Invoice viewInvoice(long id) {
        for (Invoice invoice : invoices) {
            if (invoice.getId() == id) {
                return invoice;
            }
        }
        return null;
    }

    private static List<Invoice> populateDummyInvoices() {
        System.out.println("@@@@@ Start putting data @@@@@ ");
        List<Invoice> invoiceList = new ArrayList<>();
        System.out.println("@@@@@ Creating list 1 @@@@@ ");
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setClient("Kobus Van Wyk");
        invoice.setInvoiceDate(new Date());
        invoice.setVatRate(15L);
        System.out.println("@@@@@ populating invoice " + invoice);
        List<LineItem> items = new ArrayList<>();
        LineItem lineItem = new LineItem();
        lineItem.setId(1L);
        lineItem.setDescription("Soccer Jersey");
        lineItem.setQuantity(1L);
        lineItem.setUnitPrice(new BigDecimal("250.25"));
        lineItem.setLineItemTotal(lineItem.getLineItemTotal());
        System.out.println("@@@@@@@ Line Item " + lineItem);
        items.add(lineItem);
//        invoice.setItems((List<LineItem>) items);
        System.out.println("@@@@@@@ Items " + items);
        invoice.setItems(items);
        System.out.println(" Invoice with  " + invoice.getItems().size() + " items");
        System.out.println(" @@@@@@@@@@ Finished creating @@@@@@@@@@");
//        invoiceList.add(invoice);
        invoiceList.addAll(Arrays.asList(invoice));
        System.out.println("@@@@@@@@@ Invoice List @@@@@@@@@ "+ invoiceList.size() + " Size");
        invoices.addAll(new ArrayList<> (invoiceList));
        System.out.println("@@@ addAll " + invoices);
//        list.addAll(Arrays.asList())

        Optional.ofNullable(invoices).ifPresent(invoices::addAll);
        System.out.println("@@@ addAll " + invoices);
        
//        Invoice invoice1 = new Invoice();
//        invoice1.setId(2L);
//        invoice1.setClient("Richard Banson");
//        invoice1.setInvoiceDate(new Date());
//        invoice1.setVatRate(15L);
//        List<LineItem> item2 = new ArrayList<>();
//        LineItem lineItem2 = new LineItem();
//        lineItem2.setId(2L);
//        lineItem2.setDescription("T-shirt");
//        lineItem2.setQuantity(6L);
//        lineItem2.setUnitPrice(new BigDecimal("780.25"));
//        lineItem2.setLineItemTotal(lineItem.getLineItemTotal());
//        item2.add(lineItem2);
//        invoice1.setItems(item2);
//        System.out.println(" Finished creating the second one");
//        
//        Invoice invoice3 = new Invoice();
//        invoice3.setId(3L);
//        invoice3.setClient("Thabo Dlamini");
//        invoice3.setInvoiceDate(new Date());
//        invoice3.setVatRate(15L);
//        List<LineItem> item3 = new ArrayList<>();
//        lineItem = new LineItem();
//        lineItem.setId(3L);
//        lineItem.setDescription("Hockey stick");
//        lineItem.setQuantity(2L);
//        lineItem.setUnitPrice(new BigDecimal("1050.25"));
//        lineItem.setLineItemTotal(lineItem.getLineItemTotal());
//        item3.add(lineItem);
//        invoice.setItems(item3);
//        System.out.println(" Finished creating the third one");
//        
//        Invoice invoice4 = new Invoice();
//        invoice4.setId(4L);
//        invoice4.setClient("Ryan Chetty");
//        invoice4.setInvoiceDate(new Date());
//        invoice4.setVatRate(15L);
//        List<LineItem> item4 = new ArrayList<>();
//        LineItem lineItem4 = new LineItem();
//        lineItem4.setId(4L);
//        lineItem4.setDescription("Cricket Caps");
//        lineItem4.setQuantity(14L);
//        lineItem4.setUnitPrice(new BigDecimal("650.25"));
//        item4.add(lineItem4);
//        invoice4.setItems(item4);
//        list.add(invoice);
//        System.out.println("@@@ list" + list);
        
        System.out.println("@@@ invoices" + invoices);
        return invoices;
    }
}
