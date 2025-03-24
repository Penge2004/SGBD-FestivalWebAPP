package com.project.sgbd_project.Domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * This class is used in the PerformanceController as an object that has only the id and date necessary
 * because the Performance is only created in the service
 */
public class PerformanceRequest {
    @JsonProperty("artist_id")
    private String artist_id;

    @JsonProperty("stage_id")
    private String stage_id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("start_time")
    private String start_time;

    public String getArtistId() {
        return artist_id;
    }

    public void setArtistId(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getStageId() {
        return stage_id;
    }

    public void setStageId(String stageId) {
        this.stage_id = stageId;
    }

    public String getPerformanceDate() {
        return start_time;
    }

    public void setPerformanceDate(String start_time) {
        this.start_time = start_time;
    }

    @Override
    public String toString() {
        return "PerformanceRequest{" +
                "artistId='" + artist_id + '\'' +
                ", stageId='" + stage_id + '\'' +
                ", performanceDate='" + start_time + '\'' +
                '}';
    }
}
