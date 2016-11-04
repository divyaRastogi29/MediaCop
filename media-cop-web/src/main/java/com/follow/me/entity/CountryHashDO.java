package com.follow.me.entity;

import com.follow.me.util.StringListConverter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by divya on 31/10/16.
 */

@Entity
@Table(name = "CountryHash")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class CountryHashDO {

    @Id
    @Column(name = "country" , unique = true)
    String country ;

    @Column(name = "hashtags")
    @Convert(converter = StringListConverter.class)
    List<String> hashtags ;

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


    @Override
    public String toString(){
        return "CountryHash : country - "+country+" hashtags - "+hashtags+" ]" ;
    }
}
