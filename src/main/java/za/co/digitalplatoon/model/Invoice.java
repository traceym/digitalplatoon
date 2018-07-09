/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.digitalplatoon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.stream.Collectors;

/**
 *
 * @author Mandla
 */
@Entity
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String client;
    private Long vatRate;
    private Date invoiceDate;
    private List<LineItem> items;
    public static final BigDecimal VAT = new BigDecimal("15");
    public static final BigDecimal HUNDRED = new BigDecimal("100");
    private BigDecimal vat;
    private BigDecimal subTotal;
    private BigDecimal total;
    

    public Invoice() {
    }

    public Invoice(Long id, String client, Long vatRate, Date invoiceDate, List<LineItem> items) {
        this.id = id;
        this.client = client;
        this.vatRate = vatRate;
        this.invoiceDate = invoiceDate;
        this.items = items;
    }

    public Invoice(Invoice copyFrom) {
        this.id = copyFrom.id;
        this.client = copyFrom.client;
        this.vatRate = copyFrom.vatRate;
        this.invoiceDate = copyFrom.invoiceDate;
        this.items = new ArrayList<>();
        copyFrom.getItems().forEach((lineItem) -> {
            items.add(new LineItem(lineItem));
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public BigDecimal getSubTotal() {
        return getItems().stream()
                .<BigDecimal>map(invoice -> invoice
                .getUnitPrice()
                .multiply(BigDecimal.valueOf(invoice.getQuantity())))
                .collect(Collectors.reducing(
                        BigDecimal.ZERO,
                        (sum, elem) -> sum.add(elem)));
    }

    public BigDecimal getVatAmount() {
        return VAT.multiply(getSubTotal().divide(HUNDRED));
    }

    public void setVatAmount(BigDecimal vat) {
        this.vat = vat;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        BigDecimal vatAmount = getSubTotal().divide(HUNDRED).multiply(VAT);
        BigDecimal totalAmount = getSubTotal().add(vatAmount);
        return totalAmount;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.client);
        hash = 53 * hash + Objects.hashCode(this.vatRate);
        hash = 53 * hash + Objects.hashCode(this.invoiceDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Invoice other = (Invoice) obj;
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.vatRate, other.vatRate)) {
            return false;
        }
        if (!Objects.equals(this.invoiceDate, other.invoiceDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", client=" + client + ", vatRate=" + vatRate + ", invoiceDate=" + invoiceDate + '}';
    }

}
