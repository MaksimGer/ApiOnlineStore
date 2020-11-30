/*package org.example.onlinestore.domain.entityes.resources;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Product;

import java.io.Serializable;
import java.util.Objects;

public class CompositeKey implements Serializable {
    private Product product;
    private Attribute attribute;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        CompositeKey key = (CompositeKey) o;
        return Objects.equals(product, key.product) &&
                Objects.equals(attribute, key.attribute);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;

        hashCode = 31 * hashCode + product.hashCode();
        hashCode = 31 * hashCode + attribute.hashCode();

        return hashCode;
    }
}*/
