package org.example.onlinestore.domain.entityes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.onlinestore.domain.entityes.resolver.EntityIdResolver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1188L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    // ------------------------------------------------------------------------------------------------

    public Product() { }

    public Long getId() { return this.id; }

    public String getName() { return name; }

    public Category getCategory() { return category; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setCategory(Category category) {
        if(this.category != null && !this.category.equals(category)){
            this.category.removeProduct(this);
        }

        if(category != null){
            this.category = category;

            category.addProduct(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Product product = (Product) o;

        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                category.equals(product.category);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;

        hashCode = 31 * hashCode + (int)(id^(id>>>32));
        hashCode = 31 * hashCode + name.hashCode();
        hashCode = 31 * hashCode + category.hashCode();

        return hashCode;
    }
}
