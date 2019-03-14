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
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = DBHelper.getInstance(this.getApplicationContext());
        loadSampleData();
    }


    protected void loadSampleData() {
        db.cleanDB();
        db.insertGroups("L2", 2);
        db.insertGroups("L3", 3);
        db.insertGroups("M1", 4);
        db.insertGroups("M2", 5);
        db.insertGroups("L1", 1);
        db.insertGroups(getString(R.string.no_group), -1);
        db.insertCourse("DBD", "Database Design");
        db.insertCourse("SLR", "Recherche");
        db.insertCourse("ProgInterf", "Android");
        db.insertCourse("ProgSym", "CLIPS");
        db.insertCourse("NLP", "Machine Learning");
        db.insertCourse("Projet", "MMA");
        db.insertGroups(getString(R.string.lecturer_group), -2);
        db.insertPerson("Nathalie", "Camelin", "-2");
        db.insertPerson("Nicolas", "Dugué", "-2");
        db.insertPerson("Valérie", "Renault", "-2");
        db.insertPerson("Thierry", "Lemeunier", "-2");
        db.insertPerson("Marie", "Tahon", "-2");
        db.insertPerson("Sylvain", "Meigner", "-2");
        db.insertPerson("Valentin", "Pelloin", "4");
        db.insertPerson("Benoit", "Combasteix", "4");
        db.insertPerson("Valentin", "Debonne", "4");
        db.insertPerson("Valentin", "Lion", "4");
        db.insertPerson("Florian", "Sebille", "4");
        db.insertPerson("Thibault", "Brocherieux", "4");
        db.insertPerson("Adam", "Lion", "3");
        db.insertPerson("Anthony", "Lardy", "3");
        db.insertPerson("Maelle", "Brassier", "5");
        db.insertPerson("Maria", "Molina", "5");
        db.insertPerson("Thibault", "Prouteau", "4");


        db.insertLecture("2019-03-22 08:00", "2019-03-22 10:00", "Nathalie Camelin", "IC2-114", "1", "4");
        db.insertLecture("2019-03-22 08:00", "2019-03-22 10:00", "Fethi Bougares", "IC2-114", "2", "4");
        db.insertLecture("2019-03-22 08:00", "2019-03-22 12:00", "Marie Tahon", "IC2-114", "4", "3");
        db.insertLecture("2019-03-22 08:00", "2019-03-22 16:00", "Marie Tahon", "IC2-114", "4", "3");
        db.insertLecture("2019-03-22 08:00", "2019-03-22 18:00", "Marie Tahon", "IC2-114", "4", "3");
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}