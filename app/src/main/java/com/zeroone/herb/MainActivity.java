package com.zeroone.herb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String AD_UNIT_ID = "ca-app-pub-4743806105497141/6645882595";
    private AppOpenAd appOpenAd = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private boolean isShowingAd = false;
    private List<FeaturedMovie> dataModels;
    private SliderAdapter sliderAdapter;
    private List<FeaturedMovie> featuredMovies;
    private FeaturedAdapter featuredAdapter;
    private RecyclerView recyclerView;
    private RecyclerView mRecyclerView;
    private List<Manga> mangas;
    private MangaAdapter mangaAdapter;
    LottieAnimationView animationMovie, animationManga;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView01, mAdView02, mAdView03, mAdView00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mAdView01 = findViewById(R.id.adView01);
        AdRequest adRequest01 = new AdRequest.Builder().build();
        mAdView01.loadAd(adRequest01);

        checkAppVersion();

        mAdView01.setAdListener(new AdListener() {
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
                Toast.makeText(MainActivity.this, adError.getMessage(), Toast.LENGTH_SHORT).show();
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

        AdView adView02 = new AdView(this);
        adView02.setAdSize(AdSize.FULL_BANNER);

        mAdView02 = findViewById(R.id.adView02);
        AdRequest adRequest02 = new AdRequest.Builder().build();
        mAdView02.loadAd(adRequest02);

        mAdView02.setAdListener(new AdListener() {
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
                Toast.makeText(MainActivity.this, adError.getMessage(), Toast.LENGTH_SHORT).show();
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

        AdView adView03 = new AdView(this);
        adView03.setAdSize(AdSize.FULL_BANNER);

        mAdView03 = findViewById(R.id.adView03);
        AdRequest adRequest03 = new AdRequest.Builder().build();
        mAdView03.loadAd(adRequest03);

        mAdView03.setAdListener(new AdListener() {
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
                Toast.makeText(MainActivity.this, adError.getMessage(), Toast.LENGTH_SHORT).show();
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

        AdView adView00 = new AdView(this);
        adView00.setAdSize(AdSize.FULL_BANNER);

        mAdView00 = findViewById(R.id.adView00);
        AdRequest adRequest00 = new AdRequest.Builder().build();
        mAdView00.loadAd(adRequest03);

        mAdView00.setAdListener(new AdListener() {
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
                Toast.makeText(MainActivity.this, adError.getMessage(), Toast.LENGTH_SHORT).show();
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

        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                appOpenAd = ad;
                showAdIfAvailable();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                loadAppOpenAd(); // Retry loading the ad
            }
        };

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadAppOpenAd();
            }
        });



        ImageView infoApp = findViewById(R.id.infoApp);
        infoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View customView = getLayoutInflater().inflate(R.layout.about_dialog, null);
                builder.setView(customView);
                AlertDialog alertDialog = builder.create();

                Button okayBtn = customView.findViewById(R.id.okayBtn);
                okayBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        EditText searchText = findViewById(R.id.searchText);
        animationMovie = findViewById(R.id.animationMovie);
        animationManga = findViewById(R.id.animationMovieManga);
        TextView movienotFoundText = findViewById(R.id.movienotFoundText);
        TextView manganotFoundText = findViewById(R.id.movienotFoundTextManga);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();
                List<FeaturedMovie> filteredMovies = new ArrayList<>();

                for (FeaturedMovie movie : featuredMovies) {
                    String title = movie.getTitle().toLowerCase();
                    String releaseDate = movie.getReleaseDate().toLowerCase();

                    if (title.contains(query) || releaseDate.contains(query)) {
                        filteredMovies.add(movie);
                    }
                }

                featuredAdapter = new FeaturedAdapter(filteredMovies, MainActivity.this);
                recyclerView.setAdapter(featuredAdapter);

                if (filteredMovies.isEmpty()) {
                    animationMovie.setVisibility(View.VISIBLE);
                    movienotFoundText.setVisibility(View.VISIBLE);
                    animationMovie.playAnimation();
                } else {
                    animationMovie.setVisibility(View.GONE);
                    movienotFoundText.setVisibility(View.GONE);
                    animationMovie.cancelAnimation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        EditText searchManga = findViewById(R.id.searchManga);
        searchManga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();
                List<Manga> mangas1 = new ArrayList<>();

                for (Manga manga : mangas) {
                    String title = manga.getMtitle().toLowerCase();
                    String releaseDate = manga.getMeleaseDate().toLowerCase();

                    if (title.contains(query) || releaseDate.contains(query)) {
                        mangas1.add(manga);
                    }
                }

                mangaAdapter = new MangaAdapter(mangas1, MainActivity.this);
                mRecyclerView.setAdapter(mangaAdapter);

                if (mangas1.isEmpty()) {
                    animationManga.setVisibility(View.VISIBLE);
                    manganotFoundText.setVisibility(View.VISIBLE);
                    animationManga.playAnimation();
                } else {
                    animationManga.setVisibility(View.GONE);
                    manganotFoundText.setVisibility(View.GONE);
                    animationManga.cancelAnimation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        SliderView sliderView = findViewById(R.id.sliderView);

        sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(6);

        renewItems(sliderView);

        loadFirebaseForSlider();

        recyclerView = findViewById(R.id.recyclerView);
        featuredMovies = new ArrayList<>();
        featuredAdapter = new FeaturedAdapter(featuredMovies, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(featuredAdapter);

        loadFeaturedData();

        //Manga
        mRecyclerView = findViewById(R.id.recyclerView2);
        mangas = new ArrayList<>();
        mangaAdapter = new MangaAdapter(mangas, this);

        LinearLayoutManager layoutManagerM = new LinearLayoutManager(this);
        layoutManagerM.setOrientation(RecyclerView.HORIZONTAL);
        layoutManagerM.setReverseLayout(true);
        layoutManagerM.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(layoutManagerM);
        mRecyclerView.setAdapter(mangaAdapter);

        loadMangaData();
    }

    private void loadMangaData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://herb-2023-default-rtdb.firebaseio.com");
        DatabaseReference databaseReference = database.getReference("Manga");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    Manga manga = contentSnapShot.getValue(Manga.class);
                    mangas.add(manga);
                }
                mangaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });
    }

    private void loadFeaturedData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://herb-2023-default-rtdb.firebaseio.com");
        DatabaseReference databaseReference = database.getReference("Featured_movies");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    FeaturedMovie featuredMovie = contentSnapShot.getValue(FeaturedMovie.class);
                    featuredMovies.add(featuredMovie);
                }
                featuredAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });
    }

    private void loadFirebaseForSlider() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://herb-2023-default-rtdb.firebaseio.com");
        DatabaseReference reference = database.getReference("Featured_movies");

        Query query = reference.orderByChild("fDownloadCount").limitToLast(3); // Limit to 3 items

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<FeaturedMovie> sortedList = new ArrayList<>();
                for (DataSnapshot contentSlider : snapshot.getChildren()) {
                    FeaturedMovie sliderItem = contentSlider.getValue(FeaturedMovie.class);
                    sortedList.add(sliderItem);
                }

                Collections.sort(sortedList, new Comparator<FeaturedMovie>() {
                    @Override
                    public int compare(FeaturedMovie movie1, FeaturedMovie movie2) {
                        return movie2.getfDownloadCount() - movie1.getfDownloadCount();
                    }
                });

                int itemsToAdd = Math.min(sortedList.size(), 3);
                dataModels.addAll(sortedList.subList(0, itemsToAdd));

                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }

    private void renewItems(SliderView sliderView) {
        dataModels = new ArrayList<>();
        FeaturedMovie dataItems = new FeaturedMovie();
        dataModels.add(dataItems);

        sliderAdapter.renewItems(dataModels);
        sliderAdapter.deleteItems(0);
    }



    public void loadAppOpenAd() {
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(
                this,
                AD_UNIT_ID,
                request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                loadCallback
        );
    }

    public void showAdIfAvailable() {
        if (!isShowingAd && appOpenAd != null) {
            isShowingAd = true;
            appOpenAd.show(this);
        } else {
            loadAppOpenAd();
        }
    }

    private void showUpdateDialog(String marketUri) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setIcon(R.drawable.logo);
        builder.setTitle("Update Available");
        builder.setMessage("A new version of the app is available. Please update for the best experience.");
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Redirect to Play Store for the update
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketUri));
                startActivity(intent);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void checkAppVersion() {
        DatabaseReference versionRef = FirebaseDatabase.getInstance("https://herb-2023-default-rtdb.firebaseio.com/")
                .getReference("AppVersion");

        versionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String version = dataSnapshot.child("version").getValue(String.class);
                    String marketUri = dataSnapshot.child("marketUri").getValue(String.class);

                    if (version != null && !version.equals(getAppVersion())) {
                        showUpdateDialog(marketUri);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Log.e("DatabaseError", "Database error: " + databaseError.getMessage());
            }
        });
    }

    private String getAppVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}