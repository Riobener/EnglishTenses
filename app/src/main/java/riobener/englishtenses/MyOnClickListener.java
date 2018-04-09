package riobener.englishtenses;


import android.view.View;
import android.widget.Toast;

class MyOnClickListener implements View.OnClickListener
{
    private final int position;

    public MyOnClickListener(int position)
    {
        this.position = position;
    }

    public void onClick(View v)
    {
        Toast.makeText(v.getContext(),"Hello",Toast.LENGTH_LONG).show();

    }
}