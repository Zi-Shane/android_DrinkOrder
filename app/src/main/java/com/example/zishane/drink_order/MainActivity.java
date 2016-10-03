package com.example.zishane.drink_order;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,
                     AdapterView.OnItemSelectedListener,
                     DatePickerDialog.OnDateSetListener,
                     TimePickerDialog.OnTimeSetListener
{

    private Spinner spn_drinks;
    private ListView lv_temper;
    private TextView tv_order;

    String[] temper1 = {"冰", "去冰", "溫"};
    String[] temper2 = {"冰", "去冰"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn_drinks = (Spinner) findViewById(R.id.spn_drinks);
        lv_temper = (ListView) findViewById(R.id.lv_temper);

        tv_order = (TextView) findViewById(R.id.tv_order);

        spn_drinks.setOnItemSelectedListener(this);
        lv_temper.setOnItemClickListener(this);

        String[] drinks = {"珍珠奶茶", "布丁奶茶", "檸檬汁", "酪梨牛奶"};

        //Spinner未下拉樣式
        ArrayAdapter<String> drinks_txt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, drinks);
        //Spinner下拉選項樣式
        drinks_txt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_drinks.setAdapter(drinks_txt);
    }

    public void order(View v) {
        setOrder(temper[position]);

        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("訂購項目")
                .setIcon(android.R.drawable.presence_away)
                .setMessage(msg)
                .setPositiveButton("確定", null)
                .setNegativeButton("取消", null)
                .setNeutralButton("重選", null)
                .show();

        //Calendar c = Calendar.getInstance();
    }

    int position;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //ListView
        this.position = position;
        setOrder(temper[position]);
    }

    String msg;
    private void setOrder(String s) {
        msg = spn_drinks.getSelectedItem()+ "," + temper[position];
        tv_order.setText(msg);
    }

    String[] temper;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Spinner


        if (position == 2 || position == 3) {
            temper = temper2;
        }else {
            temper = temper1;
        }

        //ListView的顯示樣式
        ArrayAdapter<String> temper_txt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, temper);

        lv_temper.setAdapter(temper_txt);

        if (spn_drinks.getSelectedItemPosition() == 2 || spn_drinks.getSelectedItemPosition() == 3){
            Toast tos = Toast.makeText(this, "沒有  " + temper1[2], Toast.LENGTH_LONG);
                    tos.setGravity(Gravity.RIGHT | Gravity.TOP, 50, 50);
                    tos.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Spinner
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
