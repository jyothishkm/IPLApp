package com.example.bridgeit.ipl_app.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bridgeit.ipl_app.R;
import com.example.bridgeit.ipl_app.adapter.TeamAdapter;
import com.example.bridgeit.ipl_app.controller.TeamController;
import com.example.bridgeit.ipl_app.model.TeamModel;
import com.example.bridgeit.ipl_app.myinetrface.ArrayListTeam;
import com.example.bridgeit.ipl_app.myinetrface.ClickListener;
import com.example.bridgeit.ipl_app.utility.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by bridgeit on 21/11/16.
 */

public class TeamFragment extends Fragment  {
    ArrayList<TeamModel> mTeamModels;
    ProgressDialog mDialog;
    public TeamFragment() {
    }
    public TeamFragment(ProgressDialog mDialog) {
        this.mDialog = mDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.team_fragment, container, false);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());


        // get the data from controller and pass to adapter
        TeamController teamController = new TeamController();

        teamController.getmDatabaseReference(new ArrayListTeam() {
            @Override
            public void fireBaseData(ArrayList<TeamModel> model) {
                mTeamModels  = model;
                mDialog.dismiss();

                if (mTeamModels != null && mTeamModels.size()>0) {
                    TeamAdapter adapter = new TeamAdapter(mTeamModels, getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }


            }
        });




        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void OnClick(View view, int position) {


                TeamModel model = mTeamModels.get(position);

                String name = model.getTeamname();

                Fragment player = PlayerFragment.newInstance(name);
                //  Log.i("Check",name);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_player, player).addToBackStack(String.valueOf(new TeamFragment())).commit();
            }
        }));
        return view;
    }
}
