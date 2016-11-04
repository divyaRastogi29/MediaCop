package com.follow.me.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by divya on 31/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class GetTopHashTagRequest {

    private String country ;

    public GetTopHashTagRequest(){

    }

    public GetTopHashTagRequest(String country){
         this.country = country ;
     }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
