package info.androidhive.viewpager2.fragments;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import info.androidhive.viewpager2.R;
public class MoviesFragment extends Fragment {
    private VideoView myvideo;
    private ImageView myimage;
    private String URLvideos;
    private String URLimages;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide action bar in this fragment
        // ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        // comprobar si hay el bundle
        if (getArguments() != null) {
            URLvideos = getArguments().getString(FragmentViewPagerActivity.URLVIDEOS_KEY,"No Url");
            //URLimages = getArguments().getString(FragmentViewPagerActivity.URLIMAGES_KEY,"No Url");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // CREANDO LAS IMAGENES
        /*
        myimage = (ImageView)view.findViewById(R.id.myimg);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(URLimages).getContent());
            myimage.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // CREANDO LOS VIDEOS
        Uri uri=Uri.parse(URLvideos);
        myvideo = (VideoView)view.findViewById(R.id.videoView2);
        myvideo.setVideoURI(uri);
       // myimage.setVisibility(View.VISIBLE);
        myvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
               // myimage.setVisibility(View.INVISIBLE);
                if(FragmentViewPagerActivity.MY_TEXTO == "pause"){
                   // myimage.setVisibility(View.VISIBLE);
                    mp.pause();
                }
            }
        });


    }
}
