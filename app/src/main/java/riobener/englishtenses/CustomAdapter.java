package riobener.englishtenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Asus on 09.04.2018.
 */

public class CustomAdapter extends BaseAdapter{
    Context context;
    LayoutInflater layoutInflater;
    View view;

    private final String[]tensesList = {"Past Simple","Present Simple", "Future Simple",
            "Past Continuous","Present Continuous","Future Continuous",
            "Past Perfect","Present Perfect","Future Perfect",
            "Past Perfect Continuous","Present Perfect Continuous","Future Perfect Continuous"};
    CustomAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return tensesList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertview==null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.button_grid_item,null);
            Button btn = (Button)view.findViewById(R.id.buttn);
            viewGroup.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            btn.setText(tensesList[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(view.getId()){
                        case R.id.buttn: Toast.makeText(context,"Hello",Toast.LENGTH_LONG) ;
                            break;
                    }
                }
            });
        }

        return view;
    }
}
