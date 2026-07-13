package com.ediiskandar.alqurantajwid.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ediiskandar.alqurantajwid.R;
import com.ediiskandar.alqurantajwid.adapter.AyahAdapter;
import com.ediiskandar.alqurantajwid.data.AppDatabase;
import com.ediiskandar.alqurantajwid.data.BookmarkDao;
import com.ediiskandar.alqurantajwid.model.Ayah;
import com.ediiskandar.alqurantajwid.model.Surah;
import com.ediiskandar.alqurantajwid.service.AudioService;
import com.ediiskandar.alqurantajwid.util.QuranDataManager;

public class SurahDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AyahAdapter adapter;
    private Surah currentSurah;
    private BookmarkDao bookmarkDao;
    private AudioService audioService;
    private boolean audioServiceBound = false;
    private ImageButton btnPlayAudio;

    private final ServiceConnection audioConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioService.LocalBinder binder = (AudioService.LocalBinder) service;
            audioService = binder.getService();
            audioServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            audioServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_detail);

        initializeDatabase();
        initializeViews();
        loadSurahData();
        setupAudioButton();
    }

    private void initializeDatabase() {
        AppDatabase db = AppDatabase.getDatabase(this);
        bookmarkDao = db.bookmarkDao();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerview_ayahs);
        btnPlayAudio = findViewById(R.id.btn_play_audio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadSurahData() {
        int surahNumber = getIntent().getIntExtra("SURAH_NUMBER", 1);
        currentSurah = QuranDataManager.getSurah(this, surahNumber);

        if (currentSurah != null) {
            setTitle(currentSurah.number + ". " + currentSurah.name);
            
            adapter = new AyahAdapter(currentSurah.ayahs, ayah -> {
                showAyahOptions(ayah);
            });
            
            recyclerView.setAdapter(adapter);
        }
    }

    private void setupAudioButton() {
        btnPlayAudio.setOnClickListener(v -> {
            if (audioService != null) {
                if (audioService.isPlaying()) {
                    audioService.pauseAudio();
                    btnPlayAudio.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    // Implement audio URL loading from Quran API
                    Toast.makeText(this, "Audio feature coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showAyahOptions(Ayah ayah) {
        // Show bookmark dialog or menu
        Toast.makeText(this, "Ayah: " + ayah.number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, AudioService.class);
        bindService(intent, audioConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (audioServiceBound) {
            unbindService(audioConnection);
            audioServiceBound = false;
        }
    }
}
