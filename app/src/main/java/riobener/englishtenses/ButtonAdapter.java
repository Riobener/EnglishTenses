package riobener.englishtenses;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by Asus on 09.04.2018.
 */

public class ButtonAdapter extends BaseAdapter {
    private Context mContext;

    final String[]tenseList = {"Past\nSimple","Present Simple", "Future Simple",
            "Past Continuous","Present Continuous","Future Continuous",
            "Past Perfect","Present Perfect","Future Perfect",
            "Past Perfect Continuous","Present Perfect Continuous","Future Perfect Continuous"};
    public ButtonAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return tenseList.length;
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return position;
    }



    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {

            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(350, 250));
            btn.setPadding(10, 10, 10, 10);
        }
        else {
            btn = (Button) convertView;
        }

        btn.setText(tenseList[position]);

        btn.setTextColor(Color.BLACK);
        btn.setOnClickListener(new MyOnClickListener(position,mContext,tenseList));
        btn.setAllCaps(false);
        btn.setTextSize(20);
        btn.setId(position);

        return btn;
    }
}