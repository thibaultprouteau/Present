package com.example.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ajout_groupe extends AppCompatActivity {

    DBHelper db;
    private String CHECKED = "checked";
    private ListView listView;
    private TextView groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_groupe);
        db = DBHelper.getInstance(getApplicationContext());
        Toolbar toolbar_ajout_etudiant = findViewById(R.id.tool_bar_ajout_groupe);
        setSupportActionBar(toolbar_ajout_etudiant);
        getSupportActionBar().setTitle(R.string.new_group);
        groupName = findViewById(R.id.group_name);
        getContent();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void getContent() {
        ArrayList<String> people = new ArrayList<>();

        for (Person p : db.getPeople()) {
            people.add(p.getFirstName() + " " + p.getLastName());
        }
        ArrayAdapter<String> peopleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, people);
        listView = findViewById(R.id.list_view_students);
        listView.setAdapter(peopleAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                String selectedItem = listView.getItemAtPosition(position).toString();
                Log.d(CHECKED, "onItemChecked: " + selectedItem);
                ((CheckedTextView) view).setChecked(currentCheck);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!groupName.getText().toString().isEmpty()) {
            if (db.getGroupId(groupName.getText().toString()) == null) {
                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                ArrayList<Person> peopleToBeAdded = new ArrayList<>();
                for (int i = 0; i < checkedItems.size(); i++) {
                    if (checkedItems.valueAt(i) == true) {
                        String[] person = (listView.getItemAtPosition(i)).toString().split(" ");
                        peopleToBeAdded.add(new Person(-1, person[0], person[1], -1));
                    }
                }
                db.insertGroups(groupName.getText().toString());
                for (Person p : peopleToBeAdded) {
                    db.insertPerson(p.getFirstName(), p.getLastName(), db.getGroupId(groupName.getText().toString()));
                    Log.d(CHECKED, "Inserted in table: " + db.PERSON_TABLE_NAME + " " + p.getFirstName() + " " + p.getLastName() + " with groupId: " + db.getGroupId(groupName.getText().toString()));
                }
                this.finish();
            } else {
                Toast.makeText(this, R.string.invalidGroupName, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.emptyGroupName, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
