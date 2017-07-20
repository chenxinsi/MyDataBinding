package com.liangyu.mydatabinding;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liangyu.mydatabinding.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    Person person = new Person("xinsi",25);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //binding.setPerson(person);
        User user = new User(null,"chen");
        binding.setUser(user);
        binding.setAdapter(new OnUserClickListener(){
            @Override
            public void onClick1(View view) {
                Toast.makeText(MainActivity.this,"onClick1",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick2(View view) {
                Toast.makeText(MainActivity.this,"onClick2",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick3(View view) {
                Toast.makeText(MainActivity.this,"onClick3",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnUserClickListener{

        public void onClick1(View view);
        public void onClick2(View view);
        public void onClick3(View view);
    }
}
