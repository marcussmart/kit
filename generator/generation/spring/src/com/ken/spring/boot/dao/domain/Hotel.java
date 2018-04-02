package com.ken.spring.boot.dao.domain;

import java.io.Serializable;

public class Hotel implements Serializable {
    private static final long serialVersionUID = -1449899732516439164L;

    /*
     * id
     */
    private Integer id;

    /*
     * 
     */
    private Integer city;

    /*
     * 
     */
    private String name;

    /*
     * 
     */
    private String address;

    /*
     * 
     */
    private String zip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", city=").append(city);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", zip=").append(zip);
        sb.append("]");
        return sb.toString();
    }
}