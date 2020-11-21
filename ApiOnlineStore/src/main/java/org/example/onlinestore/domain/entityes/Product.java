package org.example.onlinestore.domain.entityes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Category_id")
    private Category category;

    // ------------------------------------------------------------------------------------------------

    public Product() { }

    public Long getId() { return this.id; }

    public String getName() { return name; }

    public Category getCategory() { return category; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setCategory(Category category) { this.category = category; }
}
