package com.bi.MySQL.DO;

/**
 * Created by cz on 2017/6/15.
 */

public class ProductDO {
    private Integer Id;
    private String ASIN;
    private String title;
    private String groups;
    private String similar;
    private Integer salesrank;
    private Integer categories;
    private String reviews;

    public ProductDO(Integer id, String ASIN, String title, String groups, String similar, Integer salesrank, Integer categories, String reviews) {
        Id = id;
        this.ASIN = ASIN;
        this.title = title;
        this.groups = groups;
        this.similar = similar;
        this.salesrank = salesrank;
        this.categories = categories;
        this.reviews = reviews;
    }

    public ProductDO(ProductDO p) {
        this.Id = p.getId();
        this.ASIN = p.getASIN();
        this.title = p.getTitle();
        this.groups = p.getGroups();
        this.similar = p.getSimilar();
        this.salesrank = p.getSalesrank();
        this.categories = p.getCategories();
        this.reviews = p.getReviews();

    }

    public ProductDO() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getASIN() {
        return ASIN;
    }

    public void setASIN(String ASIN) {
        this.ASIN = ASIN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = similar;
    }

    public Integer getSalesrank() {
        return salesrank;
    }

    public void setSalesrank(Integer salesrank) {
        this.salesrank = salesrank;
    }

    public Integer getCategories() {
        return categories;
    }

    public void setCategories(Integer categories) {
        this.categories = categories;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
