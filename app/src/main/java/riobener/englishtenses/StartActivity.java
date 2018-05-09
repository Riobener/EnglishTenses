package riobener.englishtenses;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import static riobener.englishtenses.StringArrays.practiceItems;
import static riobener.englishtenses.StringArrays.tenseList;
import static riobener.englishtenses.StringArrays.timeItems;


public class StartActivity extends AppCompatActivity {
    GridView gridview;
    Button practiceButton;
    Intent practiceIntent;
    Bundle extras;
    int chosenItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        extras = new Bundle();
        practiceIntent = new Intent(this, PracticeActivity.class);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ButtonAdapter(this, tenseList));
        practiceButton = (Button) findViewById(R.id.practice);
        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showPracticeOptions();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId()==R.id.statistic){
           Toast.makeText(StartActivity.this,"Когда-нибудь доделаю...",Toast.LENGTH_LONG).show();
       }
                return super.onOptionsItemSelected(item);
        }
       void showPracticeOptions(){
           AlertDialog.Builder alert = new AlertDialog.Builder(this);
           alert.setTitle("Выберите режим:");
           alert.setCancelable(false);
           alert.setSingleChoiceItems(practiceItems,-1, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                    chosenItem = i;
               }
           });
           alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   if(chosenItem==0){
                       chosenItem = -1;
                       extras.putInt("mode",0);
                       practiceIntent.putExtras(extras);
                       startActivity(practiceIntent);

                   }else if(chosenItem==1){
                       chosenItem = -1;
                       showTimeModeOptions();
                   }else if(chosenItem==-1){
                       chosenItem =-1;
                       showPracticeOptions();
                       Toast.makeText(getApplicationContext(),"Выберите режим практики!",Toast.LENGTH_SHORT).show();

                   }
               }
           });
           alert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   chosenItem = -1;
                   dialogInterface.cancel();
               }
           });
           AlertDialog dialog = alert.create();
           dialog.show();
        }
        void showTimeModeOptions(){
            final AlertDialog.Builder timeAlert = new AlertDialog.Builder(this);
            timeAlert.setTitle("Выберите время:");
            timeAlert.setCancelable(false);

            timeAlert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    chosenItem = -1;
                    dialogInterface.cancel();
                }
            });
            timeAlert.setSingleChoiceItems(timeItems,-1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    chosenItem = i;
                }
            });
            timeAlert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch(chosenItem){
                        case -1:
                            chosenItem = -1;
                            showTimeModeOptions();
                            Toast.makeText(getApplicationContext(),"Выберите время!",Toast.LENGTH_SHORT).show();
                            break;
                        case 0:
                            chosenItem = -1;
                            extras.putInt("mode",1);
                            extras.putInt("time",30000);
                            practiceIntent.putExtras(extras);
                            startActivity(practiceIntent);

                            break;
                        case 1:
                            chosenItem = -1;
                            extras.putInt("mode",1);
                            extras.putInt("time",60000);
                            practiceIntent.putExtras(extras);
                            startActivity(practiceIntent);

                            break;
                        case 2:
                            chosenItem = -1;
                            extras.putInt("mode",1);
                            extras.putInt("time",120000);
                            practiceIntent.putExtras(extras);
                            startActivity(practiceIntent);

                            break;
                    }
                }
            });
            timeAlert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    chosenItem = -1;
                    dialogInterface.cancel();
                }
            });
            AlertDialog dialog = timeAlert.create();
            dialog.show();
        }
    }

