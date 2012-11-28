package ru.devpad.framelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ExampleActivity3 extends Activity {
    private ImageView pic1;
    private ImageView pic2;
    private ImageView pic3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.framelayout_ex3);

        pic1 = (ImageView) findViewById(R.id.pic1);
        pic2 = (ImageView) findViewById(R.id.pic2);
        pic3 = (ImageView) findViewById(R.id.pic3);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pic1:
                pic1.setVisibility(View.GONE);
                pic2.setVisibility(View.VISIBLE);
                pic3.setVisibility(View.GONE);
                break;
            case R.id.pic2:
                pic1.setVisibility(View.GONE);
                pic2.setVisibility(View.GONE);
                pic3.setVisibility(View.VISIBLE);
                break;
            case R.id.pic3:
                pic1.setVisibility(View.VISIBLE);
                pic2.setVisibility(View.GONE);
                pic3.setVisibility(View.GONE);
                break;
        }
    }
}