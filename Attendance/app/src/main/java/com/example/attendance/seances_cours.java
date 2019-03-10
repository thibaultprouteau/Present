package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class seances_cours extends AppCompatActivity {

    private static final String MENUERROR = "MenuCours";
    private String courseId;
    private String courseName;
    DBHelper db;
    private SimpleAdapter adapter;
    private ArrayList<HashMap<String, String>> lectures = new ArrayList<>();
    private ListView lecturesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seances_cours);

        db = new DBHelper(this);
        courseId = getIntent().getStringExtra("courseId");
        courseName = getIntent().getStringExtra("courseName");

        Toolbar toolbar_seances_cours = findViewById(R.id.toolbar_seances_cours);
        setSupportActionBar(toolbar_seances_cours);
        getSupportActionBar().setTitle(getString(R.string.lectures) + " " + courseName);

        getContent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_import, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_button:
                Intent ajout_seance = new Intent(getApplicationContext(), add_lecture.class);
                ajout_seance.putExtra("courseName", courseName);
                startActivity(ajout_seance);
                break;
            case R.id.import_course:
                //import
                break;
            default:
                //nothing
                Log.e(MENUERROR, "Unknown menu item pressed");
        }
        return super.onOptionsItemSelected(item);
    }

    protected void getContent() {
        HashMap<String, String> item;
        Log.d(MENUERROR, "Value of id" + courseId);
        for (Lecture l : db.getLectures(Integer.valueOf(courseId))) {
            item = new HashMap<String, String>();
            item.put("line1", courseName);
            item.put("line2", db.getGroupName(l.getIdGroup().toString()));
            item.put("line3", getString(R.string.start_time) + " " + l.getStartTime() + " " + getString(R.string.end_time) + " " + l.getEndTime());
            item.put("line4", getString(R.string.lecturer2) + " " + l.getLecturer() + " " + getString(R.string.location) + " " + l.getLocation());
            lectures.add(item);
        }
        adapter = new SimpleAdapter(this,
                lectures, R.layout.list_item,
                new String[]{"line1", "line2", "line3", "line4"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d});
        lecturesListView = findViewById(R.id.list_view_seances);
        lecturesListView.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            getContent();
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
