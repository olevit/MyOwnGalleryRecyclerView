package com.example.myowngalleryrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PictureModel> pictures = new ArrayList<>();

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String externalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    File appDirectory = new File(externalStorageDirectoryPath + "/Android/data/com.example.omnemixsize/files/Pictures/");

    if (!appDirectory.exists()) {
        appDirectory.mkdir();
    }

    File[] files = appDirectory.listFiles();
    for (int i = 0; i < files.length; i++) {
        PictureModel pictureModel = new PictureModel();
        pictureModel.setTitle("Your size is" + i);
        pictureModel.setUrl(files[i].getAbsolutePath());
        pictures.add(pictureModel);
    }

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
    recyclerView.setHasFixedSize(true);
    GalleryAdapter adapter = new GalleryAdapter(MainActivity.this, pictures);
    recyclerView.setAdapter(adapter);

    /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
            new RecyclerItemClickListener.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {



                }
            }));*/

    }

}