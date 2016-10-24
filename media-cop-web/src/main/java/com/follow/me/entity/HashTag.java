package com.follow.me.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by divya on 20/10/16.
 */

@Entity
@Table(name="HashTag")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class HashTag {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;

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
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    Date created ;

    @Column(name = "lastUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    Date lastUpdated ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString(){
        return "HashTag : [ id : "+id+" name : "+name+" rank : "+
                rank+" priority : "+priority+" country : "+country+" created : "+created+" updated : "+lastUpdated+" ]";
    }

}
