package com.easyept.CrmForWork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long factoryId;
    @NotBlank
    private String factoryName;
    @NotBlank
    private String townName;

    public Factory(@NotBlank String factoryName, @NotBlank String townName) {
        this.factoryName = factoryName;
        this.townName = townName;
    }

    //lombok getters and setters
}
