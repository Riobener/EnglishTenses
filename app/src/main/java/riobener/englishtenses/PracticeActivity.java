package riobener.englishtenses;


import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;




import static riobener.englishtenses.StringArrays.tenseList;

public class PracticeActivity extends AppCompatActivity {
    public int correct = 0;
    public int mistakes = 0;
    private long timeInMillis = 0;
    private CountDownTimer mCountDownTimer;
    boolean checkWithoutButton = false;
    ViewGroup radioLayout;
    ViewGroup textLayout;
    ViewGroup progressLayout;
    RadioGroup radioGroup;
    RadioButton[] radButton;
    Button chext;
    Bundle bundle;
    TextView timerText;
    TextView statusMes;
    TextView mainText;
    TextView comboText;
    Intent intent;
    ProgressBar survivalBar;
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = this.getIntent();
        bundle = intent.getExtras();
        //normal mode = 0, time mode = 1, survival mode = 2
        mode = bundle.getInt("mode");
        timeInMillis = bundle.getInt("time");

        statusMes = (TextView)findViewById(R.id.status);
        radioGroup = (RadioGroup)findViewById(R.id.varOfAnswers);
        mainText = (TextView)findViewById(R.id.mainText);
        if(mode==1||mode==2){
            RelativeLayout.LayoutParams radioGroupParams = new RelativeLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            radioGroupParams.setMargins(0,0,0,100);
            radioGroup.setLayoutParams(radioGroupParams);
            RelativeLayout.LayoutParams radioParams = new RelativeLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            radioParams.setMargins(0,20,0,20);
            radButton = new RadioButton[4];
            for(int i =0; i<radButton.length;i++){
                radButton[i] = new RadioButton(this);
                radButton[i].setId(i+10);
                radButton[i].setTextSize(25);
                radioGroup.addView(radButton[i],radioParams);
            }

            }else{
            radButton = new RadioButton[4];
            for(int i =0; i<radButton.length;i++){
                radButton[i] = new RadioButton(this);
                radButton[i].setId(i+10);
                radButton[i].setTextSize(25);
                radioGroup.addView(radButton[i]);
            }

        }

        if(mode == 1){
            getSupportActionBar().setTitle("Режим на время");
            checkWithoutButton = true;
            progressLayout = (ViewGroup)findViewById(R.id.progressLayout);
            timerText = new TextView(this);
            timerText.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams)timerText.getLayoutParams();
            relativeParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            timerText.setLayoutParams(relativeParams);
            timerText.setText("00:00");
            timerText.setTextSize(50);
            timerText.setTextColor(Color.BLACK);
            progressLayout.addView(timerText);
            startReadyDialogTimer();
            startPractice();


        }else if(mode == 0){
            getSupportActionBar().setTitle("Обычный режим");
            checkWithoutButton = false;
            progressLayout = (ViewGroup)findViewById(R.id.progressLayout);
            radioLayout = (ViewGroup)findViewById(R.id.layoutWithRadio);
            chext = new Button(this);
            chext.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)chext.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
            layoutParams.addRule(RelativeLayout.BELOW,radioGroup.getId());
            layoutParams.setMargins(10,10,10,30);
            chext.setLayoutParams(layoutParams);
            chext.setBackgroundResource(R.drawable.button_style);
            chext.setText("Проверить");
            chext.setAllCaps(false);
            chext.setTextSize(15);
            radioLayout.addView(chext);
            comboText = new TextView(this);
            comboText.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            RelativeLayout.LayoutParams comboParams = (RelativeLayout.LayoutParams)comboText.getLayoutParams();
            comboParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            comboText.setLayoutParams(comboParams);
            comboText.setText("Комбо: "+combo);
            comboText.setTextSize(40);
            comboText.setTextColor(Color.DKGRAY);
            comboText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            progressLayout.addView(comboText);
            startPractice();
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
                            startPractice();
                            chext.setText("Проверить");
                        }
                    }
                }
            });
        }else if(mode==2){
            getSupportActionBar().setTitle("Режим выживание");
            checkWithoutButton = true;
            progressLayout = (ViewGroup)findViewById(R.id.progressLayout);
            survivalBar = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);

            survivalBar.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            RelativeLayout.LayoutParams progressParams = (RelativeLayout.LayoutParams)survivalBar.getLayoutParams();
            progressParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            progressParams.setMargins(20,10,20,0);
            survivalBar.setLayoutParams(progressParams);
            survivalBar.setProgress(50);
            survivalBar.setScaleY(7f);
            survivalBar.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

            progressLayout.addView(survivalBar);

            startPractice();





        }
    }
    private int position;
    void setupRadio(){
        int reiteration;
        int radRep;
        ArrayList<String> helpar = new ArrayList<>();

        reiteration = getRandom(0,radButton.length);
        int chosenPos = findCorrectAnswer();
        radButton[reiteration].setText(tenseList[chosenPos]);
        for(int i = 0; i<tenseList.length;i++){
            helpar.add(tenseList[i]);
        }
        helpar.remove(chosenPos);
        for(int i = 0; i<radButton.length;i++){
            if(i!=reiteration){
                radRep = getRandom(0, helpar.size());
                radButton[i].setText(helpar.get(radRep));
                helpar.remove(radRep);
            }
        }
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(checkWithoutButton){
                    switch(i){
                        case 10:
                            counterCorrectOrMistakes();
                            startPractice();
                            position = 0;
                            break;
                        case 11:
                            counterCorrectOrMistakes();
                            startPractice();
                            position = 1;
                            break;
                        case 12:
                            counterCorrectOrMistakes();
                            startPractice();
                            position = 2;
                            break;
                        case 13:
                            counterCorrectOrMistakes();
                            startPractice();
                            position = 3;
                            break;
                    }
                }else{
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

            }
        });
    }

//chosenTense has a value that numbers the txt. file in /assets folder. Calculation takes place in alphabetical order.
    private int chosenTense = 0;

    private String correctAnswer;
    void setNewText(){
        InputStreamReader inputStream = null;
        try {
            chosenTense = getRandom(1,12);
            switch(chosenTense){
                case 1:
                    inputStream = new InputStreamReader(getAssets().open("Future Continuous.txt"));
                    correctAnswer = "Future Continuous";
                    break;
                case 2:
                    inputStream = new InputStreamReader(getAssets().open("Future Perfect Continuous.txt"));
                    correctAnswer = "Future Perfect Continuous";
                    break;
                case 3:
                    inputStream = new InputStreamReader(getAssets().open("Future Perfect.txt"));
                    correctAnswer = "Future Perfect";
                    break;
                case 4:
                    inputStream = new InputStreamReader(getAssets().open("Future Simple.txt"));
                    correctAnswer = "Future Simple";
                    break;
                case 5:
                    inputStream = new InputStreamReader(getAssets().open("Past Continuous.txt"));
                    correctAnswer = "Past Continuous";
                    break;
                case 6:
                    inputStream = new InputStreamReader(getAssets().open("Past Perfect Continuous.txt"));
                    correctAnswer = "Past Perfect Continuous";
                    break;
                case 7:
                    inputStream = new InputStreamReader(getAssets().open("Past Perfect.txt"));
                    correctAnswer = "Past Perfect";
                    break;
                case 8:
                    inputStream = new InputStreamReader(getAssets().open("Past Simple.txt"));
                    correctAnswer = "Past Simple";
                    break;
                case 9:
                    inputStream = new InputStreamReader(getAssets().open("Present Continuous.txt"));
                    correctAnswer = "Present Continuous";
                    break;
                case 10:
                    inputStream = new InputStreamReader(getAssets().open("Present Perfect Continuous.txt"));
                    correctAnswer = "Present Perfect Continuous";
                    break;
                case 11:
                    inputStream = new InputStreamReader(getAssets().open("Present Perfect.txt"));
                    correctAnswer = "Present Perfect";
                    break;
                case 12:
                    inputStream = new InputStreamReader(getAssets().open("Present Simple.txt"));
                    correctAnswer = "Present Simple";
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(inputStream);
        int desiredLine = getRandom(0,4);
        String mainLine="";
        int counterLine = 0;
        try {
            while ((mainLine = br.readLine()) != null)   {
                if (counterLine == desiredLine) {
                    break;
                }
                counterLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainText.setText(mainLine);
    }
   private int findCorrectAnswer(){
        int pos = 0;
        for(int i = 0; i<tenseList.length;i++){
            if(tenseList[i].equals(correctAnswer)){
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
    private void counterCorrectOrMistakes(){
        if(radButton[position].getText().equals(correctAnswer)){
            correct++;
        }else{
            mistakes++;
        }
    }
private void showStatus(){

    if(radButton[position].getText().equals(correctAnswer)){
        statusMes.setTextColor(Color.GREEN);
        statusMes.setText("Правильно!");
        addCombo();

    }else{
        statusMes.setTextColor(Color.RED);
        statusMes.setText("Не правильно! Правильный ответ: "+correctAnswer);
        breakCombo();
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
                mCountDownTimer.cancel();
                timerText.setText("00:00");
                showResultDialog();
            }
        }.start();


    }
    void updateTextCount(){
        int min = (int) (timeInMillis / 1000)/60;
        int sec = (int) (timeInMillis / 1000)%60;

        String timeFormat = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        timerText.setText(timeFormat);
    }
    public int combo = 0;
    void addCombo(){
        combo++;
        comboText.setText("Комбо: "+combo);
    }
    void breakCombo(){
        combo = 0;
        comboText.setText("Комбо: "+combo);
    }
    void startReadyDialogTimer(){
        final Dialog alertDialog = new Dialog(this);
        alertDialog.setCancelable(false);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.dialog_ready);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        final TextView txt = (TextView)alertDialog.findViewById(R.id.readyTimer);

        Thread t = new Thread() {
            @Override
            public void run() {
                txt.setText("3");
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                 if(txt.getText()=="3"){
                                    txt.setText("2");
                                }else if(txt.getText()=="2"){
                                    txt.setText("1");
                                }else if(txt.getText()=="1"){
                                    alertDialog.cancel();
                                    startTimer();
                                    interrupt();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }
    void showResultDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(PracticeActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_result,null);
        builder.setCancelable(false);
        builder.setView(mView);
        final AlertDialog dialog = builder.create();
        dialog.show();
        Button menuButton = (Button)mView.findViewById(R.id.mainMenu);
        Button repeatButton = (Button)mView.findViewById(R.id.repeatButton);
        TextView corText = (TextView)mView.findViewById(R.id.correct);
        TextView misText = (TextView)mView.findViewById(R.id.mistakes);
        corText.setText(corText.getText()+" "+correct);
        misText.setText(misText.getText()+" "+mistakes);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PracticeActivity.this,StartActivity.class));
                mCountDownTimer.cancel();
                dialog.cancel();
                finish();
            }
        });
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restartIntent = getIntent();
                mCountDownTimer.cancel();
                dialog.cancel();
                finish();
                startActivity(restartIntent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(mode==1){
            mCountDownTimer.cancel();
            finish();
            startActivity(new Intent(PracticeActivity.this,StartActivity.class));
        }else{

            finish();
            startActivity(new Intent(PracticeActivity.this,StartActivity.class));
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home&&mode==1){
            mCountDownTimer.cancel();
            finish();

        }else{
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}