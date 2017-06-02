package com.ydkmm.sqlitetestforalarm;

/**
 * Created by asrafkaizen on 6/1/2017.
 */

class AlarmPOJO {
    private int id;
    private long timeLong;
    private String details;

    public AlarmPOJO(){
        id = 0;
        timeLong = 0;
        details = "";
    }

    public AlarmPOJO(int i, long l, String d){
        id = i;
        timeLong = l;
        details = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(long timeLong) {
        this.timeLong = timeLong;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
