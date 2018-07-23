package com.example.android.baking;

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
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
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
    RelativeLayout mView;
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_step_instruction, container, false);
        text = (TextView) view.findViewById(R.id.instructions);
        playerView = (PlayerView) view.findViewById(R.id.playerView);

        Log.e("Master", "StepInstructionFrag: I am born");

        Bundle bundle = getArguments();

        if (bundle != null){
            Log.v("StepInstruct Fragment", bundle.toString());
            current = bundle.getParcelable("Package");
        }

        return view;
    }

    public void changeData(Steps current){

        Uri uri=null;
        if(current!=null){
            uri = Uri.parse(current.getVideoURL());
            text.setText(current.getDescription());}

        mExoPlayer.setPlayWhenReady(true);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab")).createMediaSource(uri);
        mExoPlayer.prepare(mediaSource, true, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
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
            mExoPlayer.prepare(mediaSource, true, false);
        }
    }


}
