package org.example.onlinestore.domain.entityes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.onlinestore.domain.entityes.resolver.EntityIdResolver;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class
            ,resolver = EntityIdResolver.class
            , scope = Category.class
            ,property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Attribute> attributes = new HashSet<>();

    @OneToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class
            ,resolver = EntityIdResolver.class
            ,scope = Category.class
            ,property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Product> products = new HashSet<>();

//------------------------------------------------------------------------------------------

    public Category() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Attribute> getAttributes() { return attributes; }

    public void setAttributes(Set<Attribute> attributes) { this.attributes = attributes; }

    public boolean addAttribute(Attribute attribute){
        boolean isAdded = attributes.add(attribute);

        if(!attribute.getCategories().contains(this)) {
            attribute.addCategory(this);
        }

        return isAdded;
    }

    public boolean addProduct(Product product){
        boolean isAdded = products.add(product);

        if(!product.getCategory().equals(this)) {
            product.setCategory(this);
        }

        return isAdded;
    }


    public void removeAttribute(Attribute attribute){
        attributes.remove(attribute);
        if(attribute.getCategories().contains(this)){
            attribute.removeCategory(this);
        }
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public void removeAllAttributes(){
        attributes.clear();
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void removeAllProducts() { products.clear(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;

        hashCode = 31 * hashCode + (int)(id^(id>>>32));
        hashCode = 31 * hashCode + name.hashCode();

        return hashCode;
    }
}
