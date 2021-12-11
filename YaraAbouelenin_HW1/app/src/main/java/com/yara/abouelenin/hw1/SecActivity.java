package com.yara.abouelenin.hw1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecActivity extends AppCompatActivity {

    Intent intent;
    String movie;
    int total;
    Dialog customDialog;
    TextView result;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_sec);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();
        Bundle b = intent.getExtras();
        int num1 = b.getInt("num1");
        int num2 = b.getInt("num2");
        movie = b.getString("movie");

        total = num1 * num2;



        makeAndShowDialog("Cost: " + total + "TL");

        //Dialog
        createDailog("Confirmed cost : " + total + "TL");

    }

    public void onClick(View view) {

        Intent intent = new Intent();
        finish();
    }


    private void makeAndShowDialog(String message) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);
        box.setTitle("You chose: " + movie);
        box.setMessage(message);

        box.setPositiveButton("Close and confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                customDialog.show();

            }
        });

        box.create();
        box.show();
    }

    public void createDailog(String message){
        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.dialog);
        btn =customDialog.findViewById(R.id.btn);
        result = customDialog.findViewById(R.id.result);

        result.setText("" +  message);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });


    }

}