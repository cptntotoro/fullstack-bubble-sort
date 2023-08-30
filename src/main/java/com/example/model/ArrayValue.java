package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "array_values")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArrayValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "array_id")
    private Array array;

    @NotNull
    @NotEmpty
    private Integer value;

    @Enumerated(EnumType.STRING)
    private Sort sort;

    public ArrayValue(Array array, Integer value, Sort sort) {
        this.array = array;
        this.value = value;
        this.sort = sort;
    }
}
