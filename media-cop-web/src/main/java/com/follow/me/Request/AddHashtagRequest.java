package com.follow.me.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by divya on 24/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class AddHashtagRequest {

    public AddHashtagRequest(){

    }



    private List<HashTag> hashTagList ;

    public AddHashtagRequest(List<HashTag> hashTagList){
        this.hashTagList = hashTagList ;
    }

    public List<HashTag> getHashTagList() {
        return hashTagList;
    }

    public void setHashTagList(List<HashTag> hashTagList) {
        this.hashTagList = hashTagList;
    }

   /* @Override
    public String toString(){
        return "AddHashtagRequest : HashTagList - "+hashTagList ;
    }
*/

}
