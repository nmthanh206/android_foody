package hcmute.spkt.foody_g7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import hcmute.spkt.foody_g7.databinding.ActivityStartAppBinding;

public class StartApp extends AppCompatActivity {
    ActivityStartAppBinding binding;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new Database(this);
//        setContentView(R.layout.activity_start_app);
//        replaceFragment(new SignInFragment());
        binding= ActivityStartAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new SignInFragment());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
}