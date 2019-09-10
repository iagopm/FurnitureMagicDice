package com.example.laleydeldadoappoficial.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.laleydeldadoappoficial.pojo.Dice;

import java.util.List;

@Dao
public interface DiceDAO {
    @Insert
    @Transaction
    void insert(Dice... dices);

    @Delete
    void delete(Dice dice);

    @Query("select * from dices")
    List<Dice> findAll();

    @Query("select* from dices where id=:id")
    Dice findById(Integer id);

}
