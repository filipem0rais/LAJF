//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 


package ch.es.pl.quotes.api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class ItemEntity {
    @TableGenerator(name = "genItems",
            table = "iditems",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 200,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genItems")
    @Column(name = "idItem", nullable = false)
    private Integer idItem;

    @Column(name = "iteDescription", nullable = false)
    private String iteDescription;

    @Column(name = "iteInitialValue", nullable = false)
    private BigDecimal iteInitialValue;

    @Column(name = "iteOnSale", nullable = false)
    private Boolean iteOnSale;

    @Column(name = "iteDatePublication", nullable = false)
    private LocalDateTime iteDatePublication;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCategory", nullable = false)
    private CategoryEntity category;

    @Column(name = "iteName", nullable = false)
    private String iteName;

    @Column(name = "iteState", nullable = false)
    private String iteState;

    @Column(name = "itePicture", nullable = false)
    private String itePicture;

    @Column(name = "itePickedUp", nullable = false)
    private Boolean itePickedUp;

    // getters and setters


    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getIteDescription() {
        return iteDescription;
    }

    public void setIteDescription(String iteDescription) {
        this.iteDescription = iteDescription;
    }

    public BigDecimal getIteInitialValue() {
        return iteInitialValue;
    }

    public void setIteInitialValue(BigDecimal iteInitialValue) {
        this.iteInitialValue = iteInitialValue;
    }

    public Boolean getIteOnSale() {
        return iteOnSale;
    }

    public void setIteOnSale(Boolean iteOnSale) {
        this.iteOnSale = iteOnSale;
    }

    public LocalDateTime getIteDatePublication() {
        return iteDatePublication;
    }

    public void setIteDatePublication(LocalDateTime iteDatePublication) {
        this.iteDatePublication = iteDatePublication;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getIteName() {
        return iteName;
    }

    public void setIteName(String iteName) {
        this.iteName = iteName;
    }

    public String getIteState() {
        return iteState;
    }

    public void setIteState(String iteState) {
        this.iteState = iteState;
    }

    public String getItePicture() {
        return itePicture;
    }

    public void setItePicture(String itePicture) {
        this.itePicture = itePicture;
    }

    public Boolean getItePickedUp() {
        return itePickedUp;
    }

    public void setItePickedUp(Boolean itePickedUp) {
        this.itePickedUp = itePickedUp;
    }
}

