package com.example.bridgeit.ipl_app.model;

/**
 * Created by bridgeit on 23/11/16.
 */

public class TeamModel {
private String owner;
    private String teamname;
    private String url;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TeamModel(String owner, String teamname, String url) {
        this.owner = owner;

        this.teamname = teamname;
        this.url = url;
    }

    public TeamModel() {
    }
}
