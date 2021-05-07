package com.nathanhuisman.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });

    }

    //updates the display
    private void updateInput(String strToAdd){

        //grab old string
        String oldString = display.getText().toString();

        //grab current cursor position
        int cursorPos = display.getSelectionStart();

        //divide up the input into two strings
        String leftString = oldString.substring(0, cursorPos);
        String rightString = oldString.substring(cursorPos);

        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {

            //display new complete string
            display.setText(String.format("%s%s%s", leftString, strToAdd, rightString));
            display.setSelection(cursorPos + 1);
        }
    }

    //numbers
    public void zeroBTN(View v){
        updateInput("0");
    }
    public void oneBTN(View v){
        updateInput("1");
    }
    public void twoBTN(View v){
        updateInput("2");
    }
    public void threeBTN(View v){
        updateInput("3");
    }
    public void fourBTN(View v){
        updateInput("4");
    }
    public void fiveBTN(View v){
        updateInput("5");
    }
    public void sixBTN(View v){
        updateInput("6");
    }
    public void sevenBTN(View v){
        updateInput("7");
    }
    public void eightBTN(View v){
        updateInput("8");
    }
    public void nineBTN(View v){
        updateInput("9");
    }




    //functions
    public void multiplyBTN(View v){
        updateInput("×");
    }
    public void divideBTN(View v){
        updateInput("÷");
    }
    public void additionBTN(View v){
        updateInput("+");
    }
    public void subtractionBTN(View v){
        updateInput("-");
    }
    public void plusMinusBTN(View v){
        updateInput("-");
    }
    public void parenthesesBTN(View v){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closedPar += 1;
            }
        }

        if(openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateInput("(");
            display.setSelection(cursorPos + 1);
        }
        else if(closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateInput(")");
            display.setSelection(cursorPos + 1);
        }


    }
    public void exponentBTN(View v){
        updateInput("^");
    }
    public void decimalBTN(View v){
        updateInput(".");
    }
    public void equalBTN(View v){
        String userExpression = display.getText().toString();

        userExpression = userExpression.replaceAll("÷", "/");
        userExpression = userExpression.replaceAll("×", "*");

        Expression exp = new Expression(userExpression);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }
    public void clearBTN(View v){
        display.setText("");
    }
    public void backspaceBTN(View v){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

}