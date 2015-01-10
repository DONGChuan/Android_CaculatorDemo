package com.example.dchuan.calculatordemp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    Button btn_point, btn_clear, btn_del;
    Button btn_plus, btn_sub, btn_multiply, btn_divide, btn_equal; // +, -, x, ÷, =
    EditText et_input;

    boolean clear_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);

        et_input = (EditText) findViewById(R.id.et_input);

        // Set OnClickListener for all the buttons
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String str = et_input.getText().toString();
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)view).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_sub:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(clear_flag) {
                    clear_flag = false;
                    et_input.setText("");
                }
                et_input.setText(str+" "+((Button)view).getText()+" ");
                break;
            case R.id.btn_clear:
                clear_flag = false;
                str = "";
                et_input.setText("");
            case R.id.btn_del:
                if(clear_flag) {
                    clear_flag = false;
                    et_input.setText("");
                } else if (str!=null && str!="") {
                    et_input.setText(str.substring(0, str.length()-1));
                }
                break;
            case R.id.btn_equal:
                getResult();
                break;
        }
    }

    private void getResult() {

        String str = et_input.getText().toString();

        if(str == null || str == "") {
            return;
        }

        if(!str.contains(" ")) {
            return;
        }

        if(clear_flag) {
            clear_flag = false;
            return;
        }
        clear_flag = true;

        double result = 0;
        String strA = str.substring(0, str.indexOf(" "));
        String op = str.substring(str.indexOf(" ") + 1, str.indexOf(" ") + 2);
        String strB = str.substring(str.indexOf(" ") + 3);
        if(!strA.equals("") && !strB.equals("")) {
            double d1 = Double.parseDouble(strA);
            double d2 = Double.parseDouble(strB);
            System.out.print(op);
            if(op.equals("+")){
                result = d1+d2;
            } else if(op.equals("-")) {
                result = d1-d2;
            } else if(op.equals("×")) {
                result = d1*d2;
            } else if(op.equals("÷")) {
                if(d2==0) {
                    result = 0;
                } else {
                    result = d1/d2;
                }
            }
            if(!strA.contains(".") && !strB.contains(".") && !op.equals(" ÷ ")){
                int result_int = (int)result;
                et_input.setText(result_int + "");
            } else {
                et_input.setText(result + "");
            }
        } else if(!strA.equals("") && strB.equals("")) {
            et_input.setText(str + "");
        } else if(strA.equals("") && !strB.equals("")) {
            double d2 = Double.parseDouble(strB);
            if(op.equals("+")){
                result = 0+d2;
            } else if(op.equals("-")) {
                result = 0-d2;
            } else if(op.equals("×")) {
                result = 0;
            } else if(op.equals("÷")) {
                result = 0;
            }
            if(strB.contains(".")) {
                int result_int = (int)result;
                et_input.setText(result_int + "");
            } else {
                et_input.setText(result + "");
            }
        } else if(strA.equals("") && strB.equals("")) {
            et_input.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
