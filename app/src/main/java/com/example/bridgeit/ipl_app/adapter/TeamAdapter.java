package com.example.bridgeit.ipl_app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bridgeit.ipl_app.R;
import com.example.bridgeit.ipl_app.controller.ImageDownloader;
import com.example.bridgeit.ipl_app.model.TeamModel;
import com.example.bridgeit.ipl_app.myinetrface.ImageDownloaderInterface;

import java.util.ArrayList;

/**
 * Created by bridgeit on 21/11/16.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {
    ArrayList<TeamModel> teamModelArrayList;
    Context mContext;
    Animation mAnimation;
    public TeamAdapter(ArrayList<TeamModel> teamModelArrayList, Context context) {
        this.teamModelArrayList = teamModelArrayList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_team_display, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {




        TeamModel teamModel = teamModelArrayList.get(position);
        //animation

        holder.team_name.setText(teamModel.getTeamname());
        holder.team_name.setTextColor(Color.BLACK);
        holder.team_owner.setText(teamModel.getOwner());
        holder.team_owner.setTextColor(Color.BLACK);
        mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
        holder.cardView.setAnimation(mAnimation);
        holder.cardView.startAnimation(mAnimation);

        ImageDownloader.downloadImage(teamModel.getUrl(), new ImageDownloaderInterface() {
            @Override
            public void getImage(Bitmap bitmap) {
                holder.logo.setImageBitmap(bitmap);
            }
        });


    }

    @Override
    public int getItemCount() {
        return teamModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView team_name;
        TextView team_owner;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_team);
            logo = (ImageView) itemView.findViewById(R.id.mImage_team_logo);
            team_name = (TextView)itemView.findViewById(R.id.mText_team_name);
            team_owner = (TextView) itemView.findViewById(R.id.mText_team_owner);
        }
    }
}
