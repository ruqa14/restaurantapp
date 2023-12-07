package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MenuActivity extends AppCompatActivity {

    private DatabaseHelperMenu db;
    private RecyclerView recyclerView;
    private TextView total;
    private Button checkoutButton;
    private MyAdapter myAdapter;
    private String menuItemsStr[], itemsDescriptionStr[], itemsPricesStr[], itemsTimeStr[];
    private int images[] = {R.drawable.burger, R.drawable.pizza, R.drawable.juice, R.drawable.sandwich, R.drawable.pasta};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Menu");
        setContentView(R.layout.activity_menu);

        db = new DatabaseHelperMenu(this);
        recyclerView = findViewById(R.id.recyclerView);
        menuItemsStr = getResources().getStringArray(R.array.menuItems);
        itemsDescriptionStr = getResources().getStringArray(R.array.Description);
        itemsPricesStr = getResources().getStringArray(R.array.Prices);
        itemsTimeStr = getResources().getStringArray(R.array.Time);
        total = (TextView) findViewById(R.id.totalNumbTxt);
        checkoutButton = findViewById(R.id.checkoutBtn);

        myAdapter = new MyAdapter(this, menuItemsStr, itemsDescriptionStr, itemsPricesStr, itemsTimeStr, images, total);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = myAdapter.getMaxTime();
                if (time == -1) {
                    Toast.makeText(getApplicationContext(), "No items was selected", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(MenuActivity.this, TimerActivity.class);
                    i.putExtra("timer", time);
                    Toast.makeText(getApplicationContext(), "Thanks, We are preparing your order", Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();

                }
            }
        });
    }



}