package riobener.englishtenses;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    Intent intentFrom;
    String title;
    TextView mText;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        intentFrom = this.getIntent();
        title = intentFrom.getStringExtra("title");
        setTitle("");
        mText = (TextView)findViewById(R.id.theoryTxt);
        image = (ImageView)findViewById(R.id.theoryPictures);
        switch (title){
            case "Past Simple":
                image.setImageResource(R.drawable.past_simple);
                mText.setText(getText(R.string.Past_Simple));
                break;
            case "Present Simple":
                image.setImageResource(R.drawable.present_simple);
                mText.setText(getText(R.string.Present_Simple));
                break;
            case "Future Simple":
                image.setImageResource(R.drawable.future_simple);
                mText.setText(getText(R.string.Future_Simple));
                break;
            case "Past Continuous":
                image.setImageResource(R.drawable.past_continuous);
                mText.setText(getText(R.string.Past_Continuous));
                break;
            case "Present Continuous":
                image.setImageResource(R.drawable.present_continuous);
                mText.setText(getText(R.string.Present_Continuous));
                break;
            case "Future Continuous":
                image.setImageResource(R.drawable.future_continuous);
                mText.setText(getText(R.string.Future_Continuous));
                break;
            case "Past Perfect":
                image.setImageResource(R.drawable.past_perfect);

                mText.setText(getText(R.string.Past_Perfect));
                break;
            case "Present Perfect":
                image.setImageResource(R.drawable.present_perfect);
                mText.setText(getText(R.string.Present_Perfect));
                break;
            case "Future Perfect":
                image.setImageResource(R.drawable.future_perfect);
                mText.setText(getText(R.string.Future_Perfect));
                break;
            case "Past Perfect Continuous":
                image.setImageResource(R.drawable.past_perfect_continuous);
                mText.setText(getText(R.string.Past_Perfect_Continuous));
                break;
            case "Present Perfect Continuous":
                image.setImageResource(R.drawable.present_perfect_continuous);
                mText.setText(getText(R.string.Present_Perfect_Continuous));
                break;
            case "Future Perfect Continuous":
                image.setImageResource(R.drawable.future_perfect_continuous);
                mText.setText(getText(R.string.Future_Perfect_Continuous));
                break;
        }




    }


}
