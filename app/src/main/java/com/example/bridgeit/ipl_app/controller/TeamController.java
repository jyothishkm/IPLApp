package com.example.bridgeit.ipl_app.controller;

import com.example.bridgeit.ipl_app.myinetrface.ArrayListTeam;
import com.example.bridgeit.ipl_app.model.TeamModel;
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

public class TeamController {
    public static final String TAG = "TeamController";
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    DatabaseReference mDatabaseReference = mDatabase.getReference("ipl");

    public DatabaseReference getmDatabaseReference(final ArrayListTeam arrayListdata) {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<TeamModel>> t = new GenericTypeIndicator<ArrayList<TeamModel>>() {
                };

                ArrayList<TeamModel> modelArrayList = new ArrayList<TeamModel>();
                modelArrayList.addAll(dataSnapshot.getValue(t));
                arrayListdata.fireBaseData(modelArrayList);



                for (int i = 0; i <modelArrayList.size() ; i++)
                {

                   // Log.i(TAG, "onDataChange: "+modelArrayList.get(i).getOwner()+"  ...");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;
    }


}
