package com.devtyagi.maalgaadi.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dealers")
@AllArgsConstructor
@NoArgsConstructor
public class Dealer {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String dealerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    private String natureOfMaterial;

    private Double weightOfMaterial;

    private Integer quantity;

    private String city;

    private String state;

}