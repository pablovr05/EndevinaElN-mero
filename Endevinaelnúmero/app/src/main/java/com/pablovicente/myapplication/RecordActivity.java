package com.pablovicente.myapplication;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        tableLayout = findViewById(R.id.tableLayout);

        showRecords();
    }

    private void showRecords() {
        tableLayout.removeAllViews();

        TableRow headerRow = new TableRow(this);
        TextView nameHeader = new TextView(this);
        nameHeader.setText("Nombre");
        TextView attemptsHeader = new TextView(this);
        attemptsHeader.setText("Intentos");

        headerRow.addView(nameHeader);
        headerRow.addView(attemptsHeader);
        tableLayout.addView(headerRow);

        // Esto es para que se muestren los registros en la propia tabla
        for (Record record : MainActivity.records) {
            TableRow row = new TableRow(this);
            TextView nameTextView = new TextView(this);
            nameTextView.setText(record.getName());
            TextView attemptsTextView = new TextView(this);
            attemptsTextView.setText(String.valueOf(record.getAttempts()));

            row.addView(nameTextView);
            row.addView(attemptsTextView);
            tableLayout.addView(row);
        }
    }
}
