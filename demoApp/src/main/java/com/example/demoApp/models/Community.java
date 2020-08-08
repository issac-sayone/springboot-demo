package com.example.demoApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "dashboard_community")
public class Community extends BaseModel implements Serializable {

    @Column(name = "name")
    private String name;


    public Community() {
    }

    public Community(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
