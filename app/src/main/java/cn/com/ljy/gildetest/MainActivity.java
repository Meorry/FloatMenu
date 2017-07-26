package cn.com.ljy.gildetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView mShowData;
    private long time1;
    private long time2;
    private long time3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Button btnx = (Button) findViewById(R.id.btn_move_x);
        Button btny = (Button) findViewById(R.id.btn_move_y);
        Button btnall = (Button) findViewById(R.id.btn_move_all);
        View view = findViewById(R.id.view);
        FloatViewUtils.moveView(view, btnx, FloatViewUtils.MOVE_TYPE_X);
        FloatViewUtils.moveView(view, btny, FloatViewUtils.MOVE_TYPE_Y);
        FloatViewUtils.moveView(view, btnall, FloatViewUtils.MOVE_TYPE_ALL);
        mShowData = (TextView) findViewById(R.id.text_show_data);
        findViewById(R.id.btn_get_now_day).setOnClickListener(this);
        findViewById(R.id.btn_get_now_time).setOnClickListener(this);

        time1 = System.currentTimeMillis();
        time2 = new Date().getTime();
        time3 = Calendar.getInstance().getTimeInMillis();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_now_day:
                mShowData.setText(DataUtils.getShortTime(time1));
                break;
            case R.id.btn_get_now_time:
//                mShowData.setText(DataUtils.getShortTime2(time2));
                mShowData.setText(" " + DataUtils.getMaxDayByMonth(2017, 7));
                break;
        }
    }
}
