package com.example.koffie_app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RockSaltRegular extends androidx.appcompat.widget.AppCompatTextView {
    public RockSaltRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RockSaltRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RockSaltRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/rock_salt_regular.ttf");
        setTypeface(tf);
    }
}