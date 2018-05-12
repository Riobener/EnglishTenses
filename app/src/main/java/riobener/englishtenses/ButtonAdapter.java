package riobener.englishtenses;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;



/**
 * Created by Asus on 09.04.2018.
 */

public class ButtonAdapter extends BaseAdapter {
    private Context mContext;
    final String[]list;




    public ButtonAdapter(Context c,String[]tenses) {

        this.list = tenses;
        mContext = c;

    }

    public int getCount() {
        return list.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,
                        View convertView, ViewGroup parent) {

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        Button btn;
        if (convertView == null) {
            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(width/3-28, 250));
            btn.setPadding(20, 20, 20, 20);
        }
        else {
            btn = (Button) convertView;
        }
        btn.setText(list[position]);
        btn.setTextColor(Color.BLACK);
        btn.setAllCaps(false);
        btn.setTextSize(15);
        btn.setId(position);
        btn.setBackgroundResource(R.drawable.button_style);
        btn.setOnClickListener(new MyOnClickListener(position,mContext,list));
        return btn;
    }
}