package ru.devpad.framelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ExampleActivity6 extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("FrameLayout programmatically");

        ImageView image = new ImageView(this);
        image.setLayoutParams(
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT
                )
        );
        image.setScaleType(ImageView.ScaleType.CENTER);
        image.setImageResource(R.drawable.pic2);

        TextView description = new TextView(this);
        FrameLayout.LayoutParams paramDescription = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        paramDescription.bottomMargin = 20;
        paramDescription.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        description.setLayoutParams(paramDescription);
        description.setPadding(12, 12, 12, 12);
        description.setBackgroundColor(0xAA000000);
        description.setTextColor(0xffffffff);
        description.setText("Клёвые туфли");

        ImageView googlePlus = new ImageView(this);
        FrameLayout.LayoutParams paramGooglePlus = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        paramGooglePlus.setMargins(12, 12, 12, 12);
        paramGooglePlus.gravity = Gravity.RIGHT | Gravity.TOP;
        googlePlus.setLayoutParams(paramGooglePlus);
        googlePlus.setImageResource(R.drawable.google_plus);

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT
                )
        );
        frameLayout.addView(image);
        frameLayout.addView(description);
        frameLayout.addView(googlePlus);

        setContentView(frameLayout);
    }
}