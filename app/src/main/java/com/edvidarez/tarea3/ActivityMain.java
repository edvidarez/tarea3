package com.edvidarez.tarea3;

import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {

    ImageButton _btnLike;
    RadioGroup _radioGroup;
    Button _cart;
    int SelectedSize = 0;
    Boolean isAdded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btnLike = findViewById(R.id.main_activity_like);
        _radioGroup = findViewById(R.id.main_activity_color_selector);
        _cart = findViewById(R.id.main_activity_add);




        _btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(ActivityMain.this,"+1 to Vintage Bicycle",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        _cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!isAdded){
                    _cart.setText("Added to Cart");
                    isAdded = true;
                    CoordinatorLayout nv = findViewById(R.id.coordinationLayout);
                    Snackbar bar =  Snackbar.make(nv,"text",Snackbar.LENGTH_LONG);
                    bar.setAction("UNDO", new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            _cart.setText("Add to Cart");
                            isAdded = false;
                        }
                    });
                    bar.show();
                }
                else
                {
                    Toast toast = Toast.makeText(ActivityMain.this,"Item already is in Cart",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

    public void clickOnSizeButton(View v){
        if(SelectedSize!=0){
            Button button = findViewById(SelectedSize);
            Drawable normalButton = getDrawable(R.drawable.border);
            button.setBackground(normalButton);
            button.setTextColor(getResources().getColor(R.color.colorPrimary,null));
        }
        v.getId();
        Drawable selectedButton = getDrawable(R.drawable.button_selected);
        v.setBackground(selectedButton);
        SelectedSize = v.getId();
        Button btn = findViewById(v.getId());
        btn.setTextColor(getResources().getColor(R.color.white,null));
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {

        bundle.putInt("Color",_radioGroup.getCheckedRadioButtonId());
        bundle.putInt("Size",SelectedSize);
        bundle.putBoolean("isAdded",isAdded);
        super.onSaveInstanceState(bundle);
    }



    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);

        _radioGroup.check(bundle.getInt("Color"));
        if(bundle.getInt("Size")!=0) {
            clickOnSizeButton(findViewById(bundle.getInt("Size")));
            SelectedSize = bundle.getInt("Size");
        }
        if(bundle.getBoolean("isAdded")){
            _cart.setText("Added to Cart");
            isAdded = true;
        }
    }
}
