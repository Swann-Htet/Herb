package com.zeroone.herb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.ads.OnUserEarnedRewardListener;
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

import pl.droidsonroids.gif.GifImageView;

public class MangaDetailActivity extends AppCompatActivity {

    String COVER_URL;
    String DES_URL;
    String THUMB_URL;
    String MANGA_TITLE;
    String MANGA_NAME;
    String MANGA_AUTHOR;
    ImageView mangaThumb, mangaDes;
    GifImageView mangaCover;
    TextView mangaTitle;
    private AdView mAdView;
    private LottieAnimationView animationViewPoll;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_detail);

        COVER_URL = getIntent().getStringExtra("coverURL");
        DES_URL = getIntent().getStringExtra("desURL");
        THUMB_URL = getIntent().getStringExtra("thumbURL");
        MANGA_TITLE = getIntent().getStringExtra("title");
        MANGA_NAME = getIntent().getStringExtra("mName");
        MANGA_AUTHOR = getIntent().getStringExtra("mAuthor");


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mAdView = findViewById(R.id.adViewManga);
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
                Toast.makeText(MangaDetailActivity.this, adError.getMessage(), Toast.LENGTH_SHORT).show();
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

        InterstitialAd.load(this,"ca-app-pub-4743806105497141/3972451948", adRequestBanner,
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
                        Toast.makeText(MangaDetailActivity.this, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MangaDetailActivity.this);
                }
            }
        },2000);


        mangaCover = findViewById(R.id.mangaCover);
        FirebaseDatabase databaseG = FirebaseDatabase.getInstance("https://herb-2023-default-rtdb.firebaseio.com");
        DatabaseReference referenceG = databaseG.getReference("Background_Gif");

        referenceG.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String gifUrl = snapshot.child("url").getValue(String.class);

                    Glide.with(MangaDetailActivity.this)
                            .asGif()
                            .load(gifUrl)
                            .into(mangaCover);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        mangaThumb = findViewById(R.id.mmangaThumb);
        mangaDes = findViewById(R.id.mangaDes);
        mangaTitle = findViewById(R.id.mangaTitle);

        mangaTitle.setText(MANGA_TITLE);
        Glide.with(this).load(THUMB_URL).into(mangaThumb);
        Glide.with(this).load(DES_URL).into(mangaDes);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadRewardedAd();
            }
        });

        Button buttonDownload =findViewById(R.id.downManga);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedAd != null) {
                    mRewardedAd.show(MangaDetailActivity.this, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            loadRewardedAd();

                            AlertDialog.Builder builder = new AlertDialog.Builder(MangaDetailActivity.this);
                            View customView = getLayoutInflater().inflate(R.layout.download_alert, null);
                            builder.setView(customView);

                            TextView movieName = customView.findViewById(R.id.movieName);
                            ImageView movieThumb = customView.findViewById(R.id.movieThumb);
                            TextView movieActress = customView.findViewById(R.id.movieActress);

                            movieName.setText(MANGA_TITLE);
                            movieActress.setText(MANGA_AUTHOR);
                            Glide.with(MangaDetailActivity.this).load(THUMB_URL).into(movieThumb);

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
                                    DatabaseReference databaseRef = database.getReference("Manga");

                                    databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                                                Manga featuredMovie = movieSnapshot.getValue(Manga.class);

                                                String cName = featuredMovie.getmCoverName();

                                                if (cName != null && cName.equals(MANGA_NAME)) {
                                                    int currentCount = featuredMovie.getmDownloadCount();
                                                    currentCount++;
                                                    movieSnapshot.getRef().child("mDownloadCount").setValue(currentCount);

                                                    String downloadLink = featuredMovie.getMlink();
                                                    if (downloadLink != null) {
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadLink));
                                                        startActivity(browserIntent);
                                                    }
                                                }
                                            }
                                            Toast.makeText(MangaDetailActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            // Handle errors
                                            Toast.makeText(MangaDetailActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-4743806105497141/9272594677",
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
}