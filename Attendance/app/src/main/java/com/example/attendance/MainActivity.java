package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onClick_gerer_cours(View view) {
        Intent gerer_cours = new Intent(getApplicationContext(), gerer_cours.class);
        startActivity(gerer_cours);
    }

    protected void onClick_gerer_groupes(View view) {
        Intent gerer_groupes = new Intent(getApplicationContext(), gerer_groupes.class);
        startActivity(gerer_groupes);
    }

    protected void onClick_statistiques(View view) {
        Intent statistiques = new Intent(getApplicationContext(), statistiques.class);
        startActivity(statistiques);
    }
}