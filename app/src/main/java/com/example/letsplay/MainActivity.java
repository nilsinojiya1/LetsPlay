package com.example.letsplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 is 0 and 1 is x
    int activePlayer= 0;
    boolean gameIsActive=true;
    //2 means unused
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tapCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tapCounter]==2 && gameIsActive) {
            gameState[tapCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.zero);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winPositions : winPosition)
            {
                if(gameState[winPositions[0]]==gameState[winPositions[1]] &&
                        gameState[winPositions[1]]==gameState[winPositions[2]] &&
                        gameState[winPositions[0]] != 2)
                {
                    gameIsActive=false;
                    String winner = "X";
                    if (gameState[winPositions[0]]==0) {
                        winner = "O";
                    }
                    System.out.println(gameState[winPositions[0]]);
                    TextView winmssg = (TextView) findViewById(R.id.winMssg);
                    winmssg.setText(winner+" has won!!");
                    //winsome one
                    LinearLayout lout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    lout.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameIsOver =true;
                    for(int counterState : gameState)
                    {
                        if(counterState == 2)
                        {
                            gameIsOver=false;
                        }
                    }
                    if(gameIsOver){
                        TextView winmssg = (TextView) findViewById(R.id.winMssg);
                        winmssg.setText("it's a draw!!");
                        //winsome one
                        LinearLayout lout = (LinearLayout) findViewById(R.id.playAgainLayout);

                        lout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view)
    {
        gameIsActive=true;

        LinearLayout lout = (LinearLayout) findViewById(R.id.playAgainLayout);

        lout.setVisibility(View.INVISIBLE);
        //reset variable
        //0 is 0 and 1 is x
        activePlayer= 0;
        //2 means unused
        for(int i = 0 ;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
