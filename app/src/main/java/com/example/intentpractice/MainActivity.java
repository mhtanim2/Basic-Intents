package com.example.intentpractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.intentpractice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int reqCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = binding.etNum.getText().toString();
                String getIndex = num.charAt(2) + "" + num.charAt(4);
                int n = Integer.parseInt(getIndex);
                int newMod = n % 3;
                Intent intent = new Intent(MainActivity.this, SecodnActivity.class);
                //intent.putExtra("value",Integer.toString(n));
                intent.putExtra("value", getIndex);
                intent.putExtra("MOD", Integer.toString(newMod));
                //startActivity(intent);
                startActivityForResult(intent, reqCode);
            }
        });
        binding.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating Sending type intent
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String send = "This is a application";
                String sub = "Implesit Intent";
                //Putting the text
                i.putExtra(Intent.EXTRA_TEXT, send);
                i.putExtra(Intent.EXTRA_SUBJECT, sub);
                //Chosing the file
                startActivity(Intent.createChooser(i, "Sharing the text==> "));
            }
        });
        //permission for the Camera
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 1);
        }
        //Opening Camera
        binding.btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
        //Opening Gallery
        binding.btGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("*image/");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "title"), 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == reqCode) {
            if (resultCode == RESULT_OK) {
                String newData = data.getStringExtra("NewMod");
                binding.tvGetResult.setText(newData);
            } else {
                Toast.makeText(getApplicationContext(), "Result is not OK", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == 1) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            binding.img.setImageBitmap(bitmap);

        }
        if (requestCode == 2) {
            Uri uri = data.getData();
            binding.imgGal.setImageURI(uri);
            binding.btGallery.setText("Clected");

        }
    }
}