package com.pablovicente.endevinaelnmero;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        final Button guessButton = findViewById(R.id.guessButton);
        final TextView textView = findViewById(R.id.textView);
        final EditText editText = findViewById(R.id.editText);

        Random random = new Random();
        final int numberToGuess = random.nextInt(100);

        guessButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String introducedNumber = editText.getText().toString();

                if (introducedNumber.equals(numberToGuess+"")) {
                    textView.setText("Número correcte, felicitats");
                    final int numberToGuess = random.nextInt(100);
                    congratsGameDialogFragment dialogFragment = new congratsGameDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "GAME DIALOG");
                } else if (Integer.parseInt(introducedNumber) < numberToGuess) {
                    textView.setText("El número és més gran");
                } else if (Integer.parseInt(introducedNumber) > numberToGuess) {
                    textView.setText("El número és més petit");
                } else {
                    textView.setText("Error");
                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public class congratsGameDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction.
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Felicitats, el número introduït és correcte");
            return builder.create();
        }
    }
}