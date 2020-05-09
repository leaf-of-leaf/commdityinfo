package com.kj.commdityinfo.bean;

import java.io.Serializable;
import java.util.Date;

public class ItemInfo implements Serializable {
    private Integer id;

    private String name;

    private String location;

    private Date saleTime;

    private String content;

    private Integer start;

    private Integer personNum;

    private Double price;

    private String priceLink;

    private Integer isexists;

    private String img;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceLink() {
        return priceLink;
    }

    public void setPriceLink(String priceLink) {
        this.priceLink = priceLink == null ? null : priceLink.trim();
    }

    public Integer getIsexists() {
        return isexists;
    }

    public void setIsexists(Integer isexists) {
        this.isexists = isexists;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", location=").append(location);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", content=").append(content);
        sb.append(", start=").append(start);
        sb.append(", personNum=").append(personNum);
        sb.append(", price=").append(price);
        sb.append(", priceLink=").append(priceLink);
        sb.append(", isexists=").append(isexists);
        sb.append(", img=").append(img);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}