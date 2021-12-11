package com.yara.abouelenin.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView title;
    EditText noOfSeats;
    ImageView img , img2;
    SeekBar sb;
    Spinner foodSp;
    String movieName;
    int[] imgIds = {R.drawable.hpall,R.drawable.hp1, R.drawable.hp2, R.drawable.hp3, R.drawable.hp4, R.drawable.hp5, R.drawable.hp6, R.drawable.hp7, R.drawable.hp8};
    int[] foodimgIds = {R.drawable.imgall2,R.drawable.img1,R.drawable.img2,R.drawable.img3 };

    ArrayList<String> foodItems;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        prepareData();


        title= findViewById(R.id.Title);
        noOfSeats= findViewById(R.id.seats);
        img= findViewById(R.id.img);
        img2 = findViewById(R.id.img2);
        sb = findViewById(R.id.seekBar1);
        foodSp= findViewById(R.id.spinner1);

        //Custom Spinner
        CustomerSpinnerAdapter adapter = new CustomerSpinnerAdapter(this, foodItems , foodimgIds);
        foodSp.setAdapter(adapter);

        foodSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                TextView tvSelected = view.findViewById(R.id.tvItem);
                Spinner sipnnerTemp = (Spinner) parent;
                TextView selectedItemTextView = sipnnerTemp.getSelectedView().findViewById(R.id.tvItem);

                img2.setImageResource(foodimgIds[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                img.setImageResource(imgIds[i]);
                displayToast(String.valueOf("Movie Number " + i));
                movieName = "Movie " + i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        noOfSeats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //displayToast("Please insert a number");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    //ANIMATION
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(800);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        title.startAnimation(anim);

    }

    public void prepareData() {
        foodItems = new ArrayList<>();
        Collections.addAll(foodItems, "Choose a free snack","Popcorn", "Chocolate", "Cotton candy");

    }

    public void onClick(View view) {

        if(view.getId() == R.id.submit) {
            intent = new Intent(this, SecActivity.class);

            //Warning message for leaving number of seats field empty
            String numOfseats = noOfSeats.getText().toString();

            if( numOfseats.length() == 0 ) {
                displayToast("Please insert number of seats");
                Log.d("Error" , "Empty num");
            }

            else{
                int num1 = Integer.parseInt(noOfSeats.getText().toString());
                int num2 = 20;
                String movie = movieName;

                Bundle b = new Bundle();
                b.putInt("num1" , num1);
                b.putInt("num2" , num2);
                b.putString("movie" , movie);
                intent.putExtras(b);

                startActivity(intent);
            }
        }



    }

    private void displayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



}