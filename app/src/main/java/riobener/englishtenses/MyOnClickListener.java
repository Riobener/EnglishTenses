package riobener.englishtenses;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.whalemare.sheetmenu.SheetMenu;


class MyOnClickListener implements View.OnClickListener
{

    //CHOSENTIME:
    //1 - Past Simple 2 - Present Simple 3 - Future Simple 4 - Past Continuous 5 - Present Continuous 6 - Future Continuous
    //7 - Past Perfect 8 - Present Perfect 9 - Future Perfect
    // 10 - Past Perfect Continuous 11 - Present Perfect Continuous 12 - Future Perfect Continuous
    public static int CHOSEN_TIME = 0;

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
        ShowMenu();

    }

    private void ShowMenu() {
        SheetMenu.with(context)
                .setTitle("Что вы хотите выбрать?")
                .setMenu(R.menu.grid_menu)
                .setAutoCancel(false)
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {



                         if(menuItem.getItemId()==R.id.theory){

                            theoryIntent.putExtra("title",tensList[position]);
                            context.startActivity(theoryIntent);


                        }
                        return false;
                    }
                }).show();
}
    }