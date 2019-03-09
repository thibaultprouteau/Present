package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class gerer_cours extends AppCompatActivity {

    private static final String MENUERROR = "MenuCours";
    private static final String DBDEBUG = "dbdebug";
    private static final String GERERCOURS = "gererCours";
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_cours);
        Toolbar toolbar_gerer_cours = findViewById(R.id.toolbar_gerer_cours);
        setSupportActionBar(toolbar_gerer_cours);
        getSupportActionBar().setTitle(R.string.gerer_cours);

        Log.d(GERERCOURS, "onCreate: gererCours created ");
        db = new DBHelper(this);
        getContent();




    }

    protected void getContent() {
        ArrayList<String> courseName = db.getAllCoursesNames();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                courseName);//L'arraylist est vide c'est pour ça que rien ne s'affiche dans la listview
        Log.d(DBDEBUG, "ArrayList.size  courseName  :" + courseName.size());

        ListView courseListView = findViewById(R.id.list_view_cours);
        courseListView.setAdapter(itemsAdapter);
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCourse = (String) parent.getItemAtPosition(position);  //gets the item that has been clicked
                Integer courseId = Integer.valueOf(db.getCourseId(selectedCourse));
                //TODO Add new intent
                Intent intent = new Intent(getApplicationContext(), seances_cours.class);
                Log.d(GERERCOURS, ("This content " + (String) parent.getItemAtPosition(position)));
                intent.putExtra("courseId", (db.getCourseId((String) parent.getItemAtPosition(position))));
                intent.putExtra("courseName", (String) parent.getItemAtPosition(position));
                startActivity(intent);

            }
        });
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
                Intent ajout_cours = new Intent(getApplicationContext(), ajout_cours.class);
                startActivity(ajout_cours);
                break;
            case R.id.import_course:
                //import
                break;
            default:
                //nothing
                Log.e(MENUERROR,"Unknown menu item pressed");
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            getContent();
        }
        super.onWindowFocusChanged(hasFocus);
    }


}
