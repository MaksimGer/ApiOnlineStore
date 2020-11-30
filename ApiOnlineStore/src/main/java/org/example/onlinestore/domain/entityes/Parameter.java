package org.example.onlinestore.domain.entityes;

import org.example.onlinestore.domain.entityes.resources.CompositeKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(CompositeKey.class)
@Table(name = "Params")
public class Parameter implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Parameter parameter = (Parameter) o;
        return Objects.equals(product, parameter.product) &&
                Objects.equals(attribute, parameter.attribute) &&
                Objects.equals(value, parameter.value);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;

        hashCode = 31 * hashCode + product.hashCode();
        hashCode = 31 * hashCode + attribute.hashCode();
        hashCode = 31 * hashCode + value.hashCode();

        return hashCode;
    }
}