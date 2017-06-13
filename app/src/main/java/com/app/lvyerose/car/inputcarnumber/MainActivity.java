package com.app.lvyerose.car.inputcarnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout inputLayout;
    private ArrayList<EditText> editTexts = new ArrayList<>(7);
    private int screenWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initUIMeasure();
        setEdtListener();
    }

    private void setEdtListener() {
        for (int i = 0; i < editTexts.size(); i++) {
            editTexts.get(i).setTag(false);
            final int position = i;
            editTexts.get(i).addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //输入了一个之后自动对其进行跳转
                    if (!TextUtils.isEmpty(s.toString())) {
                        //变化不为空时才进行自动操作
                        if (s.toString().length() == 2) {
                            editTexts.get(position).setText(s.toString().substring(1));
                            editTexts.get(position).setSelection(1);
                        }
                        //设置当前输入框中有内容
                        editTexts.get(position).setTag(true);

                        if (position < 6) {
                            //失去焦点
                            EditText currentEdt = editTexts.get(position);
                            //下一个 获取焦点
                            EditText nextEdt = editTexts.get(position + 1);
                            nextEdt.setFocusable(true);
                            nextEdt.setFocusableInTouchMode(true);
                            nextEdt.requestFocus();
                            nextEdt.findFocus();
                        }
//                        if (TextUtils.equals("", s.toString())) {
//                            editTexts.get(position).setText("");
//                        }
                    }
                }
            });
            editTexts.get(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        //获得焦点了，让其光标在最后
                        if (!TextUtils.isEmpty(editTexts.get(position).getText().toString())) {
                            editTexts.get(position).setSelection(1);
                        }
                    }
                }
            });
            editTexts.get(i).setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        boolean isEmpty = (boolean) editTexts.get(position).getTag();
                        //删除事件
                        if (TextUtils.isEmpty(editTexts.get(position).getText().toString()) && position > 0) {
                            if (isEmpty) {
                                //第一次为空了  但是本次仍然停留在该输入框中
                                editTexts.get(position).setTag(false);
                            } else {
                                EditText previousEdt = editTexts.get(position - 1);
                                previousEdt.setText("");
                                previousEdt.setFocusable(true);
                                previousEdt.setFocusableInTouchMode(true);
                                previousEdt.requestFocus();
                                previousEdt.findFocus();
                                return true;
                            }
                        }
                    }
                    return false;
                }
            });

        }
    }

    private void initUIMeasure() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        //获取 weight 对应控件 等分屏幕宽度所分配的宽度
        int edtWidth = (int) ((screenWidth
                - 2 * getResources().getDimension(R.dimen.input_layout_padding)
                - 2 * 7 * getResources().getDimension(R.dimen.input_edt_padding)
                - 2 * getResources().getDimension(R.dimen.input_edt_round_padding)
                - getResources().getDimension(R.dimen.input_edt_round_width)
        ) / 7);
        //加上控件自身设置的距上下间距 则为正方形高度
        int layoutHeight = (int) (edtWidth + 2 * getResources().getDimension(R.dimen.input_edt_padding));
        inputLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                layoutHeight));
    }

    private void initView() {
        inputLayout = (LinearLayout) findViewById(R.id.input_layout_view);
        editTexts.add((EditText) findViewById(R.id.input_1_edt));
        editTexts.add((EditText) findViewById(R.id.input_2_edt));
        editTexts.add((EditText) findViewById(R.id.input_3_edt));
        editTexts.add((EditText) findViewById(R.id.input_4_edt));
        editTexts.add((EditText) findViewById(R.id.input_5_edt));
        editTexts.add((EditText) findViewById(R.id.input_6_edt));
        editTexts.add((EditText) findViewById(R.id.input_7_edt));
    }
}
