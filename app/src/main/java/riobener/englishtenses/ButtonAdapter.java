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

    final String[]tenseList = {"Past Simple","Present Simple", "Future Simple",
            "Past Continuous","Present Continuous","Future Continuous",
            "Past Perfect","Present Perfect","Future Perfect",
            "Past Perfect Continuous","Present Perfect Continuous","Future Perfect Continuous"};
    public ButtonAdapter(Context c) {
        mContext = c;
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return tenseList.length;
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }



    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(350, 250));
            btn.setPadding(10, 10, 10, 10);
        }
        else {
            btn = (Button) convertView;
        }

        btn.setText(tenseList[position]);
        // filenames is an array of strings
        btn.setTextColor(Color.BLACK);
        btn.setOnClickListener(new MyOnClickListener(position));
        btn.setAllCaps(false);
        btn.setTextSize(20);
        btn.setId(position);

        return btn;
    }
}