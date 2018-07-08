/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.digitalplatoon.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mandla
 */
public class InvoiceResponse {

    private Invoice invoice;
    private List<String> errors = new ArrayList<>();

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public boolean isError() {
        return !(errors == null || errors.isEmpty());
    }
}
