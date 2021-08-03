package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InlineSuggestionsRequest;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.support.v7.widget.GridLayout;


public class MainActivity extends AppCompatActivity {
    //0:yellow, 1:red, 2:null
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{1,4,7},{2,5,8},{0,3,6},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    int activePlayer=0;



    public void dropIn(View view){
        ImageView counter= (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2&& gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String winner = null;
                    if (activePlayer == 1) winner = "yellow";
                    else winner = "red";

                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    TextView winnerText = (TextView) findViewById(R.id.textView);
                    winnerText.setText(winner + " has won!!");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);

                }
            }
        }else {
            int counter1=0;
            GridLayout gridLayout=findViewById(R.id.gridLayout);
            for(int i=0; i<gridLayout.getChildCount(); i++) {
                ImageView yourImage = (ImageView)gridLayout.getChildAt(i);
                if (yourImage.getDrawable()==null){
                    counter1=1;
                }
            }
            if(counter1==0){
                gameOver();
            }
        }

    }

    public boolean isGameActive() {
        return gameActive;
    }
    public void gameOver(){
        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView winnerText = (TextView) findViewById(R.id.textView);
        winnerText.setText( "No one has won!!");
        playAgain.setVisibility(View.VISIBLE);
        winnerText.setVisibility(View.VISIBLE);

    }
    public void play(View v){
        Button playAgain=(Button)findViewById(R.id.playAgain);
        TextView winnerText= (TextView)findViewById(R.id.textView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        gameActive=true;
        activePlayer=0;
//        Log.i("info","play again button pressed");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}