package io.agus.learning.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
// @JsonIdentityInfo(
// generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id"
// )
@Setter
@Getter
@NoArgsConstructor
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
}
