package com.example.laleydeldadoappoficial.dbs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.laleydeldadoappoficial.dao.DiceDAO;
import com.example.laleydeldadoappoficial.pojo.Dice;

@Database(entities = {Dice.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DiceDAO getDiceDAO();
}
