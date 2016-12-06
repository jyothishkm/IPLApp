package com.example.bridgeit.ipl_app.controller;

import android.util.Log;

import com.example.bridgeit.ipl_app.model.PlayerModel;
import com.example.bridgeit.ipl_app.myinetrface.ArrayListPlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by bridgeit on 23/11/16.
 */

public class PlayerController {
    public static final String TAG = "PlayerController";
    String mUrl;
    DatabaseReference mDatabaseReference;
    public PlayerController(String mUrl) {
        this.mUrl = mUrl;
        refIns();
    }
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    public PlayerController() {
    }

    private void refIns() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        Log.i("jyothish", mUrl);
        mDatabaseReference = mDatabase.getReference(mUrl);
    }




    public DatabaseReference getmDatabaseReference(final ArrayListPlayer listPlayer) {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<PlayerModel>> typeIndicator = new GenericTypeIndicator<ArrayList<PlayerModel>>() {
                };
                ArrayList<PlayerModel> playerModels = new ArrayList<PlayerModel>();
                playerModels.addAll(dataSnapshot.getValue(typeIndicator));
                listPlayer.fireBaseData(playerModels);

                /*for (int i = 0; i <playerModels.size() ; i++) {
                    Log.i("PlayerController", playerModels.get(i).getPlayer_name());

                }*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mDatabaseReference;
    }
}
