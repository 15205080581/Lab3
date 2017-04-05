package edu.fjnu.cse.lab3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//参考自http://jingyan.baidu.com/article/1612d5007dd2e9e20f1eee6e.html


public class MainActivity extends AppCompatActivity {

    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] imageIds = new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    private TextView txt;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.main);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(onClick);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, xmlactivity.class);
                startActivity(intent);
            }
        });
        txt = (TextView) findViewById(R.id.btn2);
        registerForContextMenu(txt);
        setContentView(R.layout.activity_main);
        //创建一个List集合，List集合的元素是Map
        List<Map<String,Object>> listItems =
                new ArrayList<Map<String,Object>>();
        for (int i=0;i<names.length;i++)
        {
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("header",imageIds[i]);
            listItem.put("personName",names[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simple_item,
                new String[] {"personName","header"},
                new int[] {R.id.name,R.id.header});
        final ListView list = (ListView) findViewById(R.id.mylist);
        //为ListView设置Adapater
        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String,String> map = (HashMap<String,String>)list.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(),names[i],Toast.LENGTH_SHORT).show();
            }
        });
    }

    public View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("ANDROID APP")
                    .setView(R.layout.login)
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Sign in", null)
                    .show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator=new MenuInflater(this);
        //装填R.menu.my_menu对应的菜单，并添加到menu中
        inflator.inflate(R.menu.menu_main, menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_res_test, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View source,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        //装填R.menu.context对应的菜单，并添加到menu中
        inflater.inflate(R.menu.context , menu);
        menu.setHeaderTitle("请选择背景色");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        switch (mi.getItemId()) {
            // 普通箱单被点击处所进行的操作。
            case R.id.plain_item:
                Toast.makeText(this, "你单击了普通菜单", Toast.LENGTH_LONG).show();
                break;
            // 为子菜单的子项定义被点击时所进行的操作。
            case R.id.font_10:
                txt.setTextSize(10 * 2);
                break;
            case R.id.font_12:
                txt.setTextSize(12 * 2);
                break;
            case R.id.font_14:
                txt.setTextSize(14 * 2);
                break;
            case R.id.font_16:
                txt.setTextSize(16 * 2);
                break;
            case R.id.font_18:
                txt.setTextSize(18 * 2);
                break;
            case R.id.red_font:
                txt.setTextSize(Color.RED);
                mi.setChecked(true);
                break;
            case R.id.green_font:
                txt.setTextSize(Color.GREEN);
                mi.setChecked(true);
                break;
            case R.id.blue_font:
                txt.setTextSize(Color.BLUE);
                mi.setChecked(true);
                break;
        }
        return true;
    }


    @Override
    public boolean onContextItemSelected(MenuItem mi) {
        mi.setChecked(true);
        switch (mi.getItemId()) {
            case R.id.red:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.RED);
                break;
            case R.id.green:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blue:
                mi.setChecked(true);
                txt.setBackgroundColor(Color.BLUE);
                break;
        }
        return true;
    }


}
