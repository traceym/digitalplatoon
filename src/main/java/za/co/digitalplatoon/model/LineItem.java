/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.digitalplatoon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Mandla
 */
//@Entity
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal lineItemTotal;

    public LineItem() {
    }

    public LineItem(LineItem copyFrom) {
        this.id = copyFrom.id;
        this.description = copyFrom.description;
        this.unitPrice = copyFrom.unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setLineItemTotal(BigDecimal lineItemTotal) {
        this.lineItemTotal = lineItemTotal;
    }

    public BigDecimal getLineItemTotal() {  
      return getUnitPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.quantity);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.unitPrice);
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
        final LineItem other = (LineItem) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.unitPrice, other.unitPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LineItem{" + "id=" + id + ", quantity=" + quantity + ", description=" + description + ", unitPrice=" + unitPrice + '}';
    }

}
