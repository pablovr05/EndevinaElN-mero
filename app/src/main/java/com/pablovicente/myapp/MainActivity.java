package com.pablovicente.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int numberToGuess;
    private int contador = 0;
    public static ArrayList<Record> records = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button guessButton = findViewById(R.id.guessButton);
        final TextView textView = findViewById(R.id.textView);
        final TextView textView2 = findViewById(R.id.textView2);
        final EditText editText = findViewById(R.id.editText);
        final ScrollView scrollView = findViewById(R.id.ScrollView);

        Random random = new Random();
        numberToGuess = random.nextInt(100);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGuess(editText, textView, textView2, scrollView, random);
            }
        });

        editText.setOnEditorActionListener((v, actionId, event) -> {
            handleGuess(editText, textView, textView2, scrollView, random);
            return true;
        });
    }

    private void handleGuess(EditText editText, TextView textView, TextView textView2, ScrollView scrollView, Random random) {
        String introducedNumber = editText.getText().toString();

        if (introducedNumber.isEmpty()) {
            addMessageToTextView("Número introduït: " + editText.getText().toString() + " - Introduïu un número.");
            return;
        }

        try {
            int guessedNumber = Integer.parseInt(introducedNumber);

            if (guessedNumber == numberToGuess) {
                contador++;
                addMessageToTextView("Número correcte, felicitats!");
                actualitzaContador(contador, textView2);

                showSaveRecordDialog();

                numberToGuess = random.nextInt(100);
                textView.setText("");
            } else if (guessedNumber < numberToGuess) {
                addMessageToTextView("Número introduït: " + editText.getText().toString() + " - El número és més gran.");
                contador++;
                actualitzaContador(contador, textView2);
            } else {
                addMessageToTextView("Número introduït: " + editText.getText().toString() + " - El número és més petit.");
                contador++;
                actualitzaContador(contador, textView2);
            }
            editText.setText("");

        } catch (NumberFormatException e) {
            addMessageToTextView("Número introduït: " + editText.getText().toString() + " - Introduïu un número vàlid.");
        }
    }

    private void addMessageToTextView(String message) {
        TextView textView = findViewById(R.id.textView);
        ScrollView scrollView = findViewById(R.id.ScrollView);
        textView.append(message + "\n");
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    private void showSaveRecordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Guardar récord");

        final EditText nameEditText = new EditText(MainActivity.this);
        nameEditText.setHint("Introduce tu nombre");

        builder.setView(nameEditText);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String name = nameEditText.getText().toString();
            if (!name.isEmpty()) {
                records.add(new Record(name, contador));
                Toast.makeText(MainActivity.this, "Récord guardado", Toast.LENGTH_SHORT).show();
                openRecordActivity();
            } else {
                Toast.makeText(MainActivity.this, "Por favor, introduce tu nombre", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    private void openRecordActivity() {
        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
        startActivity(intent);
    }

    public static void actualitzaContador(int contador, TextView textView) {
        textView.setText("Intents: " + contador);
    }
}
