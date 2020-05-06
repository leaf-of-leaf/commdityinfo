package com.kj.commdityinfo.bean;

import java.io.Serializable;
import java.util.Date;

public class ItemInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.iteminfo_id
     *
     * @mbg.generated
     */
    private Integer iteminfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.location
     *
     * @mbg.generated
     */
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.sale_time
     *
     * @mbg.generated
     */
    private Date saleTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.start
     *
     * @mbg.generated
     */
    private Integer start;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.person_num
     *
     * @mbg.generated
     */
    private Integer personNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.price
     *
     * @mbg.generated
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.price_link
     *
     * @mbg.generated
     */
    private String priceLink;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iteminfo.isexists
     *
     * @mbg.generated
     */
    private Integer isexists;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table iteminfo
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.iteminfo_id
     *
     * @return the value of iteminfo.iteminfo_id
     *
     * @mbg.generated
     */
    public Integer getIteminfoId() {
        return iteminfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.iteminfo_id
     *
     * @param iteminfoId the value for iteminfo.iteminfo_id
     *
     * @mbg.generated
     */
    public void setIteminfoId(Integer iteminfoId) {
        this.iteminfoId = iteminfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.name
     *
     * @return the value of iteminfo.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.name
     *
     * @param name the value for iteminfo.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.location
     *
     * @return the value of iteminfo.location
     *
     * @mbg.generated
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.location
     *
     * @param location the value for iteminfo.location
     *
     * @mbg.generated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.sale_time
     *
     * @return the value of iteminfo.sale_time
     *
     * @mbg.generated
     */
    public Date getSaleTime() {
        return saleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.sale_time
     *
     * @param saleTime the value for iteminfo.sale_time
     *
     * @mbg.generated
     */
    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.content
     *
     * @return the value of iteminfo.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.content
     *
     * @param content the value for iteminfo.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.start
     *
     * @return the value of iteminfo.start
     *
     * @mbg.generated
     */
    public Integer getStart() {
        return start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.start
     *
     * @param start the value for iteminfo.start
     *
     * @mbg.generated
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.person_num
     *
     * @return the value of iteminfo.person_num
     *
     * @mbg.generated
     */
    public Integer getPersonNum() {
        return personNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.person_num
     *
     * @param personNum the value for iteminfo.person_num
     *
     * @mbg.generated
     */
    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.price
     *
     * @return the value of iteminfo.price
     *
     * @mbg.generated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.price
     *
     * @param price the value for iteminfo.price
     *
     * @mbg.generated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.price_link
     *
     * @return the value of iteminfo.price_link
     *
     * @mbg.generated
     */
    public String getPriceLink() {
        return priceLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.price_link
     *
     * @param priceLink the value for iteminfo.price_link
     *
     * @mbg.generated
     */
    public void setPriceLink(String priceLink) {
        this.priceLink = priceLink == null ? null : priceLink.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iteminfo.isexists
     *
     * @return the value of iteminfo.isexists
     *
     * @mbg.generated
     */
    public Integer getIsexists() {
        return isexists;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iteminfo.isexists
     *
     * @param isexists the value for iteminfo.isexists
     *
     * @mbg.generated
     */
    public void setIsexists(Integer isexists) {
        this.isexists = isexists;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iteminfo
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", iteminfoId=").append(iteminfoId);
        sb.append(", name=").append(name);
        sb.append(", location=").append(location);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", content=").append(content);
        sb.append(", start=").append(start);
        sb.append(", personNum=").append(personNum);
        sb.append(", price=").append(price);
        sb.append(", priceLink=").append(priceLink);
        sb.append(", isexists=").append(isexists);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}