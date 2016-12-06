package com.example.bridgeit.ipl_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bridgeit.ipl_app.R;
import com.example.bridgeit.ipl_app.adapter.PlayerAdapter;
import com.example.bridgeit.ipl_app.controller.PlayerController;
import com.example.bridgeit.ipl_app.model.PlayerModel;
import com.example.bridgeit.ipl_app.myinetrface.ArrayListPlayer;
import com.example.bridgeit.ipl_app.myinetrface.ClickListener;
import com.example.bridgeit.ipl_app.utility.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by bridgeit on 24/11/16.
 */

public class PlayerFragment extends Fragment {
//String mUrl;
ArrayList<PlayerModel> playerModels;
    public static Fragment newInstance(String url) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("URL", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View view = inflater.inflate(R.layout.player_fragment, container, false);
        String url = getArguments().getString("URL");

        Log.i("PlayerFragment",url);
        final  RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        PlayerController playerController = new PlayerController(url);

        playerController.getmDatabaseReference(new ArrayListPlayer() {
            @Override
            public void fireBaseData(ArrayList<PlayerModel> model) {
                playerModels  = model;
                if (playerModels != null && playerModels.size()>0) {
                    PlayerAdapter adapter = new PlayerAdapter(playerModels, getContext());
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.i("exception","data not available");
                }

            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void OnClick(View view, int position) {
               PlayerModel model = playerModels.get(position);
                Log.i("postion player", ""+position);
                Bundle bundle = new Bundle();
                bundle.putString("name", model.getPlayer_name());
                bundle.putString("dob", model.getPlayer_dob());
                bundle.putString("role", model.getPlayer_role());
                bundle.putString("batting", model.getPlayer_batting_style());
                bundle.putString("bowling", model.getPlayer_bowling_style());
                bundle.putString("notionality", model.getPlayer_nationality());
                bundle.putString("image",model.getPlayer_img_url());

                Intent intent = new Intent(getActivity(), Player.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        }));
        return view;
    }

}
