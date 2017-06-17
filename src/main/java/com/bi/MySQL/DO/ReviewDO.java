package com.bi.MySQL.DO;

/**
 * Created by cz on 2017/6/15.
 */
public class ReviewDO {
    private Integer Id;
    private String allReviews;

    public ReviewDO(Integer id, String allReviews) {
        Id = id;
        this.allReviews = allReviews;
    }

    public ReviewDO() {
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setAllReviews(String allReviews) {
        this.allReviews = allReviews;
    }

    public Integer getId(){return Id;}
    public String getAllReviews(){return allReviews;}
}
