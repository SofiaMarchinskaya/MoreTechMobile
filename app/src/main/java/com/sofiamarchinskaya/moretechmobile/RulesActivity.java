package com.sofiamarchinskaya.moretechmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RulesActivity extends AppCompatActivity implements RuleAdapter.OnItemClicked {

    ArrayList<Rule> rules = new ArrayList<Rule>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
       RuleAdapter adapter = new RuleAdapter(this, rules);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        adapter.setOnClick(this);


    }
    private void setInitialData(){

        rules.add(new Rule ("Цель игры", R.drawable.ic_crown_2));
        rules.add(new Rule ("События", R.drawable.ic_note));
        rules.add(new Rule ("Необязательные расходы", R.drawable.ic_wallet));

    }

    @Override
    public void onItemClick(int position) {



    }
}

