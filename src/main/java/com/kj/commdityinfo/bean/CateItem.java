package com.kj.commdityinfo.bean;

import java.io.Serializable;

public class CateItem implements Serializable {
    private Integer cateId;

    private String title;

    private Integer navId;

    private NavItem navItem;

    private static final long serialVersionUID = 1L;

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public NavItem getNavItem() {
        return navItem;
    }

    public void setNavItem(NavItem navItem) {
        this.navItem=navItem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cateId=").append(cateId);
        sb.append(", title=").append(title);
        sb.append(", navId=").append(navId);
        sb.append(", navItem=").append(navItem);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}