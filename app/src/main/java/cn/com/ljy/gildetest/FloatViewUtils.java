package cn.com.ljy.gildetest;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lijiyuan on 2017/7/14.
 * 拖动菜单
 */

public class FloatViewUtils {

    public static final int MOVE_TYPE_X = 1;        //x轴拖动
    public static final int MOVE_TYPE_Y = 2;        //y轴拖动
    public static final int MOVE_TYPE_ALL = 3;      //全屏拖动
    private static boolean clickormove = true;      //点击或拖动，点击为true，拖动为false

    public static void moveView(final View parent, View moveView, final int type){
        moveView.setOnTouchListener(new View.OnTouchListener() {//设置按钮被触摸的时间
            int lastX, lastY;                   // 记录移动的最后的位置
            int downX, downY;                   //按下时的X，Y坐标
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int ea = event.getAction();//获取事件类型
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: // 按下事件
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        downX = lastX;
                        downY = lastY;
                        break;
                    case MotionEvent.ACTION_MOVE: // 拖动事
                        // 移动中动态设置位置
                        int dx = (int) event.getRawX() - lastX;//位移量X
                        int dy = (int) event.getRawY() - lastY;//位移量Y
                        int left = v.getLeft() + dx;
                        int top = v.getTop() + dy;
                        int right = v.getRight() + dx;
                        int bottom = v.getBottom() + dy;
                        //++限定按钮被拖动的范围
                        if (left < 0) {
                            left = 0;
                            right = left + v.getWidth();
                        }
                        if (right > parent.getWidth()) {
                            right = parent.getWidth();
                            left = right - v.getWidth();
                        }
                        if (top < 0) {
                            top = 0;
                            bottom = top + v.getHeight();
                        }
                        if (bottom > parent.getHeight()) {
                            bottom = parent.getHeight();
                            top = bottom - v.getHeight();
                        }
                        //--限定按钮被拖动的范围
                        switch (type){
                            case MOVE_TYPE_X:
                                bottom = v.getBottom();
                                top = v.getTop();
                                break;
                            case MOVE_TYPE_Y:
                                left = v.getLeft();
                                right = v.getRight();
                                break;
                            case MOVE_TYPE_ALL:
                                break;
                        }
                        v.layout(left, top, right, bottom);//按钮重画

                        // 记录当前的位置
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP: // 弹起事件
                        //判断是单击事件或是拖动事件，位移量大于5则断定为拖动事件
                        if (Math.abs((int) (event.getRawX() - downX)) > 5
                                || Math.abs((int) (event.getRawY() - downY)) > 5)
                            clickormove = false;
                        else
                            clickormove = true;
                        break;
                }
                return false;
            }
        });
    }
}
