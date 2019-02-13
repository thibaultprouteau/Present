package com.example.presence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar mytoolbar = (Toolbar)findViewById(R.id.maintoolbar);
       // setSupportActionBar(mytoolbar);
        Button gerer_cours = (Button)findViewById(R.id.button_gerer_cours);
        Button gerer_groupes = (Button)findViewById(R.id.button_gerer_groupes);
        Button statistiques = (Button)findViewById(R.id.button_statistiques);
        }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu_add, menu);
        return true;
    }*/

    public void onClick_gerer_cours(View view){
        Intent gerer_cours  = new Intent(getApplicationContext(), Gerer_Cours.class);
        startActivity(gerer_cours);
    }

    public void onClick_gerer_groupes(View view){

    }

    public void onClick_statistiques(View view){

    }
}
