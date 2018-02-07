package com.example.yzj.guidetest;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Button button1,button2,button3,button4,button5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                //获取控件宽高
                Rect rect = new Rect();
                button1.getLocalVisibleRect(rect);

                //获取控件XY坐标
                int[] position = new int[2];
                button1.getLocationOnScreen(position);

                showGuideView(rect, position);
                //showDialog(rect, position);
                break;
            case R.id.button2:
                //获取控件宽高
                Rect rect2 = new Rect();
                button2.getLocalVisibleRect(rect2);

                //获取控件XY坐标
                int[] position2 = new int[2];
                button2.getLocationOnScreen(position2);

                showGuideView(rect2, position2);
                //showDialog(rect2, position2);
                break;
            case R.id.button3:
                //获取控件宽高
                Rect rect3 = new Rect();
                button3.getLocalVisibleRect(rect3);

                //获取控件XY坐标
                int[] position3 = new int[2];
                button3.getLocationOnScreen(position3);

                showGuideView(rect3, position3);
                //showDialog(rect3, position3);
                break;
            case R.id.button4:
                //获取控件宽高
                Rect rect4 = new Rect();
                button4.getLocalVisibleRect(rect4);

                //获取控件XY坐标
                int[] position4 = new int[2];
                button4.getLocationOnScreen(position4);

                showGuideView(rect4, position4);
                //showDialog(rect4, position4);
                break;
            case R.id.button5:
                //获取控件宽高
                Rect rect5 = new Rect();
                button5.getLocalVisibleRect(rect5);

                //获取控件XY坐标
                int[] position5 = new int[2];
                button5.getLocationOnScreen(position5);

                showGuideView(rect5, position5);
                //showDialog(rect5, position5);
            default:
                break;
        }
    }

    public void showGuideView(Rect rect, int[] position){
        View view = getWindow().getDecorView().findViewById(R.id.activity_main);
        if (view == null) return;

        ViewParent viewParent = view.getParent();
        if (viewParent instanceof FrameLayout) {
            final FrameLayout frameParent = (FrameLayout) viewParent;//整个父布局

            final LinearLayout linearLayout = new LinearLayout(this);//新建一个LinearLayout
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor(0x88000000);//背景设置灰色透明
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frameParent.removeView(linearLayout);
                }
            });

            //上布局
            ImageView topGuideView = new ImageView(this);
            topGuideView.setLayoutParams(new ViewGroup.LayoutParams(rect.width(), rect.height()));
            topGuideView.setBackgroundResource(R.drawable.isee);
            topGuideView.setX(position[0] - rect.width()*3/2);
            topGuideView.setY(position[1] - getStatusBarHeight(MainActivity.this));

            //中布局
            ImageView CenterGuideView = new ImageView(this);
            CenterGuideView.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            CenterGuideView.setBackgroundResource(R.drawable.arrow_right_top);
            CenterGuideView.setX(position[0] - rect.width()*2);
            CenterGuideView.setY(position[1] + topGuideView.getHeight());

            //下布局
            TextView BottomGuideView = new TextView(this);
            BottomGuideView.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            BottomGuideView.setText("这是显示的引导文字");
            BottomGuideView.setTextColor(0xFFFFFFFF);
            BottomGuideView.setX(position[0] - rect.width()*2);
            BottomGuideView.setY(position[1] + CenterGuideView.getHeight());

            linearLayout.addView(topGuideView);
            linearLayout.addView(CenterGuideView);
            linearLayout.addView(BottomGuideView);
            frameParent.addView(linearLayout);
        }
    }

    public void showDialog(Rect rect, int[] position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("控件坐标");
        //y坐标为可见的状态栏高度+可见的标题栏高度+控件左上角到标题栏底部的距离
        builder.setMessage("      宽："+rect.width()+" px\n      高："+rect.height()+" px\nx坐标："+position[0]+"\ny坐标："+position[1]);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //获取状态栏高度
    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
