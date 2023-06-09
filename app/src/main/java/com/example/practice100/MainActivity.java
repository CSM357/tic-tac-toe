package com.example.practice100;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Color;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b11, b12, b13, b21, b22, b23, b31, b32, b33, res;
    boolean player1Turn = true;
    int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b11 = findViewById(R.id.button1);
        b12 = findViewById(R.id.button2);
        b13 = findViewById(R.id.button3);
        b21 = findViewById(R.id.button4);
        b22 = findViewById(R.id.button5);
        b23 = findViewById(R.id.button6);
        b31 = findViewById(R.id.button7);
        b32 = findViewById(R.id.button8);
        b33 = findViewById(R.id.button9);
        res = findViewById(R.id.button10);

        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);
        b31.setOnClickListener(this);
        b32.setOnClickListener(this);
        b33.setOnClickListener(this);
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (!button.getText().toString().isEmpty()) {
            return;
        }

        if (player1Turn) {
            button.setText("X");
            button.setTextColor(Color.BLACK);
        } else {
            button.setText("O");
            button.setTextColor(Color.BLACK);
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                playerWins("Player 1");
            } else {
                playerWins("Player 2");
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }
    private boolean checkForWin() {
        String[][] board = {
                {b11.getText().toString(), b12.getText().toString(), b13.getText().toString()},
                {b21.getText().toString(), b22.getText().toString(), b23.getText().toString()},
                {b31.getText().toString(), b32.getText().toString(), b33.getText().toString()}
        };
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].isEmpty() && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (!board[0][i].isEmpty() && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                return true;
            }
        }
        if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return true;
        }
        if (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            return true;
        }

        return false;
    }

    private void playerWins(String player) {
        Toast.makeText(this, player + " wins!", Toast.LENGTH_SHORT).show();
        disableAllButtons();
    }

    private void draw() {
        Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
    }

    private void resetGame() {
        b11.setText("");
        b12.setText("");
        b13.setText("");
        b21.setText("");
        b22.setText("");
        b23.setText("");
        b31.setText("");
        b32.setText("");
        b33.setText("");
        enableAllButtons();
        player1Turn = true;
        roundCount = 0;
    }

    private void disableAllButtons() {
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        b21.setEnabled(false);
        b22.setEnabled(false);
        b23.setEnabled(false);
        b31.setEnabled(false);
        b32.setEnabled(false);
        b33.setEnabled(false);
    }

    private void enableAllButtons() {
        b11.setEnabled(true);
        b12.setEnabled(true);
        b13.setEnabled(true);
        b21.setEnabled(true);
        b22.setEnabled(true);
        b23.setEnabled(true);
        b31.setEnabled(true);
        b32.setEnabled(true);
        b33.setEnabled(true);
    }
}
