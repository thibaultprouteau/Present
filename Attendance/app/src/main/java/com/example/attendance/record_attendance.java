package com.example.attendance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;

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
        Log.d(VISUALIZE, "lectureId" + lectureId);
        title = getIntent().getStringExtra("title");
        groupId = getIntent().getStringExtra("groupId");
        Log.d(MENUERROR, "onCreate: groupId " + groupId);
        Toolbar toolbar_record_attendance = findViewById(R.id.toolbar_record_attendance);
        setSupportActionBar(toolbar_record_attendance);
        getSupportActionBar().setTitle(title);
        db = new DBHelper(this);
        checkoutRecords();
        Log.d("RecordsContents", "Records content: " + records.toString());
        getContent();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.attendance_record, menu);
        if (!records.isEmpty()) {
            Log.d("RecordsContents", "Records: " + records.toString());
            for (int j = 0; j < adapter.getCount(); j++) {
                TextView textView = (TextView) listView.getChildAt(j);
                String valueOfTextView = textView.getText().toString();
                if (records.containsKey(valueOfTextView)) {
                    String status = records.get(valueOfTextView);
                    Log.d("Status", "status: " + status);
                    if (status.equals("1")) {
                        Log.d("loop", "entered the clause: ");
                        textView.setBackgroundColor(Color.parseColor("#ee6055"));
                    } else if (status.equals("2"))
                        textView.setBackgroundColor(Color.parseColor("#ee6055"));
                    else if (status.equals("3"))
                        textView.setBackgroundColor(Color.parseColor("#ffd97d"));

                }
            }

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.present:
                for (int i = 0; i < adapter.getCount(); i++) {
                    listView.getChildAt(i).setBackgroundColor(Color.parseColor("#aaf683"));
                    String attendee = listView.getItemAtPosition(i).toString();
                    records.put(attendee, "1");
                }
                break;
            case R.id.absent:
                for (int i = 0; i < adapter.getCount(); i++) {
                    listView.getChildAt(i).setBackgroundColor(Color.parseColor("#ee6055"));
                    String attendee = listView.getItemAtPosition(i).toString();
                    records.put(attendee, "3");
                }
                break;
            case R.id.save_attendance:
                try {
                    insertRecords(records);

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                finish();
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


    public void checkoutRecords() {
        ArrayList<AttendanceRecord> recordArrayList = db.getAttendanceRecords(Integer.valueOf(lectureId));
        ArrayList<Person> peopleInGroup = db.getPeople();
        if (!recordArrayList.isEmpty()) {
            for (AttendanceRecord aR : recordArrayList) {
                Integer personID = aR.getIdPerson();
                Log.d("checkoutRecords", "checkoutRecords: personId " + personID);
                for (Person p : peopleInGroup) {
                    if (p.getIdPerson() == personID) {
                        String personFullName = p.getFirstName() + " " + p.getLastName();
                        Log.d("checkoutRecords", "checkoutRecords: personId " + personFullName);
                        records.put(personFullName, aR.getStatus());
                    }
                }
            }
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

        if (!records.isEmpty()) {
            Log.d("RecordsContents", "Records: " + records.toString());
            for (int j = 0; j < adapter.getCount(); j++) {
                listView.setBackgroundColor(10);
                /*String valueOfTextView = textView.getText().toString();
                if(records.containsKey(valueOfTextView)){
                    String status = records.get(valueOfTextView);
                    Log.d("Status", "status: "+status);
                    if(status.equals("1")){
                        Log.d("loop", "entered the clause: ");
                        textView.setBackgroundColor(Color.parseColor("#ee6055"));
                    }
                    else if(status.equals("2")) textView.setBackgroundColor(Color.parseColor("#ee6055"));
                    else if(status.equals("3")) textView.setBackgroundColor(Color.parseColor("#ffd97d"));

                }*/
            }


        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!records.containsKey(parent.getItemAtPosition(position).toString())) {
                    records.put(parent.getItemAtPosition(position).toString(), "1");
                    Log.d(VISUALIZE, "onItemClick: value 1 " + records.toString());
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#aaf683"));
                } else if (records.get(parent.getItemAtPosition(position).toString()).equals("1")) {
                    Log.d(VISUALIZE, "onItemClick: value 2" + records.toString());
                    records.put(parent.getItemAtPosition(position).toString(), "2");
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#ee6055"));

                } else if (records.get(parent.getItemAtPosition(position).toString()).equals("2")) {
                    records.put(parent.getItemAtPosition(position).toString(), "3");
                    Log.d(VISUALIZE, "onItemClick: value 3" + records.toString());
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#ffd97d"));
                } else if (records.get(parent.getItemAtPosition(position).toString()).equals("3")) {
                    records.put(parent.getItemAtPosition(position).toString(), "1");
                    Log.d(VISUALIZE, "onItemClick: value 1" + records.toString());
                    parent.getChildAt(position).setBackgroundColor(Color.parseColor("#aaf683"));
                }

            }
        });
    }


}
