package riobener.englishtenses;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import java.util.ArrayList;
import java.util.Locale;


import static riobener.englishtenses.StringArrays.ANSWERS;
import static riobener.englishtenses.StringArrays.SENTENCES;
import static riobener.englishtenses.StringArrays.tenseList;

public class PracticeActivity extends AppCompatActivity {
    private long timeInMillis = 120000;
    private boolean enableTimer;
    private CountDownTimer mCountDownTimer;
    RadioGroup radioGroup;
    RadioButton[] radButton;
    Button chext;
    TextView timerText;
    TextView statusMes;
    TextSwitcher switcher;
    Intent intent;
    int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        intent = this.getIntent();
        //normal mode = 0, time mode = 1, survival mode = 2
        mode = intent.getIntExtra("mode",0);

        timerText = (TextView)findViewById(R.id.timer);

        switcher = (TextSwitcher)findViewById(R.id.switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setTextSize(25);
                textView.setTextColor(Color.BLACK);
                textView.setPadding(10,0,10,0);

                return textView;
            }
        });

        radioGroup = (RadioGroup)findViewById(R.id.varOfAnswers);
        radButton = new RadioButton[4];
        for(int i =0; i<radButton.length;i++){
            radButton[i] = new RadioButton(this);
            radButton[i].setId(i+10);
            radButton[i].setTextSize(20);
            radioGroup.addView(radButton[i]);
        }

        chext = (Button)findViewById(R.id.mainButton);

        if(mode == 1){
            enableTimer = true;
            startPractice(enableTimer);

            chext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!radioIsChecked()){
                        Toast.makeText(getApplicationContext(),"Пожалуйста,выберите ответ!",Toast.LENGTH_LONG).show();
                    }else if(radioIsChecked()){
                        if(chext.getText().equals("Проверить")){
                            showStatus();
                            chext.setText("Следующее");
                        }else if(chext.getText().equals("Следующее")){
                            hideStatus();
                            startPractice(enableTimer );
                            chext.setText("Проверить");
                        }
                    }
                }
            });
        }else{
            enableTimer = false;
            startPractice(enableTimer);

            chext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!radioIsChecked()){
                        Toast.makeText(getApplicationContext(),"Пожалуйста,выберите ответ!",Toast.LENGTH_LONG).show();
                    }else if(radioIsChecked()){
                        if(chext.getText().equals("Проверить")){
                            showStatus();
                            chext.setText("Следующее");
                        }else if(chext.getText().equals("Следующее")){
                            hideStatus();
                            startPractice(enableTimer );
                            chext.setText("Проверить");
                        }
                    }
                }
            });
        }

    }

    private int position;
    void setupRadio(){
        int checkReiteration;
        int radRep;
        ArrayList<String> helpar = new ArrayList<>();

            checkReiteration = getRandom(0,radButton.length);
        int chosenPos = findCorrectAnswer();
        radButton[checkReiteration].setText(tenseList[chosenPos]);
        for(int i = 0; i<tenseList.length;i++){
            helpar.add(tenseList[i]);
        }
        helpar.remove(chosenPos);
        for(int i = 0; i<radButton.length;i++){
            if(i!=checkReiteration){
                radRep = getRandom(0, helpar.size());
                radButton[i].setText(helpar.get(radRep));
                helpar.remove(radRep);
            }
        }
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case 10:
                        position = 0;
                        break;
                    case 11:
                        position = 1;
                        break;
                    case 12:
                        position = 2;
                        break;
                    case 13:
                        position = 3;
                        break;
                }
            }
        });
    }

    private int currentSent;
    void setNewText(){
        currentSent = getRandom(0,SENTENCES.length);
        switcher.setText(SENTENCES[currentSent]);
    }
    private int findCorrectAnswer(){
        int pos = 0;
        for(int i = 0; i<tenseList.length;i++){
            if(tenseList[i].equals(ANSWERS[currentSent])){
                pos = i;
                break;
            }
        }
        return pos;
    }

private void startPractice(boolean timerOn){
    if(timerOn){
        startTimer();
        setNewText();
        setupRadio();
    }else{
        timerText.setVisibility(View.INVISIBLE);
        setNewText();
        setupRadio();
    }

}
private void showStatus(){
    statusMes = (TextView)findViewById(R.id.status);
    if(radButton[position].getText().equals(ANSWERS[currentSent])){
        statusMes.setTextColor(Color.GREEN);
        statusMes.setText("Правильно!");

    }else{
        statusMes.setTextColor(Color.RED);
        statusMes.setText("Не правильно! Правильный ответ: "+ANSWERS[currentSent]);
    }

}
private void hideStatus(){
    statusMes.setText("");
}
private boolean radioIsChecked(){
    if(!radButton[0].isChecked()&&!radButton[1].isChecked()&&!radButton[2].isChecked()&&!radButton[3].isChecked()) {
        return false;
    }else {
        return true;
    }

}
    //the most useful method in this code
    public static int getRandom(float first,float last){
        int result=(int)((Math.random()*(last-first)+first));
        return result;
    }
    private void startTimer(){
        mCountDownTimer = new CountDownTimer(timeInMillis,1000) {
            @Override
            public void onTick(long l) {
                timeInMillis = l;
                updateTextCount();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }
    void updateTextCount(){
        int min = (int) (timeInMillis / 1000)/60;
        int sec = (int) (timeInMillis / 1000)%60;

        String timeFormat = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        timerText.setText(timeFormat);

    }

}
