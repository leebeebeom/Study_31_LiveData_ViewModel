package com.example.a31_livedata_viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.a31_livedata_viewmodel.databinding.ActivityUpdateActivityBinding;

public class UpdateActivity extends AppCompatActivity {

    public static final String UPDATE_PHONE = "updatePhone";
    public static final String UPDATE_ADDRESS = "updateAddress";
    private ActivityUpdateActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.editPhone.setText(intent.getStringExtra(MainActivity.PHONE));
        binding.editAddress.setText(intent.getStringExtra(MainActivity.ADDRESS));

        binding.btnSave.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            intent1.putExtra(UPDATE_PHONE, binding.editPhone.getText().toString());
            intent1.putExtra(UPDATE_ADDRESS, binding.editAddress.getText().toString());
            setResult(RESULT_OK, intent1);
            finish();
        });
        binding.btnCancel.setOnClickListener(v -> finish());
    }
}