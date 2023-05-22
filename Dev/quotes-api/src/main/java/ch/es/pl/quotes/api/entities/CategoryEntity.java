//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 


package ch.es.pl.quotes.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory", nullable = false)
    private Integer idCategory;

    @Column(name = "catName", nullable = false)
    private String catName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catParent")
    private CategoryEntity catParent;

    // getters and setters

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public CategoryEntity getCatParent() {
        return catParent;
    }

    public void setCatParent(CategoryEntity catParent) {
        this.catParent = catParent;
    }
}
