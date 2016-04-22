package ru.mail.belyakovim.yandex_mobilization_2016;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.mail.belyakovim.yandex_mobilization_2016.databinding.ActivityMusicianProfileBinding;

public class MusicianProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMusicianProfileBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_musician_profile);

        int id = getIntent().getIntExtra(MobilizationApplication.MUSICIAN_ID, 0);
        Musician musician = ((MobilizationApplication) getApplication()).getMusiciansList().find(id);

        binding.setMusician(musician);

    }
}
