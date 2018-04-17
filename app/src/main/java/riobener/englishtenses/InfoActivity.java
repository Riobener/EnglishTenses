package riobener.englishtenses;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    Intent intentFrom;
    String title;
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info);

        intentFrom = this.getIntent();
        title = intentFrom.getStringExtra("title");
        setTitle(title);
        mText = (TextView)findViewById(R.id.theoryTxt);
        switch (title){
            case "Past Simple":
                mText.setText(getText(R.string.Past_Simple));
                break;
            case "Present Simple":
                mText.setText(getText(R.string.Present_Simple));
                break;
            case "Future Simple":
                mText.setText(getText(R.string.Future_Simple));
                break;
            case "Past Continuous":
                mText.setText(getText(R.string.Past_Continuous));
                break;
            case "Present Continuous":
                mText.setText(getText(R.string.Present_Continuous));
                break;
            case "Future Continuous":
                mText.setText(getText(R.string.Future_Continuous));
                break;
            case "Past Perfect":
                mText.setText(getText(R.string.Past_Perfect));
                break;
            case "Present Perfect":
                mText.setText(getText(R.string.Present_Perfect));
                break;
            case "Future Perfect":
                mText.setText(getText(R.string.Future_Perfect));
                break;
            case "Past Perfect Continuous":
                mText.setText(getText(R.string.Past_Perfect_Continuous));
                break;
            case "Present Perfect Continuous":
                mText.setText(getText(R.string.Present_Perfect_Continuous));
                break;
            case "Future Perfect Continuous":
                mText.setText(getText(R.string.Future_Perfect_Continuous));
                break;
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
