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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
        lectures.clear();
        Log.d(MENUERROR, "Value of id" + courseId);
        for (Lecture l : db.getLectures(Integer.valueOf(courseId))) {
            item = new HashMap<String, String>();
            item.put("courseName", courseName);
            item.put("groupName", db.getGroupName(l.getIdGroup().toString()));
            item.put("start", getString(R.string.start_time) + " " + l.getStartTime());
            item.put("end", getString(R.string.end_time) + " " + l.getEndTime());
            item.put("lecturer", getString(R.string.lecturer2) + " " + l.getLecturer() + " " + getString(R.string.location) + " " + l.getLocation());
            lectures.add(item);
        }
        adapter = new SimpleAdapter(this,
                lectures, R.layout.list_item,
                new String[]{"courseName", "groupName", "start", "end", "lecturer"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_e, R.id.line_c, R.id.line_d});
        lecturesListView = findViewById(R.id.list_view_seances);
        lecturesListView.setAdapter(adapter);
        lecturesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> valuesRetrieved = (HashMap<String, String>) (((ListView) seances_cours.this.findViewById(R.id.list_view_seances)).getItemAtPosition((int) id));
                HashMap<String, String> valuesOfSelectedItem = new HashMap<>();
                valuesOfSelectedItem.put("courseName", valuesRetrieved.get("courseName"));
                valuesOfSelectedItem.put("groupName", valuesRetrieved.get("groupName"));

                String[] startTime = valuesRetrieved.get("start").split(": ");
                Log.d(MENUERROR, "onItemClick: " + startTime.length);
                Log.d(MENUERROR, "onItemClick: " + startTime[1]);
                valuesOfSelectedItem.put("start", valuesRetrieved.get("start"));
                String[] endTime = valuesRetrieved.get("end").split(": ");
                valuesOfSelectedItem.put("end", endTime[1]);
                Log.d(MENUERROR, "onItemClick: " + endTime[1]);
                String[] lecturerLocation = valuesRetrieved.get("lecturer").split(": ");
                String[] temp = lecturerLocation[2].split(getString(R.string.location_));
                valuesOfSelectedItem.put("lecturer", lecturerLocation[1]);
                valuesOfSelectedItem.put("location", temp[0]);
                Log.d(MENUERROR, "onItemClick: " + valuesOfSelectedItem.get("location"));
                String lectureId = null;
                try {
                    lectureId = db.getLectureColumnId(valuesOfSelectedItem.get("start"),
                            valuesOfSelectedItem.get("end"),
                            valuesOfSelectedItem.get("lecturer"),
                            valuesOfSelectedItem.get("location"),
                            db.getGroupId(valuesOfSelectedItem.get("groupName")), courseId);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), record_attendance.class);
                intent.putExtra("lectureId", lectureId);
                intent.putExtra("title", courseName + " " + valuesOfSelectedItem.get("start"));
                intent.putExtra("groupId", db.getGroupId(valuesOfSelectedItem.get("groupName")).toString());
                Log.d(MENUERROR, "onItemClick: groupId " + db.getGroupId(valuesOfSelectedItem.get("groupName")));
                startActivity(intent);

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {

            getContent();
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
