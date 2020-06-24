package com.kj.commdityinfo.bean;

import com.kj.commdityinfo.annotation.IgnoreSwaggerParameter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    private Integer itemId;

    private String name;

    private String location;

    private Date saleTime;

    private String content;

    private Integer star;

    private Integer personNum;

    private Double price;

    private Integer num;

    private String priceLink;

    private Integer isexists;

    private String img;

    private Integer cateId;

    @IgnoreSwaggerParameter
    private CateItem cateItem;

    private static final long serialVersionUID = 1L;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public CateItem getCateItem() {
        return cateItem;
    }

    public void setCateItem(CateItem cateItem) {
        this.cateItem=cateItem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", name=").append(name);
        sb.append(", location=").append(location);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", content=").append(content);
        sb.append(", star=").append(star);
        sb.append(", personNum=").append(personNum);
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", priceLink=").append(priceLink);
        sb.append(", isexists=").append(isexists);
        sb.append(", img=").append(img);
        sb.append(", cateId=").append(cateId);
        sb.append(", cateItem=").append(cateItem);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}