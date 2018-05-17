package riobener.englishtenses;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.whalemare.sheetmenu.SheetMenu;

import static riobener.englishtenses.StringArrays.readItems;



class MyOnClickListener implements View.OnClickListener
{
    private final int position;
    private Context context;
    Intent theoryIntent;
    private String[]tensList;

    public MyOnClickListener(int position, Context context,String[]tensesList)
    {   this.context = context;
        this.position = position;
        this.tensList = tensesList;
        theoryIntent = new Intent(context,InfoActivity.class);
    }

    public void onClick(View v)
    {
        theoryIntent.putExtra("title",tensList[position]);
        context.startActivity(theoryIntent);
    }
}