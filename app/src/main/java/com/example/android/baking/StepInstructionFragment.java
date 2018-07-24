package com.example.android.baking;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.baking.model.Ingredients;
import com.example.android.baking.model.Recipe;
import com.example.android.baking.model.Steps;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class StepInstructionFragment extends Fragment{

    private SimpleExoPlayer mExoPlayer;
    private PlayerView playerView;
    Steps current;
    View view;
    long position;
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        position = C.TIME_UNSET;
        if(savedInstanceState!=null){
            position = savedInstanceState.getLong("position", C.TIME_UNSET);
        }

        view = inflater.inflate(R.layout.fragment_step_instruction, container, false);
        text = (TextView) view.findViewById(R.id.instructions);
        playerView = (PlayerView) view.findViewById(R.id.playerView);

        Bundle bundle = getArguments();

        if (bundle != null){
            Log.v("StepInstruct Fragment", bundle.toString());
            current = bundle.getParcelable("Package");
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putLong("position", position);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mExoPlayer!=null){
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer=null;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(mExoPlayer!=null){
            position = mExoPlayer.getCurrentPosition();
            Log.v("Position", Long.toString(position));
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        initializePlayer();
    }

    private void initializePlayer() {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()), new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(mExoPlayer);

            // Prepare the MediaSource.
            Uri uri=null;
            if(current!=null){
                uri = Uri.parse(current.getVideoURL());
                TextView instructions = view.findViewById(R.id.instructions);
                instructions.setText(current.getDescription());}

            mExoPlayer.setPlayWhenReady(true);
            MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab")).createMediaSource(uri);
            if(position!=C.TIME_UNSET){
                mExoPlayer.seekTo(position);
            }

            mExoPlayer.prepare(mediaSource, true, false);
        }
    }

}
