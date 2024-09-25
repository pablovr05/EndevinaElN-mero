package com.pablovicente.endevinaelnmero;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int numberToGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button guessButton = findViewById(R.id.guessButton);
        final TextView textView = findViewById(R.id.textView);
        final EditText editText = findViewById(R.id.editText);
        final ScrollView scrollView = findViewById(R.id.ScrollView);

        Random random = new Random();
        numberToGuess = random.nextInt(100);

        guessButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String introducedNumber = editText.getText().toString();

                if (introducedNumber.isEmpty()) {
                    addMessageToTextView("Número introduït: " + editText.getText().toString() + " - Introduïu un número.");
                    return;
                }

                try {
                    int guessedNumber = Integer.parseInt(introducedNumber);

                    if (guessedNumber == numberToGuess) {
                        addMessageToTextView("Número correcte, felicitats!");

                        congratsGameDialogFragment dialogFragment = new congratsGameDialogFragment();
                        dialogFragment.show(getSupportFragmentManager(), "GAME DIALOG");
                        numberToGuess = random.nextInt(100);
                        textView.setText("");

                    } else if (guessedNumber < numberToGuess) {
                        addMessageToTextView("Número introduït: " + editText.getText().toString() + " - El número és més gran.");
                    } else {
                        addMessageToTextView("Número introduït: " + editText.getText().toString() + " - El número és més petit.");
                    }
                    editText.setText("");

                } catch (NumberFormatException e) {
                    addMessageToTextView("Número introduït: " + editText.getText().toString() + " - Introduïu un número vàlid.");
                }
            }

            private void addMessageToTextView(String message) {
                textView.append(message + "\n");
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static class congratsGameDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Felicitats, el número introduït és correcte")
                    .setPositiveButton("OK", (dialog, id) -> {
                    });
            return builder.create();
        }
    }
}
