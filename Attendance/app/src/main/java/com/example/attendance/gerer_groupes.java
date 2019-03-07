package com.example.attendance;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class gerer_groupes extends AppCompatActivity {

    private static final String DBDEBUG = "dbdebug";
    DBHelper db;

    private static final String MENUERROR = "MenuGroupes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_groupes);
        Toolbar toolbar_gerer_groupes = findViewById(R.id.toolbar_gerer_groupes);
        setSupportActionBar(toolbar_gerer_groupes);
        db = new DBHelper(this);
        ArrayList<String> groupNames = new ArrayList<>();
        for (Groups g : db.getGroups()) {
            groupNames.add(g.getGroupName());
        }
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                groupNames);//L'arraylist est vide c'est pour ça que rien ne s'affiche dans la listview
        Log.d(DBDEBUG, "ArrayList.size " + groupNames.size());

        ListView groupslistView = findViewById(R.id.list_view_groupes);
        groupslistView.setAdapter(itemsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_import, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_button:
                Intent ajout_cours = new Intent(getApplicationContext(), ajout_etudiant.class);
                startActivity(ajout_cours);
                break;
            case R.id.import_course:
                //import
                break;
            default:
                //nothing
                Log.e(MENUERROR,"Uknown menu item pressed");
        }
        return super.onOptionsItemSelected(item);
    }

}
