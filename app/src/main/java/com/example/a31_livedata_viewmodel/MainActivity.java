package com.example.a31_livedata_viewmodel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.a31_livedata_viewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final int REQUEST_CODE = 1000;
    private ActivityMainBinding binding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(MainViewModel.class);

        UserProfile userProfile = new UserProfile("홍길동", "010-0000-0000", "서울 양천구 신월동", "남성");
        if (model.getLiveData().getValue() == null){
            model.getLiveData().setValue(userProfile);
        }
        model.getLiveData().observe(this, userProfile1 -> binding.setUserProfile(userProfile1));

        binding.btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra(PHONE, binding.phone.getText().toString());
            intent.putExtra(ADDRESS, binding.address.getText().toString());
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE && resultCode == RESULT_OK && data != null){
            UserProfile userProfile = model.getLiveData().getValue();
            if (userProfile != null) {
                userProfile.setPhone(data.getStringExtra(UpdateActivity.UPDATE_PHONE));
                userProfile.setAddress(data.getStringExtra(UpdateActivity.UPDATE_ADDRESS));
                model.getLiveData().setValue(userProfile);
            }
        }
    }
}