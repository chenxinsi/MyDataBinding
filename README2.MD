# Android_Data_Binding-----------------(2)
>>BUTTON onclick()
```
<data>
    ...
    <variable  name = "adapter" type = "com.liangyu.mydatabinding.MainActivity.OnUserClickListener" />
</data>
...
<Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="click1"
        android:onClick="@{adapter.onClick1}"
        />
<Button
```


```
public interface OnUserClickListener{
        public void onClick1(View view);
        public void onClick2(View view);
        public void onClick3(View view);
}

binding.setAdapter(new OnUserClickListener(){
            @Override
            public void onClick1(View view) {
                Toast.makeText(MainActivity.this,"onClick1",Toast.LENGTH_SHORT).show();
            }
}
```