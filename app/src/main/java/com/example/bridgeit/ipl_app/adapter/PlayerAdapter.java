package com.example.bridgeit.ipl_app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bridgeit.ipl_app.R;
import com.example.bridgeit.ipl_app.controller.ImageDownloader;
import com.example.bridgeit.ipl_app.model.PlayerModel;
import com.example.bridgeit.ipl_app.myinetrface.ImageDownloaderInterface;

import java.util.ArrayList;

/**
 * Created by bridgeit on 24/11/16.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder>  {
    ArrayList<PlayerModel> mPlayerModelArrayList;
    Context mContext;
    Animation mAnimation;
    public PlayerAdapter(ArrayList<PlayerModel> mPlayerModelArrayList, Context context) {
        this.mPlayerModelArrayList = mPlayerModelArrayList;
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_player_display, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        PlayerModel model = mPlayerModelArrayList.get(position);
        holder.player_name.setText(model.getPlayer_name());
       // mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_in);
       // holder.cardView.setAnimation(mAnimation);
        //holder.cardView.startAnimation(mAnimation);
       // holder.cardView.setBackgroundResource(R.drawable.cardback);

       /* holder.player_dob.setText(model.getPlayer_dob());
        holder.player_batting_style.setText(model.getPlayer_batting_style());
        holder.player_bowling_style.setText(model.getPlayer_bowling_style());
        holder.player_nationality.setText(model.getPlayer_nationality());
        holder.player_role.setText(model.getPlayer_role());
*/
        ImageDownloader.downloadImage(model.getPlayer_img_url(), new ImageDownloaderInterface() {
            @Override
            public void getImage(Bitmap bitmap) {
                holder.player_image.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mPlayerModelArrayList.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView player_image;
        CardView cardView;
        TextView player_name, player_dob, player_batting_style, player_bowling_style,
                player_nationality, player_role;
        public MyViewHolder(View view) {
            super(view);
            player_image = (ImageView)view.findViewById(R.id.mImage_player);
            player_name = (TextView) view.findViewById(R.id.player_name);
           /* cardView = (CardView) view.findViewById(R.id.card_player);

            player_dob = (TextView) view.findViewById(R.id.player_dob);
            player_batting_style = (TextView) view.findViewById(R.id.player_batting_style);
            player_bowling_style = (TextView) view.findViewById(R.id.player_bowling_style);
            player_nationality = (TextView) view.findViewById(R.id.player_nationality);
            player_role = (TextView) view.findViewById(R.id.player_role);*/

        }
    }
}
