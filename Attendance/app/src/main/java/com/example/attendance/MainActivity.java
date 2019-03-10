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

    private static final String DATABASEDEBUG = "dbdebug";
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        db.cleanDB();
        db.insertGroups("L2", 1);
        db.insertGroups("L3", 2);
        db.insertGroups("M1", 3);
        db.insertGroups("M2", 4);
        db.insertGroups("UNCATEGORIZED", -1);
        db.insertCourse("DBD", "Database Design");
        db.insertCourse("AAN", "Machine Learning");
        db.insertGroups("TEACHER", -2);
        db.insertPerson("Nathalie", "Camelin", "-2");
        db.insertPerson("Nicolas", "Dugué", "-2");
        db.insertPerson("Valérie", "Renault", "-2");
        db.insertPerson("Valentin", "Pelloin", "1");
        db.insertPerson("Thibault", "Prouteau", "4");
        db.insertLecture("2019-03-22 08:00", "2019-03-22 10:00", "Nathalie Camelin", "IC2-114", "1", "1");
        db.insertLecture("2019-03-22 08:00", "2019-03-22 10:00", "Fethi Bougares", "IC2-114", "2", "2");
    }

    protected void onClick_gerer_cours(View view) {
        Intent gerer_cours = new Intent(getApplicationContext(), gerer_cours.class);
        startActivity(gerer_cours);
        /*********db = new DBHelper(getApplicationContext());*/
        //db.truncateTable(db.GROUPS_TABLE_NAME);
        //db.onCreate(new SQLiteDatabase() db);

         /*for(Groups g : db.getGroups()){
            Log.d(DATABASEDEBUG, g.getGroupName());
         }
        Log.d(DATABASEDEBUG, "Nb of Rows: " + (((Integer) db.getAll(db.GROUPS_TABLE_NAME).getCount()).toString()));
        Cursor c = db.getAll(db.GROUPS_TABLE_NAME);
        c.moveToFirst();
        Log.d(DATABASEDEBUG, "onClick_gerer_cours: " + (c.getString(c.getColumnIndex(db.GROUPS_COLUMN_NAME))) + " ID:" + ((Integer) c.getInt(c.getColumnIndex(db.GROUPS_COLUMN_ID))).toString());
        while ((c.moveToNext())) {
             Log.d(DATABASEDEBUG, "onClick_gerer_cours: "+(c.getString(c.getColumnIndex(db.GROUPS_COLUMN_NAME)))+" ID:"+((Integer)c.getInt(c.getColumnIndex(db.GROUPS_COLUMN_ID))).toString());
         }
        Log.d(DATABASEDEBUG, "onClick_gerer_cours: " + (db.getGroups().isEmpty()));
        for (Groups g : (db.getGroups())) {
            Log.d(DATABASEDEBUG, "Id : " + g.getIdGroup() + " name : " + g.getGroupName());
        }

        for (Person p : (db.getPeople())) {
            Log.d(DATABASEDEBUG, "id : " + p.getIdPerson() + " firstName : " + p.getFirstName() + " lastName : " + p.getLastName() + " idGroup : " + p.getIdGroup());
        }

        Log.d(DATABASEDEBUG, db.getCourseId("DBD").toString());
*/
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