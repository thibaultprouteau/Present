package com.example.attendance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ajout_etudiant extends AppCompatActivity {
    private static final String DBDEBUG = "dbdebug";
    private TextView firstName;
    private TextView lastName;
    private Spinner Group;
    DBHelper db;

    /**
     * Working, see whether we should implement the Singleton for db usage
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_etudiant);
        Toolbar toolbar_ajout_etudiant = findViewById(R.id.tool_bar_ajout_etudiant);
        setSupportActionBar(toolbar_ajout_etudiant);
        db = new DBHelper(this);

        /************* Layout Elements ***********/
        firstName = findViewById(R.id.text_view_prenom_etudiant);
        lastName = findViewById(R.id.textView_nom_etudiant);
        Group = findViewById(R.id.spinner_groupe_etudiant);

        addItemsOnSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addItemsOnSpinner() {
        ArrayList<Groups> groups = db.getGroups();
        ArrayList<String> groupNames = new ArrayList<>();
        for (Groups g : groups) {
            groupNames.add(g.getGroupName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupNames);
        Group.setAdapter(dataAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        db.insertPerson(firstName.getText().toString(), lastName.getText().toString(), (db.getGroupId(Group.getSelectedItem().toString())));
        Log.d(DBDEBUG, "Inserted: " + firstName.getText().toString() + " " + lastName.getText().toString());
        this.finish();
        return super.onOptionsItemSelected(item);

    }
}
