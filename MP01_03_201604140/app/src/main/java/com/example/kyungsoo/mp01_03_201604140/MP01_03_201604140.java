package com.example.kyungsoo.mp01_03_201604140;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.w3c.dom.Text;

public class MP01_03_201604140 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp01_03_201604140);
    }

    public void onClick_0(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            return;
        else
            result.setText(r+"0");
    }

    public void onClick_1(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("1");
        else
            result.setText(r+"1");
    }

    public void onClick_2(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("2");
        else
            result.setText(r+"2");
    }

    public void onClick_3(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("3");
        else
            result.setText(r+"3");
    }

    public void onClick_4(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("4");
        else
            result.setText(r+"4");
    }

    public void onClick_5(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("5");
        else
            result.setText(r+"5");
    }

    public void onClick_6(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("6");
        else
            result.setText(r+"6");
    }

    public void onClick_7(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("7");
        else
            result.setText(r+"7");
    }

    public void onClick_8(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("8");
        else
            result.setText(r+"8");
    }

    public void onClick_9(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.equals("0"))// 0일때는 지우고 값을 입력
            result.setText("9");
        else
            result.setText(r+"9");
    }

    public void onClick_slash(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.charAt(r.length()-1) == '*' || r.charAt(r.length()-1) == '/' ||
                r.charAt(r.length()-1) == '+' || r.charAt(r.length()-1) == '-' ||
                r.charAt(r.length()-1) == '.' )// 마지막 index가 점 또는 사칙연산일 경우
            return;
        result.setText(r+"/");
    }

    public void onClick_mul(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();
        if(r.charAt(r.length()-1) == '*' || r.charAt(r.length()-1) == '/' ||
                r.charAt(r.length()-1) == '+' || r.charAt(r.length()-1) == '-' ||
                r.charAt(r.length()-1) == '.' )// 마지막 index가 점 또는 사칙연산일 경우
            return;
        result.setText(r+"*");
    }

    public void onClick_sub(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.charAt(r.length()-1) == '*' || r.charAt(r.length()-1) == '/' ||
                r.charAt(r.length()-1) == '+' || r.charAt(r.length()-1) == '-' ||
                r.charAt(r.length()-1) == '.' )// 마지막 index가 점 또는 사칙연산일 경우
            return;
        result.setText(r+"-");
    }

    public void onClick_dot(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.charAt(r.length()-1) == '*' || r.charAt(r.length()-1) == '/' ||
                r.charAt(r.length()-1) == '+' || r.charAt(r.length()-1) == '-' ||
                r.charAt(r.length()-1) == '.' )// 마지막 index가 점 또는 사칙연산일 경우
            return;
        else if(r.contains(".")) { // 이미 점이 있는 경우
            // 점이 있는데, 사칙연산을 포함할 경우
            if(r.contains("+") || r.contains("-") || r.contains("*") || r.contains("/")) {
                String[] temp_r = r.split("\\+|-|\\*|/"); // 사칙연산을 기준으로 점이 있는지 검사를 위한 임시 변수
                // 마지막 String이 점을 포함하고 있을 경우
                if(temp_r[temp_r.length-1].contains(".")) {
                    return;
                }
                else result.setText(r+".");
            }
            else return; // 점이 있는데, 사칙연산을 포함하지 않을 경우
        }
        else
            result.setText(r+".");
    }

    public void onClick_clear(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        result.setText("0");
    }

    public void onClick_add(View view) {
        TextView result = (TextView)findViewById(R.id.result);
        String r = result.getText().toString();

        if(r.charAt(r.length()-1) == '*' || r.charAt(r.length()-1) == '/' ||
                r.charAt(r.length()-1) == '+' || r.charAt(r.length()-1) == '-' ||
                r.charAt(r.length()-1) == '.' )// 마지막 index가 점 또는 사칙연산일 경우
            return;
        result.setText(r+"+");
    }

    public void onClick_equal(View view) {
        try{
            TextView result = (TextView)findViewById(R.id.result);
            String r = result.getText().toString();

            Expression e = new ExpressionBuilder(r).build();
            double result_val = e.evaluate();
            String valtostring = String.valueOf(result_val);
            if(valtostring.contains("."))// 소수점이 있는 경우
                if(result_val % 1 == 0) {
                    valtostring = String.valueOf((int)result_val);
                    result.setText(valtostring);
                }
                else
                    result.setText(valtostring);
            else { // 소수점이 없는 경우
                valtostring = String.valueOf((int)result_val);
                result.setText(valtostring);
            }
        }
        catch (ArithmeticException ee) {
            return;
            // 0으로 나누었을 때 등 오류
        }
    }
}
