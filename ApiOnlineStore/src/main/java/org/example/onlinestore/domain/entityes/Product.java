package org.example.onlinestore.domain.entityes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1189L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price = 0.0;

    @Column(name = "count")
    private Long count = (long) 0;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    // ------------------------------------------------------------------------------------------------

    public Product() { }

    public Long getId() { return this.id; }

    public String getName() { return name; }

    public Category getCategory() { return category; }

    public Double getPrice() { return price; }

    public Long getCount() { return count; }

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

    public void setPrice(Double price) {
        if(price > 0)
            this.price = price;
    }

    public void setCount(Long count) {
        if(count > 0)
            this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Product product = (Product) o;

        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(count, product.count) &&
                category.equals(product.category);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;

        hashCode = 31 * hashCode + id.hashCode();
        hashCode = 31 * hashCode + name.hashCode();
        hashCode = 31 * hashCode + price.hashCode();
        hashCode = 31 * hashCode + count.hashCode();
        hashCode = 31 * hashCode + category.hashCode();

        return hashCode;
    }
}
