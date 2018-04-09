package riobener.englishtenses;


import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.whalemare.sheetmenu.SheetMenu;

class MyOnClickListener implements View.OnClickListener
{
    private final int position;
    private Context context;
    private static final String TAG = "ActLogs";

    public MyOnClickListener(int position, Context context,String[]tensesList)
    {   this.context = context;
        this.position = position;
    }

    public void onClick(View v)
    {
        ShowMenu();

    }

    private void ShowMenu() {
        SheetMenu.with(context)
                .setTitle("Выберите категорию:")
                .setMenu(R.menu.grid_menu)
                .setAutoCancel(false)
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.practice){
                            Log.d(TAG, "кнопка Practice");
                            Toast.makeText(context,"Practice",Toast.LENGTH_SHORT).show() ;
                        }else if(menuItem.getItemId()==R.id.theory){
                            Log.d(TAG,"кнопка Theory");
                            Toast.makeText(context,"Theory",Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                }).show();
}
    }