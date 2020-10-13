package com.example.test.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cities")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cityId;

    @NotNull
    @NotBlank
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @Min(1)
    private long area;

    @NotNull
    @Min(1)
    private long population;

    @NotNull
    @Min(1)
    private long gdp;

    @NotNull
    @NotBlank
    private String description;
}
