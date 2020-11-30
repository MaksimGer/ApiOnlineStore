package org.example.onlinestore.domain.entityes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.onlinestore.domain.entityes.resolver.EntityIdResolver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Attributes")
public class Attribute implements Serializable {
    private static final long serialVersionUID = 1188L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
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

    public boolean addCategory(Category category){
        boolean isAdded = categories.add(category);

        if(!category.getAttributes().contains(this)){
            category.addAttribute(this);
        }

        return isAdded;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Attribute attribute = (Attribute) o;
        return Objects.equals(id, attribute.id) &&
                Objects.equals(name, attribute.name);
    }

    @Override
    public int hashCode() {
        int hashCode = 23;

        hashCode = 31 * hashCode + (int)(id^(id>>>32));
        hashCode = 31 * hashCode + name.hashCode();

        return hashCode;
    }
}
