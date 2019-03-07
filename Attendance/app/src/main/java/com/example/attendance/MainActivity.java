package com.example.attendance;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASEDEBUG = "DebugDB";
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
    }

    protected void onClick_gerer_cours(View view) {
        Intent gerer_cours = new Intent(getApplicationContext(), gerer_cours.class);
        startActivity(gerer_cours);
        /*********db = new DBHelper(getApplicationContext());
        //db.truncateTable(db.GROUPS_TABLE_NAME);
        //db.onCreate(new SQLiteDatabase() db);
        db.cleanDB();
        db.insertGroups("L2");
        db.insertGroups("L3");
        db.insertGroups("M1");
        db.insertGroups("M2");
        db.insertGroups("TEACHER");
        db.insertPerson("Nathalie", "Camelin", 5);
        db.insertPerson("Nicolas", "Dugué", 4);
        db.insertPerson("Valentin", "Pelloin", 4);
        db.insertPerson("Thibault", "Prouteau", 4);


         for(Groups g : db.getGroups()){
            Log.d(DATABASEDEBUG, g.getGroupName());
         }
        Log.d(DATABASEDEBUG, "Nb of Rows: " + (((Integer) db.getAll(db.GROUPS_TABLE_NAME).getCount()).toString()));
        //Cursor c = db.getAll(db.GROUPS_TABLE_NAME);
        //c.moveToFirst();
        //Log.d(DATABASEDEBUG, "onClick_gerer_cours: "+(c.getString(c.getColumnIndex(db.GROUPS_COLUMN_NAME)))+" ID:"+((Integer)c.getInt(c.getColumnIndex(db.GROUPS_COLUMN_ID))).toString());
         /*while ((c.moveToNext())){
             Log.d(DATABASEDEBUG, "onClick_gerer_cours: "+(c.getString(c.getColumnIndex(db.GROUPS_COLUMN_NAME)))+" ID:"+((Integer)c.getInt(c.getColumnIndex(db.GROUPS_COLUMN_ID))).toString());
         }
        Log.d(DATABASEDEBUG, "onClick_gerer_cours: " + (db.getGroups().isEmpty()));
        for (Groups g : (db.getGroups())) {
            Log.d(DATABASEDEBUG, "Id : " + g.getIdGroup() + " name : " + g.getGroupName());
        }

        for (Person p : (db.getPeople())) {
            Log.d(DATABASEDEBUG, "id : " + p.getIdPerson() + " firstName : " + p.getFirstName() + " lastName : " + p.getLastName() + " idGroup : " + p.getIdGroup());
         }******************/

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