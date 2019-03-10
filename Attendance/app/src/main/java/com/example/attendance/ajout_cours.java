package com.example.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ajout_cours extends AppCompatActivity {

    private static final String DBDEBUG = "dbdebug";
    DBHelper db;
    private TextView courseName;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_cours);
        db = new DBHelper(this);
        Toolbar toolbar_ajout_cours = findViewById(R.id.tool_bar_ajout_cours);
        setSupportActionBar(toolbar_ajout_cours);

        courseName = findViewById(R.id.editText_nomModule);
        description = findViewById(R.id.editText_description);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!courseName.getText().toString().isEmpty()) {
            if (db.getCourseId(courseName.getText().toString()).isEmpty()) {
                db.insertCourse(courseName.getText().toString(), description.getText().toString());
                Log.d(DBDEBUG, "Inserted values  CourseName: " + courseName.getText().toString() + " Lecturer: " + description.getText().toString());
                this.finish();
            } else {
                Toast.makeText(this, getString(R.string.invalidCourseName), Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
