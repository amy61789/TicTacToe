package com.tictactoe.funk.tictactoe;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;


public class TicTacToe extends AppCompatActivity implements OnClickListener {

    //instance variables for the widgets
    private Button gameGrid[][] = new Button[3][3];
    private TextView displayMessage;
    private Button newGameButton;

    //instance variables for widget text and functions
    private String gameButton00Text = "";
    private String gameButton01Text = "";
    private String gameButton02Text = "";
    private String gameButton10Text = "";
    private String gameButton11Text = "";
    private String gameButton12Text = "";
    private String gameButton20Text = "";
    private String gameButton21Text = "";
    private String gameButton22Text = "";
    private String gameStatusTextViewLabel = "Player X's turn";
    private String player = "X";

    //define SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        //get references to widgets
        gameGrid[0][0] = (Button) findViewById(R.id.box1);
        gameGrid[0][1] = (Button) findViewById(R.id.box2);
        gameGrid[0][2] = (Button) findViewById(R.id.box3);
        gameGrid[1][0] = (Button) findViewById(R.id.box4);
        gameGrid[1][1] = (Button) findViewById(R.id.box5);
        gameGrid[1][2] = (Button) findViewById(R.id.box6);
        gameGrid[2][0] = (Button) findViewById(R.id.box7);
        gameGrid[2][1] = (Button) findViewById(R.id.box8);
        gameGrid[2][2] = (Button) findViewById(R.id.box9);
        displayMessage = (TextView) findViewById(R.id.userMessage);
        newGameButton = (Button) findViewById(R.id.newGame);

        //set listeners for widgets
        gameGrid[0][0].setOnClickListener(this);
        gameGrid[0][1].setOnClickListener(this);
        gameGrid[0][2].setOnClickListener(this);
        gameGrid[1][0].setOnClickListener(this);
        gameGrid[1][1].setOnClickListener(this);
        gameGrid[1][2].setOnClickListener(this);
        gameGrid[2][0].setOnClickListener(this);
        gameGrid[2][1].setOnClickListener(this);
        gameGrid[2][2].setOnClickListener(this);
        newGameButton.setOnClickListener(this);

        //get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause() {
        //save the instance variables
        Editor editor = savedValues.edit();
        editor.putString("gameButton00Text", gameGrid[0][0].getText().toString());
        editor.putString("gameButton01Text", gameGrid[0][1].getText().toString());
        editor.putString("gameButton02Text", gameGrid[0][2].getText().toString());
        editor.putString("gameButton10Text", gameGrid[1][0].getText().toString());
        editor.putString("gameButton11Text", gameGrid[1][1].getText().toString());
        editor.putString("gameButton12Text", gameGrid[1][2].getText().toString());
        editor.putString("gameButton20Text", gameGrid[2][0].getText().toString());
        editor.putString("gameButton21Text", gameGrid[2][1].getText().toString());
        editor.putString("gameButton22Text", gameGrid[2][2].getText().toString());
        editor.putString("gameStatusTextViewLabel", gameStatusTextViewLabel);
        editor.putString("player", player);

        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        //get the instance variables
        gameButton00Text = savedValues.getString("gameButton00Text", "");
        gameButton01Text = savedValues.getString("gameButton01Text", "");
        gameButton02Text = savedValues.getString("gameButton02Text", "");
        gameButton10Text = savedValues.getString("gameButton10Text", "");
        gameButton11Text = savedValues.getString("gameButton11Text", "");
        gameButton12Text = savedValues.getString("gameButton12Text", "");
        gameButton20Text = savedValues.getString("gameButton20Text", "");
        gameButton21Text = savedValues.getString("gameButton21Text", "");
        gameButton22Text = savedValues.getString("gameButton22Text", "");
        gameStatusTextViewLabel = savedValues.getString("gameStatusTextViewLabel",
                "Player X's turn");
        //player = savedValues.getString("player", "X");

        //set the game grid squares' labels
        gameGrid[0][0].setText(gameButton00Text);
        gameGrid[0][1].setText(gameButton01Text);
        gameGrid[0][2].setText(gameButton02Text);
        gameGrid[1][0].setText(gameButton10Text);
        gameGrid[1][1].setText(gameButton11Text);
        gameGrid[1][2].setText(gameButton12Text);
        gameGrid[2][0].setText(gameButton20Text);
        gameGrid[2][1].setText(gameButton21Text);
        gameGrid[2][2].setText(gameButton22Text);

        //set the game status text
        displayMessage.setText(gameStatusTextViewLabel);
    }

    //clears the game grid by resetting all squares to ""
    public void clearGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                gameGrid[i][j].setText("");
        }
    }

    //starts a new game
    public void NewGame() {
        clearGrid();
        player = "X";
        gameStatusTextViewLabel = "Player X's turn";
        displayMessage.setText(gameStatusTextViewLabel);

    }

    //determines if game can be continued (there are available spaces on game grid to click)
    public boolean contGame() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (gameGrid[i][j].getText() == "") return true;
        return false;
    }

    //checks if game has been won or tie
    private void GameOver() {
        //check for winner by row
        if (gameGrid[0][0].getText() == gameGrid[0][1].getText()
                && gameGrid[0][1].getText() == gameGrid[0][2].getText()
                && gameGrid[0][2].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[0][0].getText() + " wins!";
        }
        else if (gameGrid[1][0].getText() == gameGrid[1][1].getText()
                && gameGrid[1][1].getText() == gameGrid[1][2].getText()
                && gameGrid[1][2].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[1][0].getText() + " wins!";
        }
        else if (gameGrid[2][0].getText() == gameGrid[2][1].getText()
                && gameGrid[2][1].getText() == gameGrid[2][2].getText()
                && gameGrid[2][2].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[2][0].getText() + " wins!";
        }

        //check for winner by column
        else if (gameGrid[0][0].getText() == gameGrid[1][0].getText()
                && gameGrid[1][0].getText() == gameGrid[2][0].getText()
                && gameGrid[2][0].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[0][0].getText() + " wins!";
        }
        else if (gameGrid[0][1].getText() == gameGrid[1][1].getText()
                && gameGrid[1][1].getText() == gameGrid[2][1].getText()
                && gameGrid[2][1].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[0][1].getText() + " wins!";
        }
        else if (gameGrid[0][2].getText() == gameGrid[1][2].getText()
                && gameGrid[1][2].getText() == gameGrid[2][2].getText()
                && gameGrid[2][2].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[0][2].getText() + " wins!";
        }

        //check for win by diagonal
        else if (gameGrid[0][0].getText() == gameGrid[1][1].getText()
                && gameGrid[1][1].getText() == gameGrid[2][2].getText()
                && gameGrid[2][2].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[0][0].getText() + " wins!";
        }
        else if (gameGrid[0][2].getText() == gameGrid[1][1].getText()
                && gameGrid[1][1].getText() == gameGrid[2][0].getText()
                && gameGrid[2][0].getText() != "")
        {
            gameStatusTextViewLabel = "Player " + gameGrid[0][2].getText() + " wins!";
        }

        //check for tie
        else if (!contGame()) {
            gameStatusTextViewLabel = "It's a tie!";

        }

        //game is not over, next player's turn
        else {
            //update next player's turn
            if (player == "X"){
                player = "O";
            }
            else player = "X";
            gameStatusTextViewLabel = "Player " + player + "'s turn";
        }

        //update game status text
        displayMessage.setText(gameStatusTextViewLabel);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.box1:
                if (gameGrid[0][0].getText() == "") {
                    gameGrid[0][0].setText(player);
                    GameOver();
                }
                break;
            case R.id.box2:
                if (gameGrid[0][1].getText() == "") {
                    gameGrid[0][1].setText(player);
                    GameOver();
                }
                break;
            case R.id.box3:
                if (gameGrid[0][2].getText() == "") {
                    gameGrid[0][2].setText(player);
                    GameOver();
                }
                break;
            case R.id.box4:
                if (gameGrid[1][0].getText() == "") {
                    gameGrid[1][0].setText(player);
                    GameOver();
                }
                break;
            case R.id.box5:
                if (gameGrid[1][1].getText() == "") {
                    gameGrid[1][1].setText(player);
                    GameOver();
                }
                break;
            case R.id.box6:
                if (gameGrid[1][2].getText() == "") {
                    gameGrid[1][2].setText(player);
                    GameOver();
                }
                break;
            case R.id.box7:
                if (gameGrid[2][0].getText() == "") {
                    gameGrid[2][0].setText(player);
                    GameOver();
                }
                break;
            case R.id.box8:
                if (gameGrid[2][1].getText() == "") {
                    gameGrid[2][1].setText(player);
                    GameOver();
                }
                break;
            case R.id.box9:
                if (gameGrid[2][2].getText() == "") {
                    gameGrid[2][2].setText(player);
                    GameOver();
                }
                break;
            case R.id.newGame:
                NewGame();
                break;
        }
    }





}

