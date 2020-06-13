package com.kj.commdityinfo.bean;

import com.kj.commdityinfo.annotation.IgnoreSwaggerParameter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class UserOrder implements Serializable {

    public static final Integer UNPAY = 0;
    public static final Integer PAYING = 1;
    public static final Integer SUCCESS = 2;

    private Integer orderId;

    @NotNull
    private String orderNum;

    @NotNull
    private String orderName;

    @NotNull
    private String orderDescription;

    private Double paidAccount;

    private Date createTime;

    private Integer status;

    @NotNull
    private Integer userId;

    @IgnoreSwaggerParameter
    private User user;

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription == null ? null : orderDescription.trim();
    }

    public Double getPaidAccount() {
        return paidAccount;
    }

    public void setPaidAccount(Double paidAccount) {
        this.paidAccount = paidAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user=user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", orderName=").append(orderName);
        sb.append(", orderDescription=").append(orderDescription);
        sb.append(", paidAccount=").append(paidAccount);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", user=").append(user);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}