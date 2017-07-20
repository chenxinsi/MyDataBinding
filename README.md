# Android_Data_Binding-----------------(1)
>### 主要优势
```
  1.去除Activity/Fragment中的UI代码
  2.性能超过手写,安全
  3.执行在主线程中
```

>### 类似方案
```
  ButterKnife
  Android Annotations
  RoboBinding
```

>### 使用- Gradle
```
  App module - build.gradle
  android{
    ...
    dataBinding{
       enabled = true
    }
  }
```


>### 消除View的查找
```
我们需要通过<layout>标签包裹原有的布局
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools">
    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingLeft="@dimen/activity_horizontal_margin"
            android:paddingRight="@dimen/activity_horizontal_margin"
            android:paddingTop="@dimen/activity_vertical_margin"
            android:paddingBottom="@dimen/activity_vertical_margin"
            tools:context=".MainActivity">
        <TextView
                android:id="@+id/tvLabel"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
    </RelativeLayout>
</layout>
```
>>#### 使用binding来填充布局
```
public class MainActivity extends AppCompatActivity {
    // Store the binding
    private ActivityMainBinding binding;
//  ActivityMainBinding类 会根据activity_main.xml自动生成
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the content view (replacing `setContentView`)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // Store the field now if you'd like without any need for casting
        TextView tvLabel = binding.tvLabel;
        tvLabel.setAllCaps(true);
        // Or use the binding to update views directly on the binding
        binding.tvLabel.setText("Foo");
    }
}
```
>>#### 一种绑定数据的方法
```
public class User {
   public String firstName;
   public String lastName;
//
   public User(String firstName, String lastName){
           this.firstName = firstName;
           this.lastName = lastName;
       }
//
       public String getFirstName() {
           return firstName;
       }
//
       public String getLastName() {
           return lastName;
       }
}
```
>>#### 从对象中加载数据
需要在data下声明 variable节点
```
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
       <variable name="user" type="com.example.User"/>
    </data>
    <!-- ... rest of layout here -->
</layout>
```

>>#### 对象的属性写在view中
```
<TextView
    android:id="@+id/tvFullName"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text='@{user.getFirstName + " " + user.getLastName}' />
```
>>#### 给user赋值bind view
```
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the `activity_main` layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // Create or access the data to bind
        User user = new User("Sarah", "Gibbons");
        // Attach the user to the binding
        binding.setUser(user);
    }
}
```
到这里,运行app,你会发现数据绑定在了视图上了.

>>## 主要性能
```
1. 零反射
2. findViewById需要遍历整个ViewGroup,现在之需要做一次
3. 使用位标记来检验更新
4. 数据改变在下一次批量更新才会触发
5. 缓存表达式:
      a ? (b ? c : d) : e
```
>>## 表达式 - 例子 - 最佳实践
```
Margin @dimen + @dimen
android:text = "@{String.valueOf(index + 1)}"
visibility = "@{age &lt; 13 ? View.GONE : View.VISIBle}"
transitionName = '@{"image_" + id}'
{
  1.结合ViewModel使用
  2.保持表达式简洁简单
}
```
>>## 自动空指针检查,避免空指针
```
{user.name} -> null
{user.age} -> 0
```

github:
https://github.com/chenxinsi/DataBinding