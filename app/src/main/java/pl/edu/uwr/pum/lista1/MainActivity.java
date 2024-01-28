package pl.edu.uwr.pum.lista1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestionNumberTextView;
    private ProgressBar mQuestionProgressBar;
    private TextView mQuestionTextView;
    private RadioGroup mAnswersRadioGroup;
    private Button mNextQuestionButton;

    private ArrayList<Question> mQuestionBank = new ArrayList<>(Arrays.asList(
            new Question("Droga mleczna to galaktyka:",
                    new String[]{" Spiralna z poprzeczką"," Eliptyczna"," Soczewkowata"," Spiralna bez poprzeczki"},0,false),
            new Question("Pas Kuipera znajduje się za orbitą:",
                    new String[]{" Marsa"," Neptunna"," Jowisza"," Merkury"},1,false),
            new Question("Czym są egzoplanety?",
                    new String[]{" To planety składające się głównie z gazów"," Są to niewielkie ciała niebieskie"," Planety krążące wokół gwiazdy innej niż Słońce"," To nazwa na duże asteroidy"},2,false),
            new Question("Jaka gwiaza (nie licząc Słońca) znajduje się najbliżej Ziemi?",
                    new String[]{" Syriusz"," Proxima Centauri"," Alfa Centauri A"," Alfa Centauri B"},1,false),
            new Question("Co znajduje się w centrum Drogi Mlecznej",
                    new String[]{" Ziemia"," Gromada gwiazd"," Układ Słoneczny"," Supermasywna czarna dziura"},3,false),
            new Question("Gwiazy powstają:",
                    new String[]{" podczas zapadania się obłoków molekularnych"," podczas eksplozji supernowych typu Ia"," dzięki wiatrom kosmicznym"," Żadna odpowiedź nie jest poprawna"},0,false),
            new Question("Którą z planet NIE zaliczmy do planet wewnętrznych?",
                    new String[]{" Ziemia"," Mars"," Uran"," Wenus"},2,false),
            new Question("Kratety obserwowalne m.in. na Marsie są wynikiem:",
                    new String[]{" działaności wulkanicznej"," Wielkiego Bombardowania"," ingerencji człowieka"," Wszystkie odpowiedzi są prawidłowe"},1,false),
            new Question("Jednostka astronomiczna [1 au] to jednostka",
                    new String[]{" czasu w astronomii"," miary strumienia świetlnego w układzie SI"," światłości źródła światła"," odlgłości - średnia odległość między Ziemią a Słońcem"},3,false),
            new Question("Czym jest kwazar?",
                    new String[]{" Gwiazda o bardzo dużej masie"," Obiekt gwiazdopodobny emitujący fale radiowe"," Mała galaktyka w grupie galaktyk"," Jeden z etapów życia gwiazdy na ciągu głównym"},1,false)
    ));

    private int mUserScore = 0;
    private int mCurrentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionNumberTextView = findViewById(R.id.question_number_textview);
        mQuestionProgressBar = findViewById(R.id.question_progressbar);
        mQuestionTextView = findViewById(R.id.question_textview);
        mAnswersRadioGroup = findViewById(R.id.answers_radiogroup);
        mNextQuestionButton = findViewById(R.id.next_question_button);

        updateQuestion();

        mNextQuestionButton.setOnClickListener(v -> {
            if (mAnswersRadioGroup.getCheckedRadioButtonId() != -1) {

                if(((RadioButton) findViewById(mAnswersRadioGroup.getCheckedRadioButtonId())).getText().equals(
                        mQuestionBank.get(mCurrentQuestionIndex).getAnswers()[mQuestionBank.get(mCurrentQuestionIndex).getCorrectAnswerIndex()])) {
                    mUserScore += 10;
                }

                mAnswersRadioGroup.clearCheck();
                mCurrentQuestionIndex++;

                if(mCurrentQuestionIndex == mQuestionBank.size()) {
                    Intent intent = new Intent(MainActivity.this, FinishScreen.class);
                    intent.putExtra("Score", mUserScore);
                    intent.putExtra("Total score", mQuestionBank.size());
                    startActivity(intent);
                    finish();
                } else {
                    updateQuestion();
                }
            }
        });
    }

    private void updateQuestion() {
        if(mCurrentQuestionIndex < mQuestionBank.size()) {
            Question currentQuestion = mQuestionBank.get(mCurrentQuestionIndex);

            mQuestionNumberTextView.setText("Pytanie " + (mCurrentQuestionIndex + 1) + "/" + mQuestionBank.size());
            mQuestionProgressBar.setProgress((int) (((float) mCurrentQuestionIndex / mQuestionBank.size()) * 100));
            mQuestionTextView.setText(currentQuestion.getTextResId());

            mAnswersRadioGroup.removeAllViews();

            String[] answers = currentQuestion.getAnswers();

            for(int i = 0; i < answers.length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(answers[i]);

                radioButton.setBackgroundResource(R.drawable.custom_radio_button);
                radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                radioButton.setTypeface(null, Typeface.BOLD);
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                        RadioGroup.LayoutParams.MATCH_PARENT, 0, 1.0f
                );

                params.setMargins(20, 5, 5, 60); //
                radioButton.setLayoutParams(params);
                mAnswersRadioGroup.addView(radioButton);
            }
        } else {
            Intent intent = new Intent(MainActivity.this, FinishScreen.class);
            intent.putExtra("Score", mUserScore);
            intent.putExtra("Total score", mQuestionBank.size());
            startActivity(intent);
            finish();
        }
    }
}
