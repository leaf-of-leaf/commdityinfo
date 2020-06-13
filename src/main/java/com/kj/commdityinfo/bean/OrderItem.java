package com.kj.commdityinfo.bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OrderItem implements Serializable {
    private Integer orderItemId;
    @NotNull
    private Integer itemId;

    private String orderNum;
    @NotNull
    private Integer buyCounts;

    private Double price;

    private Double paid;
    @NotNull
    private Integer userId;

    private Item item;

    private static final long serialVersionUID = 1L;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item=item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", itemId=").append(itemId);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", buyCounts=").append(buyCounts);
        sb.append(", price=").append(price);
        sb.append(", paid=").append(paid);
        sb.append(", userId=").append(userId);
        sb.append(", item=").append(item);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}