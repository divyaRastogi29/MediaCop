package com.follow.me.service;

import com.follow.me.Request.HashTag;

import java.util.List;

/**
 * Created by divya on 24/10/16.
 */
public interface HashService {

    public boolean updateHashTag(List<HashTag> hashTagList);

    public List<String> getHashTagByCountry(String name) ;
}
