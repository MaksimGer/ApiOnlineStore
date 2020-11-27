package org.example.onlinestore.domain.entityes.resources;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Product;

import java.io.Serializable;

public class CompositeKey implements Serializable {
    private Product product;
    private Attribute attribute;
}
