package com.example.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class add_lecture extends AppCompatActivity {

    private static final String DBDEBUG = "dbdebug";
    private TextView courseName;
    private EditText date;
    private EditText start;
    private EditText end;
    private Spinner group;
    private Spinner lecturer;
    private EditText location;
    private String courseNameValue;
    private ArrayAdapter<String> groupAdapter;
    private ArrayAdapter<String> lecturerAdapter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DBHelper.getInstance(this.getApplicationContext());
        setContentView(R.layout.activity_add_lecture);
        Toolbar toolbar_ajout_seance = findViewById(R.id.tool_bar_ajout_seance);
        setSupportActionBar(toolbar_ajout_seance);
        courseNameValue = getIntent().getStringExtra("courseName");
        courseName = findViewById(R.id.course_name);
        courseName.setText(courseNameValue);
        date = findViewById(R.id.date);
        start = findViewById(R.id.debut);
        end = findViewById(R.id.fin);
        group = findViewById(R.id.group);
        lecturer = findViewById(R.id.lecturer);
        location = findViewById(R.id.location);

        //TODO Add spinners and make transaction work
        addItemsOnSpinner();
        //TODO add modifier for all values
        //TODO attendance taking

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addItemsOnSpinner() {
        ArrayList<Groups> groups = db.getGroups();
        ArrayList<String> groupNames = new ArrayList<>();
        for (Groups g : groups) {
            groupNames.add(g.getGroupName());
        }
        groupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupNames);
        group.setAdapter(groupAdapter);

        ArrayList<Person> people = db.getPeople();
        ArrayList<String> lecturerList = new ArrayList<>();
        for (Person p : people) {
            Log.d(DBDEBUG, "addItemsOnSpinner: groupId " + p.getIdGroup());
            if (p.getIdGroup().toString().equals("-2")) {
                Log.d(DBDEBUG, "addItemsOnSpinner: Entered in loop idGroup:-2");
                lecturerList.add(p.getFirstName() + " " + p.getLastName());
            }
            lecturerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lecturerList);
            lecturer.setAdapter(lecturerAdapter);


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!date.getText().toString().isEmpty() &&
                !start.getText().toString().isEmpty() &&
                !end.getText().toString().isEmpty() &&
                !group.getSelectedItem().toString().isEmpty() &&
                !lecturer.getSelectedItem().toString().isEmpty() &&
                !location.getText().toString().isEmpty()) {
            SimpleDateFormat dateformat = null;
            if (Locale.getDefault().getLanguage().equals("fr")) {
                dateformat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            } else if (Locale.getDefault().getLanguage().equals("en")) {
                dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            }

            Date startTime = null;
            Date endTime = null;
            try {
                startTime = dateformat.parse(date.getText().toString() + " " + start.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                endTime = dateformat.parse(date.getText().toString() + " " + end.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (startTime.before(endTime) && !startTime.toString().isEmpty() && !endTime.toString().isEmpty()) {
                //TODO insert lecture
                db.insertLecture(startTime.toString(), endTime.toString(), lecturer.getSelectedItem().toString(),
                        location.getText().toString(),
                        db.getCourseId(courseName.getText().toString()), db.getGroupId(group.getSelectedItem().toString()));
                Log.d(DBDEBUG, "Inserted values  in table lectures");
                this.finish();
            } else {
                Toast.makeText(this, getString(R.string.emptyField), Toast.LENGTH_SHORT).show();
            }
        } else Toast.makeText(this, getString(R.string.emptyField), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }

}
