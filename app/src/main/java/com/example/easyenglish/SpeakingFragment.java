package com.example.easyenglish;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;


public class SpeakingFragment extends Fragment {

    private TextToSpeech mTTS;
    private TextView mTextView;
    private ImageButton mButtonSpeak;

    ImageButton playButton;
    ImageView recordButton;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    private boolean isRecording = false;

    final int REQUEST_PERMISSION_CODE = 1000;

    public SpeakingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_speaking, container, false);

        recordButton = root.findViewById(R.id.start_recording_btn);
        playButton = root.findViewById(R.id.play_btn);
        playButton.setEnabled(false);
        playButton.setVisibility(View.INVISIBLE);
        //stopPlayingButton = root.findViewById(R.id.stop_btn);

        mButtonSpeak = root.findViewById(R.id.text_to_speech_btn);

        mTTS = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                             || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "language not supported");
                    }
                    else {
                        mButtonSpeak.setEnabled(true);
                    }
                }
                else {
                    Log.e("TTS", "init failed");
                }
            }
        });

        mTextView = root.findViewById(R.id.english_text_1);

        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        if (checkPermissionFromDevice()) {
            recordButton.setOnClickListener(v -> {

                if (isRecording) {
                    mediaRecorder.stop();
                    playButton.setEnabled(true);
                    playButton.setVisibility(View.VISIBLE);
                    recordButton.setEnabled(true);
                    //stopPlayingButton.setEnabled(false);
                    isRecording = false;
                }

                else {
                    pathSave = Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/"
                            + UUID.randomUUID().toString() + "_audio_record.3gp";
                    setupMediaRecorder();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    playButton.setEnabled(false);
                    playButton.setVisibility(View.INVISIBLE);
                    //stopPlayingButton.setEnabled(false);

                    Toast.makeText(getContext(), "recording...", Toast.LENGTH_SHORT).show();
                    isRecording = true;
                }
            });

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //stopPlayingButton.setEnabled(true);

                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(getContext(), "playing...", Toast.LENGTH_SHORT).show();
                }
            });

//            stopPlayingButton.setOnClickListener(v -> {
//                recordButton.setEnabled(true);
//                stopPlayingButton.setEnabled(false);
//                playButton.setEnabled(true);
//
//                if (mediaPlayer != null) {
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    setupMediaRecorder();
//                }
//            });
        }
        else {
            requestPermission();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
    }

    private void speak() {
        String text = mTextView.getText().toString();
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(getContext(), "permission granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_SHORT).show();

            }
            break;
        }
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission
                .WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
    }

}