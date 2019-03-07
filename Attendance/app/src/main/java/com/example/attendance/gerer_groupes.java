package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class gerer_groupes extends AppCompatActivity {

    private static final String MENUERROR = "MenuGroupes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_groupes);
        Toolbar toolbar_gerer_groupes = findViewById(R.id.toolbar_gerer_groupes);
        setSupportActionBar(toolbar_gerer_groupes);

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
                Intent ajout_cours = new Intent(getApplicationContext(), ajout_etudiant.class);
                startActivity(ajout_cours);
                break;
            case R.id.import_course:
                //import
                break;
            default:
                //nothing
                Log.e(MENUERROR,"Uknown menu item pressed");
        }
        return super.onOptionsItemSelected(item);


    }
}
