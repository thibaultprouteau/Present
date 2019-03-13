package com.example.attendance;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class gerer_groupes extends AppCompatActivity {

    private static final String DBDEBUG = "dbdebug";
    DBHelper db;
    private ListView groupslistView;
    private String itemPressed;


    private static final String MENUERROR = "MenuGroupes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_groupes);
        Toolbar toolbar_gerer_groupes = findViewById(R.id.toolbar_gerer_groupes);
        db = DBHelper.getInstance(getApplicationContext());
        setSupportActionBar(toolbar_gerer_groupes);
        getSupportActionBar().setTitle(R.string.gerer_groupes);
        getContent();
    }

    protected void getContent() {
        final ArrayList<String> groupNames = new ArrayList<>();
        for (Groups g : db.getGroups()) {
            groupNames.add(g.getGroupName());
        }
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                groupNames);//L'arraylist est vide c'est pour ça que rien ne s'affiche dans la listview
        Log.d(DBDEBUG, "ArrayList.size " + groupNames.size());

        groupslistView = findViewById(R.id.list_view_groupes);
        groupslistView.setAdapter(itemsAdapter);
        groupslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(DBDEBUG, "onItemClick: ");
                String groupId = db.getGroupId(parent.getItemAtPosition(position).toString());
                Log.d(DBDEBUG, groupId);
                Intent intent = new Intent(getApplicationContext(), visualize_group_members.class);
                Log.d(DBDEBUG, db.getGroupId(parent.getItemAtPosition(position).toString()));
                intent.putExtra("idGroup", groupId)
                        .putExtra("groupName", ((String) parent.getItemAtPosition(position)));
                startActivity(intent);
            }
        });
        registerForContextMenu(groupslistView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_import, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_button:
                Intent ajout_cours = new Intent(getApplicationContext(), ajout_groupe.class);
                startActivity(ajout_cours);
                break;
            case R.id.import_course:
                //do import
                break;
            default:
                //nothing
                Log.e(MENUERROR,"Uknown menu item pressed");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        getContent();
        super.onWindowFocusChanged(hasFocus);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        itemPressed = (String) groupslistView.getItemAtPosition(acmi.position);
        if (!itemPressed.equals(getString(R.string.no_group)))
            menu.setHeaderTitle(getString(R.string.action_to_perform));
        menu.add(0, v.getId(), 0, getString(R.string.edit));
        menu.add(0, v.getId(), 0, getString(R.string.delete));

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == getString(R.string.edit)) {
            if (!itemPressed.equals(getString(R.string.no_group))) {
                Intent intent = new Intent(getApplicationContext(), ajout_groupe.class);
                intent.putExtra("groupName", itemPressed);
                startActivity(intent);
            }
        } else {
            if (!itemPressed.equals(getString(R.string.no_group))) {
                db.deleteGroup(itemPressed);
            }
        }
        return super.onContextItemSelected(item);
    }
}
