package riobener.englishtenses;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Arrays;

import static riobener.englishtenses.StringArrays.ANSWERS;
import static riobener.englishtenses.StringArrays.SENTENCES;

import static riobener.englishtenses.StringArrays.tenseList;

public class PracticeActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton[] radButton;
    private static final String TAG = "MyLog";
    Button chext;
    TextView statusMes;
    TextSwitcher switcher;
    String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        startPractice();
        chext = (Button)findViewById(R.id.mainButton);
        chext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!radButton[0].isChecked()&&!radButton[1].isChecked()&&!radButton[2].isChecked()&&!radButton[3].isChecked()){
                    Toast.makeText(getApplicationContext(),"Пожалуйста,выберите ответ!",Toast.LENGTH_LONG).show();
                }else if(radButton[0].isChecked()||radButton[1].isChecked()||radButton[2].isChecked()||radButton[3].isChecked()){
                    if(chext.getText().equals("Проверить")){
                        showStatus();
                        chext.setText("Следующее");
                    }else if(chext.getText().equals("Следующее")){
                        hideStatus();
                        startPractice();
                        chext.setText("Проверить");
                    }
                }


            }
        });



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

private void startPractice(){
    setNewText();
    setupRadio();
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
    //the most useful method in this code
    public static int getRandom(float first,float last){
        int result=(int)((Math.random()*(last-first)+first));
        return result;
    }


}
