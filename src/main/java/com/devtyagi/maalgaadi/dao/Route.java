package com.devtyagi.maalgaadi.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "routes")
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String routeId;

    @NotBlank(message = "From City must not be blank!")
    private String fromCity;

    @NotBlank(message = "From State must not be blank!")
    private String fromState;

    @NotBlank(message = "To City must not be blank!")
    private String toCity;

    @NotBlank(message = "To State must not be blank!")
    private String toState;

}
