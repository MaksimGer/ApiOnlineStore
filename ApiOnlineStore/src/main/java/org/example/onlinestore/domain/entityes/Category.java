package org.example.onlinestore.domain.entityes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.onlinestore.domain.entityes.resolver.EntityIdResolver;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class
            ,resolver = EntityIdResolver.class
            , scope = Category.class
            ,property = "id")
    private Set<Attribute> attributes = new HashSet<>();

    //------------------------------------------------------------------------------------------

    public Category() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Attribute> getAttributes() { return attributes; }

    public void setAttributes(Set<Attribute> attributes) { this.attributes = attributes; }

    public void addAttribute(Attribute attribute){
        attributes.add(attribute);

        if(!attribute.getCategories().contains(this)) {
            attribute.addCategory(this);
        }
    }

    public void removeAttribute(Attribute attribute){
        attributes.remove(attribute);
        if(attribute.getCategories().contains(this)){
            attribute.removeCategory(this);
        }
    }

    public void removeAllAttributes(){
        attributes.clear();
    }
}
