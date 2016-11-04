package com.follow.me.model;

import java.util.List;

/**
 * Created by divya on 31/10/16.
 */
public class GetTopHashTagResponse extends BaseResponse{

    private List<String> hashtags ;

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }


}
