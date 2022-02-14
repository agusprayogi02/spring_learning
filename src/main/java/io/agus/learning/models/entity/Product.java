package io.agus.learning.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_product")
// @JsonIdentityInfo(
// generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id"
// )
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty(message = "Nama harus diisi!")
  @Column(length = 100)
  private String name;

  @NotEmpty(message = "Deskripsi harus diisi!")
  @Column(length = 500)
  private String description;

  @Column(nullable = false)
  private double price;

  @ManyToOne
  private Category category;

  @ManyToMany
  @JoinTable(name = "tbl_products_suppliers", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
  // @JsonManagedReference
  private Set<Supplier> suppliers;

  public Product() {
  }

  public Product(long id, String name, String description, double price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public Product(long id, String name, String description, double price, Category category, Set<Supplier> suppliers) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.category = category;
    this.suppliers = suppliers;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getPrice() {
    return price;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Set<Supplier> getSuppliers() {
    return suppliers;
  }

  public void setSuppliers(Set<Supplier> suppliers) {
    this.suppliers = suppliers;
  }
}
