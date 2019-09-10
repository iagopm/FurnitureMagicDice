package com.example.laleydeldadoappoficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.laleydeldadoappoficial.dao.DiceDAO;
import com.example.laleydeldadoappoficial.dbs.AppDatabase;
import com.example.laleydeldadoappoficial.pojo.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Boolean zero = false;
    AppDatabase database;
    DiceDAO diceDAO;
    private Integer currentDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {
        database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
        diceDAO = database.getDiceDAO();

        //Find all dices
        List<Dice> dicesToInteger = diceDAO.findAll();

        //Parse them into String
        List<String> dices = new ArrayList<>();
        for (Dice dice : dicesToInteger) {
            dices.add(dice.toString());
        }

        //Add them to the listView
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dices);
        listView.setAdapter(arrayAdapter);
        //Action onClick
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Object clickItemObj = adapterView.getAdapter().getItem(index);
                currentDice = Integer.parseInt(clickItemObj.toString());
            }
        });
    }


    public void roll(View view) {
        TextView label = findViewById(R.id.textView);
        if (currentDice == null || currentDice == 0) {
            Toast.makeText(this, R.string.add_a_new_dice_first, Toast.LENGTH_LONG).show();
        } else {
            if (zero) {
                currentDice++;
                label.setText(Integer.toString(new Random().nextInt(currentDice)));
                currentDice--;
            } else {
                label.setText(Integer.toString(new Random().nextInt(currentDice)));

            }
        }

    }


    public void newDice(View view) {
        Intent addNewDice = new Intent(this, AddNewDice.class);
        startActivity(addNewDice);
    }


    public void setZero(View view) {
        zero = !zero;
    }
}
