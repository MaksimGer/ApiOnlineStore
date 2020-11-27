package org.example.onlinestore.domain.entityes;

import org.example.onlinestore.domain.entityes.resources.CompositeKey;

import javax.persistence.*;

@Entity
@IdClass(CompositeKey.class)
@Table(name = "Params")
public class Parameter {
    @Id
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne()
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    private String value;

    // ------------------------------------------------------------------------------------------------
    public Parameter() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}