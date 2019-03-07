package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

public class ajout_etudiant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_etudiant);
        Toolbar toolbar_ajout_etudiant = findViewById(R.id.tool_bar_ajout_etudiant);
        setSupportActionBar(toolbar_ajout_etudiant);

        /************* Layout Elements ***********/

        TextView firstName = findViewById(R.id.text_view_prenom_etudiant);
        TextView lastName = findViewById(R.id.textView_nom_etudiant);
        Spinner Group = findViewById(R.id.spinner_groupe_etudiant);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
