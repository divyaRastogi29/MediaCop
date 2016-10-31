package com.follow.me.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by divya on 20/10/16.
 */

@Entity
@Table(name="HashTag")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class HashTagDO {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @Column(name = "name")
    @Type(type = "text")
    String name ;

    @Column(name = "rank")
    int rank ;

    @Column(name = "priority")
    float priority ;

    @Column(name = "country")
    @Type(type = "text")
    String country ;

    @Column(name = "created")
    Long created ;

    @Column(name = "lastUpdated")
    Long lastUpdated ;

    @Column(name = "count")
    Long count ;

   public HashTagDO(){

    }

   public HashTagDO(String name ,int rank, float priority, String country , Long created, Long updated){
        this.name = name ;
        this.rank = rank ;
        this.priority = priority ;
        this.country = country ;
        this.created = created ;
        this.lastUpdated = updated ;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString(){
        return "HashTagDO : [id : "+id+" name : "+name+" rank : "+
                rank+" priority : "+priority+" country : "+country+" created : "+created+" updated : "+lastUpdated+" count : ["+count+" ]";
    }

}
