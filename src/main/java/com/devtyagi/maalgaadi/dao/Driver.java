package com.devtyagi.maalgaadi.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "drivers")
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String driverId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    private Integer age;

    private String truckNumber;

    private Integer truckCapacity;

    private String transporterName;

    private Double drivingExperience;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "driverId")
    private List<Route> interestedRoutes;

}