package za.co.digitalplatoon;
 

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import za.co.digitalplatoon.model.Invoice;
import za.co.digitalplatoon.model.LineItem;
 

public class InvoiceClientTest {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/invoice";
     
    /* viewAllInvoices */
    @SuppressWarnings("unchecked")
    private static void viewAllInvoices(){
        System.out.println("Testing ViewAllInvoices API-----------");
        RestTemplate restTemplate = new RestTemplate();
        List<Object> invoices = restTemplate.getForObject(REST_SERVICE_URI+"/viewAllInvoices/", List.class);
        System.out.println("@@@@@ Creating list 1 @@@@@ ");
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setClient("Kobus Van Wyk");
        invoice.setInvoiceDate(new Date());
        invoice.setVatRate(invoice.getVatRate());
        List<LineItem> items = new ArrayList<>();
        LineItem lineItem = new LineItem();
        lineItem.setId(1L);
        lineItem.setDescription("Soccer Jersey");
        lineItem.setQuantity(1L);
        lineItem.setUnitPrice(new BigDecimal("250.25"));
        lineItem.setLineItemTotal(lineItem.getLineItemTotal());
        items.add(lineItem);
        invoice.setItems(items);
        invoices.add(invoice);
        if(invoices!=null){
            invoices.forEach((next) -> {
                System.out.println(next);
            });          
        }else{
            System.out.println("No invoice exist----------");
        }
    }
     
    /* viewInvoice */
    private static void viewInvoice(){
        System.out.println("Testing viewInvoice API----------");
        RestTemplate restTemplate = new RestTemplate();
        List<LineItem> itemList = new ArrayList<>();
        Invoice invoice = restTemplate.getForObject(REST_SERVICE_URI+"/1", Invoice.class);
        invoice.setClient("John Cousin");
        invoice.setId(4L);
        invoice.setInvoiceDate(new Date());
        invoice.setVatRate(15L);
        LineItem lineItem = new LineItem();
        lineItem.setDescription("Golf t-shirt");
        lineItem.setId(4L);
        lineItem.setQuantity(8L);
        lineItem.setUnitPrice(new BigDecimal("15"));
        lineItem.setLineItemTotal(new BigDecimal("150.50"));
        itemList.add(lineItem);
        invoice.setItems(itemList);  
        System.out.println(invoice);
    }
     
    /* addInvoice */
    private static void addInvoice() {
        System.out.println("Testing addInvoice ----------");
        RestTemplate restTemplate = new RestTemplate();
        List<LineItem> itemList = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setClient("John Cousin");
        invoice.setId(4L);
        invoice.setInvoiceDate(new Date());
        invoice.setVatRate(15L);
        LineItem lineItem = new LineItem();
        lineItem.setDescription("Golf t-shirt");
        lineItem.setId(4L);
        lineItem.setQuantity(8L);
        lineItem.setUnitPrice(new BigDecimal("15"));
        lineItem.setLineItemTotal(new BigDecimal("150.50"));
        itemList.add(lineItem);
        invoice.setItems(itemList);           
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/", invoice, Invoice.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    public static void main(String args[]){
        viewInvoice();
        viewAllInvoices();
        addInvoice();
    }
}