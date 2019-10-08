package com.maup.lesson20191008.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "goods")
public class Good extends SystemDictionary {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;
}
