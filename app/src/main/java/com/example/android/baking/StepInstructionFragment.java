package com.example.android.baking;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.android.baking.model.Recipe;
import com.example.android.baking.model.Steps;
import com.google.android.exoplayer2.C;
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
    long position;
    TextView text;
    int videoStep;
    Recipe currentRecipe;
    ArrayList<Steps> stList;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        position = C.TIME_UNSET;
        if(savedInstanceState!=null){
            position = savedInstanceState.getLong("position", C.TIME_UNSET);
            bundle = savedInstanceState.getBundle("bundle");
        }

        view = inflater.inflate(R.layout.fragment_step_instruction, container, false);

        playerView = (PlayerView) view.findViewById(R.id.playerView);

        bundle = getArguments();
        if (bundle != null){
            currentRecipe = bundle.getParcelable("Package");
            videoStep = bundle.getInt("Position");
            stList = currentRecipe.getSteps();
            current = stList.get(videoStep);
        }

        Button prevStep = view.findViewById(R.id.prev_step);
        Button nextStep = view.findViewById(R.id.next_step);

        prevStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoStep>0){
                    if(mExoPlayer!=null){
                        mExoPlayer.stop();
                        mExoPlayer.release();
                        mExoPlayer=null;
                    }
                    videoStep = videoStep - 1;
                    current = stList.get(videoStep);
                    initializePlayer();
                }

            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(videoStep<stList.size()-1){
                    if(mExoPlayer!=null){
                        mExoPlayer.stop();
                        mExoPlayer.release();
                        mExoPlayer=null;
                    }
                    videoStep = videoStep + 1;
                    current = stList.get(videoStep);
                    initializePlayer();
                }

            }
        });


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("position", position);
        savedInstanceState.putBundle("bundle", bundle);

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
        if (Util.SDK_INT <=23) {
            if (mExoPlayer != null) {
                mExoPlayer.stop();
                mExoPlayer.release();
                mExoPlayer = null;
            }
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        if(Util.SDK_INT >23){
            if(mExoPlayer!=null){
                mExoPlayer.stop();
                mExoPlayer.release();
                mExoPlayer = null;
            }
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

            Uri uri = getVideo();

            if (current != null) {

                // Prepare the MediaSource.
                TextView instructions = view.findViewById(R.id.instructions);
                TextView titleInstructions = view.findViewById(R.id.instructionsTitle);
                instructions.setText(current.getDescription());
                titleInstructions.setText(current.getShortDescription());

                if(uri!=null) {
                    mExoPlayer.setPlayWhenReady(true);
                    MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab")).createMediaSource(uri);
                    if (position != C.TIME_UNSET) {
                        mExoPlayer.seekTo(position);
                    }

                    mExoPlayer.prepare(mediaSource, true, false);
                }
            }
        }
    }

    private Uri getVideo (){

        Uri uri;

        if(current!=null) {
            String videoURL = current.getVideoURL();
            String thumbnailURL = current.getThumbnailURL();


            if (videoURL.toLowerCase().contains(getString(R.string.videoMP4))) {
                uri = Uri.parse(current.getVideoURL());
            } else if (thumbnailURL.toLowerCase().contains((getString(R.string.videoMP4)))) {
                uri = Uri.parse(current.getThumbnailURL());
            } else {
                uri = null;
            }

            return uri;
        }
        return null;
    }

}
