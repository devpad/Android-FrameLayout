package ru.devpad.framelayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ViewFlipper;

public class DevPadActivity extends Activity {
    private int position = 0;
    private int[] stek = new int[100];

    public static final String POSITION = "pos";
    public static final String STEK = "stek";
    private ViewFlipper flipper;
    private LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flipper = new ViewFlipper(this);
        flipper.setLayoutParams(new ViewFlipper.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        setContentView(flipper);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (savedInstanceState != null) {
            stek = savedInstanceState.getIntArray(STEK);
            position = savedInstanceState.getInt(POSITION);
        } else {
            stek[position] = R.layout.main;
        }
        setLayout(stek[position]);
    }

    public void setExample(View view) {
        switch (view.getId()) {
            case R.id.framelayout_ex3:
                startActivity(new Intent(this, ExampleActivity3.class));
                break;
            case R.id.framelayout_ex6:
                startActivity(new Intent(this, ExampleActivity6.class));
                break;
        }
    }

    public void onClick(View view) {
        int layout = getResources().getIdentifier(getResources().getResourceEntryName(view.getId()), "layout", getPackageName());
        if (layout > 0) {
            nextLayout(layout);
            Button button = (Button) view;
            setTitle(button.getText().toString());
        }
    }

    public boolean onSearchRequested() {
        return false;
    }

    private void onExit() {
        finish();
        moveTaskToBack(true);
        System.runFinalizersOnExit(true);
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            prevLayout();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setLayout(int layout) {
        stek[position] = layout;
        flipper.removeAllViews();
        flipper.addView(inflater.inflate(layout, null));
    }

    private void nextLayout(int layout) {
        position++;
        setLayout(layout);
        flipper.setInAnimation(inFromRightAnimation());
        flipper.setOutAnimation(outToLeftAnimation());
        flipper.showNext();
    }

    private void prevLayout() {
        position--;
        if (position < 0) onExit();
        else {
            int layout = stek[position];
            try {
                int id = getResources().getIdentifier(getResources().getResourceEntryName(stek[position]), "id", getPackageName());
                if (id != 0) {
                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    ViewGroup vg = (ViewGroup) layoutInflater.inflate(stek[position - 1], null);
                    Button b = (Button) vg.findViewById(id);
                    if (b != null) {
                        setTitle(b.getText().toString());
                    } else setTitle(R.string.app_name);
                } else setTitle(R.string.app_name);
            } catch (ArrayIndexOutOfBoundsException ae) {
                setTitle(R.string.app_name);
            }
            setLayout(layout);
            flipper.setInAnimation(inFromLeftAnimation());
            flipper.setOutAnimation(outToRightAnimation());
            flipper.showNext();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(POSITION, position);
        outState.putIntArray(STEK, stek);
        super.onSaveInstanceState(outState);
    }

    private Animation inFromTopAnimation() {

        Animation inFromTop = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromTop.setDuration(150);
        inFromTop.setInterpolator(new AccelerateInterpolator());
        return inFromTop;
    }

    private Animation outToBottomAnimation() {
        Animation outtoBottom = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, +1.0f
        );
        outtoBottom.setDuration(150);
        outtoBottom.setInterpolator(new AccelerateInterpolator());
        return outtoBottom;
    }

    private Animation outToTopAnimation() {
        Animation inFromTop = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromTop.setDuration(150);
        inFromTop.setInterpolator(new AccelerateInterpolator());
        return inFromTop;
    }

    private Animation outFromBottomAnimation() {
        Animation outFromBottom = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
        );
        outFromBottom.setDuration(150);
        outFromBottom.setInterpolator(new AccelerateInterpolator());
        return outFromBottom;
    }

    private Animation inFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromRight.setDuration(50);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        outtoLeft.setDuration(50);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromLeft.setDuration(50);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        outtoRight.setDuration(50);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }
}