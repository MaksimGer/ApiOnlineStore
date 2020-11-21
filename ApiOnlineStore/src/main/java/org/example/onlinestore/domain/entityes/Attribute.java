package org.example.onlinestore.domain.entityes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.onlinestore.domain.entityes.resolver.EntityIdResolver;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Attributes")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToMany(mappedBy = "attributes")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class
            , resolver= EntityIdResolver.class
            , scope=Attribute.class
            , property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Category> categories = new HashSet<>();

    //------------------------------------------------------------------------------------------

    public Attribute() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Category> getCategories() { return categories; }

    public void setCategories(Set<Category> categories) { this.categories = categories; }

    public void addCategory(Category category){
        categories.add(category);

        if(!category.getAttributes().contains(this)){
            category.addAttribute(this);
        }
    }

    public void removeCategory(Category category){
        categories.remove(category);
        if(category.getAttributes().contains(this)){
            category.removeAttribute(this);
        }
    }

    public void removeAllCategories(){
        List<Category> categories = new ArrayList<>(getCategories());
        for(Category category: categories){
            category.removeAttribute(this);
        }
        this.categories.clear();
    }
}
