package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView expression, result;
    Button btn_lp, btn_rp, btn_mod, btn_clear,
            btn_7, btn_8, btn_9, btn_div,
            btn_4, btn_5, btn_6, btn_mul,
            btn_1, btn_2, btn_3, btn_sub,
            btn_0, btn_dot, btn_equ, btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = findViewById(R.id.expression);
        result = findViewById(R.id.result);

        assign(btn_lp, R.id.btn_lp);
        assign(btn_rp, R.id.btn_rp);
        assign(btn_mod, R.id.btn_mod);
        assign(btn_clear, R.id.btn_clear);
        assign(btn_add, R.id.btn_add);
        assign(btn_sub, R.id.btn_sub);
        assign(btn_mul, R.id.btn_mul);
        assign(btn_div, R.id.btn_div);
        assign(btn_equ, R.id.btn_equ);
        assign(btn_dot, R.id.btn_dot);

        assign(btn_0, R.id.btn_0);
        assign(btn_1, R.id.btn_1);
        assign(btn_2, R.id.btn_2);
        assign(btn_3, R.id.btn_3);
        assign(btn_4, R.id.btn_4);
        assign(btn_5, R.id.btn_5);
        assign(btn_6, R.id.btn_6);
        assign(btn_7, R.id.btn_7);
        assign(btn_8, R.id.btn_8);
        assign(btn_9, R.id.btn_9);
    }

    public void assign(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;

        String seq = btn.getText().toString();
        String str = expression.getText().toString();

        if(seq.equals("AC")){
            expression.setText("");
            result.setText("0");
            return;
        }
        else if(seq.equals("=")){
            result.setText(getResult(str));
            return;
        }
        else{
            str += seq;
        }

        expression.setText(str);
    }

    public String getResult(String str){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            return context.evaluateString(scriptable, str, "Javascript", 1, null).toString();

        }
        catch(Exception ex){
            return "error";
        }
    }

}