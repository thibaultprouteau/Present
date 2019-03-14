package com.example.attendance;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class statistiques extends AppCompatActivity {
    DBHelper db;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DBHelper.getInstance(getApplicationContext());
        setContentView(R.layout.activity_statistiques);
        PieChartView pieChartView = findViewById(R.id.chart);
        List pieData = new ArrayList<>();
        float statut1 = db.getAttendanceStats("1");
        float statut2 = db.getAttendanceStats("2");
        float statut3 = db.getAttendanceStats("3");


        pieData.add(new SliceValue(statut1, Color.BLUE).setLabel(getString(R.string.present)));
        pieData.add(new SliceValue(statut2, Color.GRAY).setLabel(getString(R.string.absent)));
        pieData.add(new SliceValue(statut3, Color.RED).setLabel(getString(R.string.late)));
        //pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("Q4: $28"));
        //float [] stats =db.getAttendanceStats();

        //pieData.add(new SliceValue(stats[0],getColor(R.color.present)));
        //pieData.add(new SliceValue(stats[1],getColor(R.color.absent)));
        //pieData.add(new SliceValue(stats[2],getColor(R.color.retard)));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1(getString(R.string.app_name)).setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);

    }
}
