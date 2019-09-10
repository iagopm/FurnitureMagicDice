package com.example.laleydeldadoappoficial.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dices")
public class Dice {
    @PrimaryKey
    @NonNull
    private Integer id;

    public Dice(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public String toString() {
        return Integer.toString(id);
    }
}
