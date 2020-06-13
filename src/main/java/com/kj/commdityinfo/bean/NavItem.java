package com.kj.commdityinfo.bean;

import com.kj.commdityinfo.annotation.IgnoreSwaggerParameter;

import java.io.Serializable;
import java.util.List;

public class NavItem implements Serializable {
    private Integer navId;

    private String title;
    @IgnoreSwaggerParameter
    private List<CateItem> cateItems;

    private static final long serialVersionUID = 1L;

    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public List<CateItem> getCateItems() {
        return cateItems;
    }

    public void setCateItems(List<CateItem> cateItems) {
        this.cateItems=cateItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", navId=").append(navId);
        sb.append(", title=").append(title);
        sb.append(", cateItems=").append(cateItems);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}