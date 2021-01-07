package com.buslayout.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

public class HomeModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("seat_map")
    @Expose
    private LinkedList<SeatMap> seatMap = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LinkedList<SeatMap> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(LinkedList<SeatMap> seatMap) {
        this.seatMap = seatMap;
    }

}
