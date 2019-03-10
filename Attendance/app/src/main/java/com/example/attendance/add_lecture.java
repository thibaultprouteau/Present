package com.example.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);
        db = new DBHelper(this);
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
        //TODO add modifier for all values
        //TODO attendance taking

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!date.getText().toString().isEmpty() &&
                !start.getText().toString().isEmpty() &&
                !end.getText().toString().isEmpty() &&
                !group.getSelectedItem().toString().isEmpty() &&
                !lecturer.getSelectedItem().toString().isEmpty() &&
                !location.getText().toString().isEmpty()) {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-mm-yyyy hh:mm");
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
        }
        return super.onOptionsItemSelected(item);

    }

}
