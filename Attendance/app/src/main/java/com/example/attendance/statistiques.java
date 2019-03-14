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
        Float statut1 = db.getAttendanceStats("1");
        Float statut2 = db.getAttendanceStats("2");
        Float statut3 = db.getAttendanceStats("3");
        if (statut1 != null && statut2 != null && statut3 != null) {
            pieData.add(new SliceValue(statut1 * 500, getColor(R.color.present)).setLabel(getString(R.string.present)));
            pieData.add(new SliceValue(statut2 * 500, getColor(R.color.absent)).setLabel(getString(R.string.absent)));
            pieData.add(new SliceValue(statut3 * 500, getColor(R.color.retard)).setLabel(getString(R.string.late)));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1(getString(R.string.app_name)).setCenterText1FontSize(20).setCenterText1Color(getColor(R.color.colorPrimary));
            pieChartView.setPieChartData(pieChartData);
        }





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
