package com.example.attendance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class record_attendance extends AppCompatActivity {

    private static final String VISUALIZE = "record_attendance";
    private static final String MENUERROR = "MenuError";
    DBHelper db;
    private ListView listView;
    private String lectureId;
    private HashMap<String, String> records = new HashMap<>();
    private String title;
    private ArrayList<String> groupMembers;
    private String groupId = "empty";
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_attendance);
        lectureId = getIntent().getStringExtra("lectureId");
        title = getIntent().getStringExtra("title");
        groupId = getIntent().getStringExtra("groupId");
        Log.d(MENUERROR, "onCreate: groupId " + groupId);
        Toolbar toolbar_record_attendance = findViewById(R.id.toolbar_record_attendance);
        setSupportActionBar(toolbar_record_attendance);
        getSupportActionBar().setTitle(title);
        db = new DBHelper(this);
        getContent();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.attendance_record, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.present:

                break;
            case R.id.absent:

                break;
            case R.id.save_attendance:

                break;
            default:
                //nothing
                Log.e(MENUERROR, "Unknown menu item pressed");
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertRecords(HashMap<String, String> records) throws NoSuchFieldException {
        Iterator it = records.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String[] attendee = pair.getKey().toString().split(" ");
            String attendeeId = db.getPersonColumnId(attendee[0], attendee[1], groupId);
            db.insertAttendance(lectureId, attendeeId, pair.getValue().toString());
            Log.d(VISUALIZE, "insertRecords: " + lectureId + " " + attendeeId + "" + pair.getValue().toString());
        }
    }

    protected void getContent() {
        groupMembers = new ArrayList<String>();
        //Log.d(VISUALIZE, Integer.toString(db.getPeople().size()));
        System.out.println(groupId.isEmpty());
        for (Person p : db.getPeople(groupId)) {
            groupMembers.add(p.getFirstName() + " " + p.getLastName());
        }
        Log.d(MENUERROR, "Size of array " + groupMembers.toString());
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, groupMembers);
        Log.d("LVDEBUG", "Listview to visualize students created");
        listView = findViewById(R.id.list_view_prise_presence);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!records.containsKey(parent.getItemAtPosition(position).toString())) {
                    records.put(parent.getItemAtPosition(position).toString(), "1");
                    Log.d(VISUALIZE, "onItemClick: inserted value 1 for" + records.toString());
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#aaf683"));
                } else if (records.get(parent.getItemAtPosition(position).toString()).equals("1")) {
                    Log.d(VISUALIZE, "onItemClick: value2");
                    records.put(parent.getItemAtPosition(position).toString(), "2");
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#ee6055"));

                } else if (records.get(parent.getItemAtPosition(position).toString()).equals("2")) {
                    records.put(parent.getItemAtPosition(position).toString(), "3");
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#ffd97d"));
                } else if (records.get(parent.getItemAtPosition(position).toString()).equals("3")) {
                    records.put(parent.getItemAtPosition(position).toString(), "1");
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#aaf683"));
                }

            }
        });
    }


}
