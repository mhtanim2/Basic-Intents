package com.example.intentpractice;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.intentpractice.databinding.ActivitySecodnBinding;
public class SecodnActivity extends AppCompatActivity {
    ActivitySecodnBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivitySecodnBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        String getIndex=getIntent().getStringExtra("value");
        String getMod=getIntent().getStringExtra("MOD");
        binding.tvResult.setText(getIndex);
        binding.tvMod.setText(getMod);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),getIndex,Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                int res=Integer.parseInt(getIndex);
                int newMod=res%10;
                intent.putExtra("NewMod",Integer.toString(newMod));
                setResult(RESULT_OK,intent);
                SecodnActivity.this.finish();
            }
        });
    }
}