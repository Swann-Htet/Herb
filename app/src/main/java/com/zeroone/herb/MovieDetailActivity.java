package com.zeroone.herb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    String COVER_URL;
    String DES_URL;
    String THUMB_URL;
    String MOVIE_TITLE;
    String TRAILER_URL;
    String MOVIE_NAME;
    String MOVIE_ACTRESS;

    ImageView movieCover, movieThumb, movieDes;
    TextView movieTitle;
    SeekBar seekBar;
    ImageView playPauseButton;
    boolean isPlaying = false;
    ProgressBar loader;
    VideoView videoView;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        COVER_URL = getIntent().getStringExtra("coverURL");
        DES_URL = getIntent().getStringExtra("desURL");
        THUMB_URL = getIntent().getStringExtra("thumbURL");
        MOVIE_TITLE = getIntent().getStringExtra("title");
        TRAILER_URL = getIntent().getStringExtra("trailerURL");
        MOVIE_NAME = getIntent().getStringExtra("mName");
        MOVIE_ACTRESS = getIntent().getStringExtra("mActress");

        TextView duration = findViewById(R.id.duration);
        duration.setText(MOVIE_ACTRESS);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mAdView = findViewById(R.id.adViewMovieDetail);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Toast.makeText(MovieDetailActivity.this, adError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Ad Error", adError.getMessage());
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                Log.d("AdLoad", "Ad loaded successfully");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

        MobileAds.initialize(this);
        AdRequest adRequestBanner = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-4743806105497141/7030656283", adRequestBanner,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                            }

                            @Override
                            public void onAdImpression() {
                                super.onAdImpression();
                            }

                            @Override
                            public void onAdClicked() {
                                super.onAdClicked();
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Toast.makeText(MovieDetailActivity.this, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MovieDetailActivity.this);
                }
            }
        },2000);


        movieCover = findViewById(R.id.movieCover);
        movieThumb = findViewById(R.id.movieThumb);
        movieDes = findViewById(R.id.movieDes);
        movieTitle = findViewById(R.id.movieTitle);

        movieTitle.setText(MOVIE_TITLE);
        Glide.with(this).load(COVER_URL).into(movieCover);
        Glide.with(this).load(THUMB_URL).into(movieThumb);
        Glide.with(this).load(DES_URL).into(movieDes);

        videoView = findViewById(R.id.videoPlayer);
        seekBar = findViewById(R.id.seekBar);
        playPauseButton = findViewById(R.id.playPauseButton);
        loader = findViewById(R.id.loader);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadRewardedAd();
            }
        });

        Button buttonDownload =findViewById(R.id.downMovie);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRewardedAd != null) {
                    mRewardedAd.show(MovieDetailActivity.this, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // This is where you should give the user their reward
                            loadRewardedAd(); // Load a new ad after rewarding the user

                            AlertDialog.Builder builder = new AlertDialog.Builder(MovieDetailActivity.this);
                            View customView = getLayoutInflater().inflate(R.layout.download_alert, null);
                            builder.setView(customView);

                            TextView movieName = customView.findViewById(R.id.movieName);
                            ImageView movieThumb = customView.findViewById(R.id.movieThumb);
                            TextView movieActress = customView.findViewById(R.id.movieActress);

                            movieName.setText(MOVIE_TITLE);
                            movieActress.setText(MOVIE_ACTRESS);
                            Glide.with(MovieDetailActivity.this).load(THUMB_URL).into(movieThumb);

                            final AlertDialog alertDialog = builder.create();

                            Button buttonCancel = customView.findViewById(R.id.cancelAlert);
                            buttonCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.cancel();
                                }
                            });
                            Button buttonDownload = customView.findViewById(R.id.downloadAlert);
                            buttonDownload.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://herb-2023-default-rtdb.firebaseio.com/");
                                    DatabaseReference databaseRef = database.getReference("Featured_movies");

                                    databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                                                FeaturedMovie featuredMovie = movieSnapshot.getValue(FeaturedMovie.class);

                                                String cName = featuredMovie.getfCoverName();

                                                if (cName != null && cName.equals(MOVIE_NAME)) {
                                                    int currentCount = featuredMovie.getfDownloadCount();
                                                    currentCount++;
                                                    movieSnapshot.getRef().child("fDownloadCount").setValue(currentCount);

                                                    String downloadLink = featuredMovie.getLink();
                                                    if (downloadLink != null) {
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadLink));
                                                        startActivity(browserIntent);
                                                    }
                                                }
                                            }
                                            Toast.makeText(MovieDetailActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            // Handle errors
                                            Toast.makeText(MovieDetailActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                            alertDialog.show();
                        }
                    });
                } else {
                    // No ad available. You may want to show a regular ad or take alternative action.
                }


            }
        });

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    videoView.pause();
                    playPauseButton.setImageResource(R.drawable.ic_play); // Set pause button icon
                } else {
                    videoView.start();
                    playPauseButton.setImageResource(R.drawable.ic_pause); // Set play button icon
                }
                isPlaying = !isPlaying;
            }
        });

        videoView.setVideoPath(TRAILER_URL);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                loader.setVisibility(View.GONE);
                seekBar.setMax(videoView.getDuration());
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playPauseButton.setImageResource(R.drawable.ic_play);
                isPlaying = false;
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                return false;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (videoView.isPlaying()) {
                    int currentPosition = videoView.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                }
                new Handler().postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-4743806105497141/6015875940",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;

                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when ad impression is recorded
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mRewardedAd = null;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}