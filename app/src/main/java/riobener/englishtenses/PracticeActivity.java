package riobener.englishtenses;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;

import static riobener.englishtenses.StringArrays.tenseList;

public class PracticeActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton answer;
    Button chext;
    TextView txt;
    TextSwitcher sentences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupRadio();



    }
    void setupRadio(){
        int checkReiteration = 0;
        String[]helpar = new String[11];
        radioGroup = (RadioGroup)findViewById(R.id.varOfAnswers);
        final RadioButton[] radButton = new RadioButton[4];
        for(int i =0; i<radButton.length;i++){
            radButton[i] = new RadioButton(this);
            radButton[i].setId(i+10);

            radioGroup.addView(radButton[i]);


        }

        radButton[0].setText(tenseList[getRandom(0,12)]);
        radButton[1].setText(tenseList[getRandom(0,12)]);
        radButton[2].setText(tenseList[getRandom(0,12)]);
        radButton[3].setText(tenseList[getRandom(0,12)]);

        checkReiteration = getRandom(0,12);
        radButton[getRandom(0,4)].setText(tenseList[checkReiteration]);
        for(int i = 0; i<checkReiteration;i++){
            tenseList[i]= helpar[i];
        }
        for(int j = checkReiteration+1; j<helpar.length; j++ ){
            tenseList[j-1] = helpar[j];
        }
        for(int i = 0; i<radButton.length;i++){
            if(radButton[i].equals(tenseList[checkReiteration])){
                ;
            }else{
                radButton[i].setText(helpar[getRandom(0,11)]);
            }
        }






    }
    //the most useful method in this code
    public static int getRandom(float first,float last){
        int result=(int)((Math.random()*(last-first)+first));
        return result;
    }


}
