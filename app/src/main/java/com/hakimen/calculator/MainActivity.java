package com.hakimen.calculator;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView value;
    TextView lastVal;
    Button backspace;
    Button plus;
    Button mult;
    Button sub;
    Button div;
    Button negate;
    Button percent;
    Button equal;
    Button clear;
    List<Button> numberButtons = new ArrayList<>();

    String current = "";
    String last = "";

    enum Op {
        ADD,
        SUB,
        DIV,
        MULT,
        PERCENT,
        NONE
    }

    Op op = Op.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = findViewById(R.id.value);
        lastVal = findViewById(R.id.last_val);
        backspace = findViewById(R.id.buttonBackspace);
        plus = findViewById(R.id.buttonPlus);
        negate = findViewById(R.id.buttonNegate);
        div = findViewById(R.id.buttonDiv);
        percent = findViewById(R.id.buttonPercentage);
        sub = findViewById(R.id.buttonSub);
        equal = findViewById(R.id.buttonEq);
        mult = findViewById(R.id.buttonMult);
        clear = findViewById(R.id.buttonClear);


        numberButtons.add(findViewById(R.id.button0));
        numberButtons.add(findViewById(R.id.button1));
        numberButtons.add(findViewById(R.id.button2));
        numberButtons.add(findViewById(R.id.button3));
        numberButtons.add(findViewById(R.id.button4));
        numberButtons.add(findViewById(R.id.button5));
        numberButtons.add(findViewById(R.id.button6));
        numberButtons.add(findViewById(R.id.button7));
        numberButtons.add(findViewById(R.id.button8));
        numberButtons.add(findViewById(R.id.button9));
        numberButtons.add(findViewById(R.id.button9));
        numberButtons.add(findViewById(R.id.buttonDecimal));

        for (Button button : numberButtons) {
            button.setOnClickListener(v -> {
                if(current.isEmpty()){
                    current = ((Button)v).getText().toString();
                } else {
                    current += ((Button)v).getText().toString();
                }
                updateDisplay();
            });
        }

        backspace.setOnClickListener(v -> {
            if(!current.isEmpty()){
                current = current.substring(0, current.length()-1);
                updateDisplay();
            }
        });

        plus.setOnClickListener(v -> setOpAndClear(Op.ADD));
        div.setOnClickListener(v -> setOpAndClear(Op.DIV));
        mult.setOnClickListener(v -> setOpAndClear(Op.MULT));
        sub.setOnClickListener(v -> setOpAndClear(Op.SUB));
        percent.setOnClickListener(v -> setOpAndClear(Op.PERCENT));
        equal.setOnClickListener(v -> execCalc());

        negate.setOnClickListener(v -> {
            current = Float.parseFloat(current.isEmpty() ? "0" : current) * -1 + "";
            updateDisplay();
        });


        clear.setOnClickListener(v -> {
            this.op = Op.NONE;
            this.last = "";
            this.current = "";
            updateDisplay();
        });


    }


    private void setOpAndClear(Op op){
        last = current;
        current = "";
        this.op = op;
        updateDisplay();
    }

    private void execCalc(){
       try{
           float val = Float.parseFloat(current);
           float old = Float.parseFloat(last);
           last = current;

           float res = 0;

           switch (op){
               case ADD:
                   res = old + val;
                   break;
               case SUB:
                   res = old - val;
                   break;
               case DIV:
                   res = old / val;
                   break;
               case MULT:
                   res = old * val;
                   break;
               case PERCENT:
                   res = ((old/100) * val);
                   break;
           }

           current = "" + res;
           updateDisplay();
       }catch (Exception ignored){

       }
    }

    @SuppressLint("SetTextI18n")
    private void updateDisplay() {
        lastVal.setText(Float.parseFloat(last.isEmpty() ? "0" : last) + "");
        value.setText(Float.parseFloat(current.isEmpty() ? "0" : current) + "");
    }
}