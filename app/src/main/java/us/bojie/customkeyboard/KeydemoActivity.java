package us.bojie.customkeyboard;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.EditText;

import java.lang.reflect.Method;

public class KeydemoActivity extends Activity {
    private Context ctx;
    private Activity act;
    private EditText edit_class;
    private EditText edit_score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        char c;
        int i;
        String[] privinces = {"һ", "河北", "河南", "广东", "台湾", "广西", "西藏", "甘肃", "安徽", "."};
        for (String str : privinces) {
            c = str.toCharArray()[0];
            i = c;
            Log.i("key", "<Key android:codes=\"" + i + "\" android:keyLabel=\"" + c + "\" />");
        }


        edit_class = (EditText) this.findViewById(R.id.edit_class);
        edit_score = (EditText) this.findViewById(R.id.edit_score);


        if (android.os.Build.VERSION.SDK_INT <= 10) {
            edit_class.setInputType(InputType.TYPE_NULL);
        } else {
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(edit_class, false);
                setSoftInputShownOnFocus.invoke(edit_score, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        edit_class.setCustomSelectionActionModeCallback(new Callback() {
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });

        edit_score.setCustomSelectionActionModeCallback(new Callback() {
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });

        ctx = this;
        act = this;
        edit_class.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new KeyboardUtil(act, ctx, edit_class).showChinese();
                return false;
            }
        });

        edit_score.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new KeyboardUtil(act, ctx, edit_score).showNumber();
                return false;
            }
        });

    }
}