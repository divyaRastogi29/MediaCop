package com.follow.me.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by divya on 1/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class AddCountryHashRequest {

    public AddCountryHashRequest(){

    }

    private String country ;

    private List<String> hashtags ;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
