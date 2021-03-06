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
    private String itemPressed;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seances_cours);
        db = DBHelper.getInstance(getApplicationContext());

        courseId = getIntent().getStringExtra("courseId");
        courseName = getIntent().getStringExtra("courseName");

        Toolbar toolbar_seances_cours = findViewById(R.id.toolbar_seances_cours);
        setSupportActionBar(toolbar_seances_cours);
        getSupportActionBar().setTitle(getString(R.string.lectures) + " " + courseName);

        getContent();
        registerForContextMenu(lecturesListView);

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
        Log.d(MENUERROR, "Value of id course" + courseId);
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
                valuesOfSelectedItem.put("start", valuesRetrieved.get("start").replace(getString(R.string.start_time) + " ", ""));
                String[] endTime = valuesRetrieved.get("end").split(": ");
                valuesOfSelectedItem.put("end", endTime[1]);
                String[] lecturerLocation = valuesRetrieved.get("lecturer").split(": ");
                String[] temp = lecturerLocation[2].split(getString(R.string.location_));
                //valuesOfSelectedItem.put("lecturer", lecturerLocation[1]);
                String[] temp2 = lecturerLocation[1].split(" ");
                valuesOfSelectedItem.put("lecturer", temp2[0] + " " + temp2[1]);
                valuesOfSelectedItem.put("location", temp[0]);
                String lectureId = null;
                /*********Debug*////////////

                for (Lecture l : db.getLectures()) {
                    if (l.getStartTime().equals(valuesOfSelectedItem.get("start")) &&
                            l.getEndTime().equals(valuesOfSelectedItem.get("end")) &&
                            l.getLocation().equals(valuesOfSelectedItem.get("location")) &&
                            l.getLecturer().equals(valuesOfSelectedItem.get("lecturer"))) {
                        lectureId = l.getIdLecture().toString();

                    }
                }
                Intent intent = new Intent(getApplicationContext(), record_attendance.class);
                intent.putExtra("lectureId", lectureId);
                intent.putExtra("title", courseName + " " + valuesOfSelectedItem.get("start"));
                intent.putExtra("groupId", db.getGroupId(valuesOfSelectedItem.get("groupName")).toString());
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
