package pl.edu.uwr.pum.lista1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishScreen  extends AppCompatActivity {

    private TextView mScoreTextView;
    private Button mRestartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.finish_screen);

        mScoreTextView = findViewById(R.id.score_textview);
        mRestartButton = findViewById(R.id.restart_button);

        int score = getIntent().getIntExtra("Score",0);
        int totalScore = getIntent().getIntExtra("Total score",0);
        mScoreTextView.setText("Gratulacje\nTwój wynik to: " + score + "/" + (totalScore * 10));

        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
