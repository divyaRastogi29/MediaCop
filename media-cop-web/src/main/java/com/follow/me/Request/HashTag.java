package com.follow.me.Request;

/**
 * Created by divya on 24/10/16.
 */
public class HashTag {

    private String name ;

    private float priority ;

    private String country ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

   /* @Override
    public String toString(){
        return "Hashtag : [name - "+name+" , priority - "+" , country - "+country ;
    }*/

}
