package com.example.bridgeit.ipl_app.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bridgeit.ipl_app.R;
import com.example.bridgeit.ipl_app.controller.ImageDownloader;
import com.example.bridgeit.ipl_app.myinetrface.ImageDownloaderInterface;

/**
 * Created by bridgeit on 3/12/16.
 */

public class Player extends Activity {
    Animation mAnimation;
    RelativeLayout layout;
    ImageView imageView;
    TextView each_player_name,each_player_dob,each_player_role,each_player_batting_style,
            each_player_bowling_style,each_player_nationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_each_player);
        mAnimation = AnimationUtils.loadAnimation(Player.this, R.anim.zoom_in);
        layout = (RelativeLayout)findViewById(R.id.relative_player);
        layout.setAnimation(mAnimation);
        layout.startAnimation(mAnimation);
        each_player_name = (TextView)findViewById(R.id.each_player_name);
        each_player_dob = (TextView)findViewById(R.id.each_player_dob);
        each_player_role = (TextView)findViewById(R.id.each_player_role);
        each_player_batting_style = (TextView)findViewById(R.id.each_player_batting_style);
        each_player_bowling_style = (TextView)findViewById(R.id.each_player_bowling_style);
        each_player_nationality = (TextView)findViewById(R.id.each_player_nationality);
        imageView = (ImageView)findViewById(R.id.each_mImage_player) ;


        Bundle extras = getIntent().getExtras().getBundle("data");

        if (extras != null) {
            each_player_name.setText("Name:"+extras.getString("name"));
            each_player_dob.setText("DOB:"+extras.getString("dob"));
            each_player_role.setText("Role:"+extras.getString("role"));
            each_player_batting_style.setText("Batting Style:"+extras.getString("batting"));
            each_player_bowling_style.setText("Bowling Style:"+extras.getString("bowling"));
            each_player_nationality.setText("Nationality:"+extras.getString("notionality"));
            String url = extras.getString("image");
            ImageDownloader.downloadImage(url, new ImageDownloaderInterface() {
                @Override
                public void getImage(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            });
        }

    }
}
