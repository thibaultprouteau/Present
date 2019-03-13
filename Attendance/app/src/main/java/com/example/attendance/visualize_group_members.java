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

import java.util.ArrayList;

public class visualize_group_members extends AppCompatActivity {

    private static final String MENUERROR = "MenuVisualize";
    private static final String VISUALIZE = "visualize";
    DBHelper db;
    private String firstName;
    private String lastName;
    private String groupName;
    private String groupId;
    private ListView listView;
    private ArrayList<String> groupMembers;
    private ArrayAdapter<String> adapter;
    private String itemPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_group_members);
        db = DBHelper.getInstance(getApplicationContext());
        groupName = getIntent().getStringExtra("groupName");
        groupId = getIntent().getStringExtra("idGroup");
        Log.d(VISUALIZE, "GroupId " + groupId);
        Log.d(VISUALIZE, "GroupName " + groupName);
        Toolbar toolbar_visualize_students = findViewById(R.id.toolbar_visualize_students);
        setSupportActionBar(toolbar_visualize_students);
        getSupportActionBar().setTitle(groupName);

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
                Intent ajout_etudiant = new Intent(getApplicationContext(), ajout_etudiant.class);
                ajout_etudiant.putExtra("defaultGroup", groupName);
                startActivity(ajout_etudiant);
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
        groupMembers = new ArrayList<String>();
        Log.d(VISUALIZE, Integer.toString(db.getPeople().size()));
        for (Person p : db.getPeople(groupId)) {
            groupMembers.add(p.getFirstName() + " " + p.getLastName());
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, groupMembers);
        Log.d("LVDEBUG", "Listview to visualize students created");
        listView = findViewById(R.id.list_view_group_members);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            getContent();
        }
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        itemPressed = (String) listView.getItemAtPosition(acmi.position);
        menu.setHeaderTitle(getString(R.string.action_to_perform));
        menu.add(0, v.getId(), 0, getString(R.string.edit));
        menu.add(0, v.getId(), 0, getString(R.string.delete));

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == getString(R.string.edit)) {
            //TODO edit

        } else {
            String[] person = itemPressed.split(" ");
            db.deletePerson(person[0], person[1], groupId);
        }
        return super.onContextItemSelected(item);
    }
}
