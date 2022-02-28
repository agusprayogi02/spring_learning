package io.agus.learning.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tbl_categories")
@Setter
@Getter
@NoArgsConstructor
public class Category extends BaseEntity<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nama harus di Isi!")
    @Column(unique = true, length = 100)
    private String name;
}
