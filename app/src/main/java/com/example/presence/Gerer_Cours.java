package com.example.presence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

public class Gerer_Cours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer__cours);

        //Toolbar mytoolbar = (Toolbar) findViewById(R.id.tool_bar);
       // setSupportActionBar(mytoolbar);

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu_add, menu);
        return true;
    }*/
}
