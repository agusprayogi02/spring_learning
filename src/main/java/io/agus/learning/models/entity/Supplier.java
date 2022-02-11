package io.agus.learning.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_suppliers")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 150)
    private String name;

    @Column(name = "alamat", nullable = false, length = 200)
    private String address;

    @Column(unique = true, length = 150)
    private String email;

    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products;

    public String getAddress() {
        return address;
    }

    public Supplier(Long id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Supplier() {

    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlamat(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
