package com.bi.Model;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by cz on 2017/6/15.
 */
public class Products {
    private Integer Id;
    private String ASIN;
    private String title;
    private String groups;
    private String similar;
    private Integer salesrank;
    private Integer categories;
    private String reviews;

    public Integer getId(){return Id;}

    public String getASIN(){return ASIN;}

    public String getTitle(){return title;}

    public String getGroups(){return groups;}

    public String getSimilar(){return similar;}

    public Integer getSalesrank(){return salesrank;}

    public Integer getCategories(){return categories;}

    public String getReviews(){return reviews;}

}
