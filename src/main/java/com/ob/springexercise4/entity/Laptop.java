package com.ob.springexercise4.entity;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Laptop entity represents a model of laptop with different properties")
public class Laptop {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ApiModelProperty("Manufacturing company")
    private String brand;

    @ApiModelProperty("Processor model")
    private String processor;

    @ApiModelProperty("Capacity of ram memory")
    private String memory;

    private String disk;

    private boolean integratedGraphics;

    public Laptop(String brand, String processor, String memory, String disk, boolean integratedGraphics) {
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.disk = disk;
        this.integratedGraphics = integratedGraphics;
    }
}
