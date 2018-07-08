/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.digitalplatoon.service;


import java.util.List;
import za.co.digitalplatoon.model.Invoice;

/**
 *
 * @author Mandla
 */
public interface InvoiceService {

    Invoice addInvoice(Invoice invoice);

    List<Invoice> viewAllInvoices();

    Invoice viewInvoice(long id);
}
