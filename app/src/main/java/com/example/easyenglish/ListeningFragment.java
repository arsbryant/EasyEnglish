package com.example.easyenglish;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


public class ListeningFragment extends Fragment implements View.OnClickListener {

    Button button1, button2, button3, button4;

    public ListeningFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_listening, container, false);

        VideoView videoView = root.findViewById(R.id.video_view);
        button1 = root.findViewById(R.id.button);
        button1.setOnClickListener(this);
        button2 = root.findViewById(R.id.button2);
        button3 = root.findViewById(R.id.button3);
        button4 = root.findViewById(R.id.button4);

        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.frozen;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
//                int resId = R.color.button_background;
//                button1.setBackgroundResource(resId);
               NavController navController = Navigation.findNavController(v);

                try {
                    Thread.sleep(1000);
                    navController.navigate(R.id.action_listeningFragment_to_listeningTwo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}