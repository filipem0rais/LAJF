//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 02.05.2023
// 


package ch.es.pl.quotes.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @TableGenerator(name = "genUsers",
            table = "idUsers",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genUsers")
    @Column(name = "idUser", nullable = false)
    private Integer idUser;

    @Column(name = "useName", nullable = false)
    private String useName;

    @Column(name = "useLastName", nullable = false)
    private String useLastName;

    @Column(name = "useEmail", nullable = false, unique = true)
    private String useEmail;

    @Column(name = "usePassword", nullable = false)
    private String usePassword;

    @Column(name = "useCredit", nullable = false)
    private double useCredit;


    // getters and setters


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getUseLastName() {
        return useLastName;
    }

    public void setUseLastName(String useLastName) {
        this.useLastName = useLastName;
    }

    public String getUseEmail() {
        return useEmail;
    }

    public void setUseEmail(String useEmail) {
        this.useEmail = useEmail;
    }

    public String getUsePassword() {
        return usePassword;
    }

    public void setUsePassword(String usePassword) {
        this.usePassword = usePassword;
    }

    public double getUseCredit() {
        return useCredit;
    }

    public void setUseCredit(double useCredit) {
        this.useCredit = useCredit;
    }
}
