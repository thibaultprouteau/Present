package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
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
    private String itemPressed;
    private ListView courseListView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_cours);
        Toolbar toolbar_gerer_cours = findViewById(R.id.toolbar_gerer_cours);
        setSupportActionBar(toolbar_gerer_cours);
        getSupportActionBar().setTitle(R.string.gerer_cours);
        db = DBHelper.getInstance(getApplicationContext());
        Log.d(GERERCOURS, "onCreate: gererCours created ");
        getContent();




    }

    protected void getContent() {
        ArrayList<String> courseName = db.getAllCoursesNames();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                courseName);//L'arraylist est vide c'est pour ça que rien ne s'affiche dans la listview
        Log.d(DBDEBUG, "ArrayList.size  courseName  :" + courseName.size());

        courseListView = findViewById(R.id.list_view_cours);
        courseListView.setAdapter(itemsAdapter);
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCourse = (String) parent.getItemAtPosition(position);  //gets the item that has been clicked
                Integer courseId = Integer.valueOf(db.getCourseId(selectedCourse));
                Intent intent = new Intent(getApplicationContext(), seances_cours.class);
                Log.d(GERERCOURS, ("This content " + (String) parent.getItemAtPosition(position)));
                intent.putExtra("courseId", (db.getCourseId((String) parent.getItemAtPosition(position))));
                intent.putExtra("courseName", (String) parent.getItemAtPosition(position));
                startActivity(intent);

            }
        });
        registerForContextMenu(courseListView);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        itemPressed = (String) courseListView.getItemAtPosition(acmi.position);
        menu.setHeaderTitle(getString(R.string.action_to_perform));
        menu.add(0, v.getId(), 0, getString(R.string.edit));
        menu.add(0, v.getId(), 0, getString(R.string.delete));

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == getString(R.string.edit)) {
            //TODO edit

        } else {
            db.deleteCourse(itemPressed);
        }
        return super.onContextItemSelected(item);
    }
}



