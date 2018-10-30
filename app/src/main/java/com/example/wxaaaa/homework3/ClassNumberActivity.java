package com.example.wxaaaa.homework3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassNumberActivity extends AppCompatActivity {

    private String[] nameList = {"张三", "李四", "王五", "赵六", "刘备", "关羽", "张飞"};

//    private String[] descs = {"Java爬虫技术小白", "区块链应用开发", "前端开发第一人", "计算机视觉大佬"};

    private int[] imageIds = new int[]{R.drawable.trues, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_number);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        List<Map<String, Object>> itemList = new ArrayList<>();

        for (int i = 0; i < nameList.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("image", imageIds[i]);
            listItem.put("name", nameList[i]);
            itemList.add(listItem);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, itemList, R.layout.item_layout,
                new String[]{"image", "name"}, new int[]{R.id.image, R.id.name});
        ListView list = findViewById(R.id.numberList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                        .setTitle("学生信息")
                        .setIcon(imageIds[i])
                        .setMessage(nameList[i]);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                        .create()
                        .show();
            }
        });
    }
}
