package com.example.laleydeldadoappoficial;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.laleydeldadoappoficial.dao.DiceDAO;
import com.example.laleydeldadoappoficial.dbs.AppDatabase;
import com.example.laleydeldadoappoficial.pojo.Dice;

import java.util.List;

public class AddNewDice extends AppCompatActivity {
    AppDatabase database;
    DiceDAO diceDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dice);

    }

    public void createDice(View view) {
        database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        diceDAO = database.getDiceDAO();
        EditText numberField = findViewById(R.id.editText);
        try {
            Dice dice = diceDAO.findById(Integer.parseInt(numberField.getText().toString()));
            if (dice != null) {
                Toast.makeText(this, R.string.dice_already_exists, Toast.LENGTH_LONG).show();
            } else {
                diceDAO.insert(new Dice(Integer.parseInt(numberField.getText().toString())));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(View view) {
        database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        diceDAO = database.getDiceDAO();
        List<Dice> dices = diceDAO.findAll();
        for (Dice dice : dices) {
            diceDAO.delete(dice);
        }
    }
}
