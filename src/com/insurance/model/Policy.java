package com.insurance.model;

public class Policy {
    private int policyId;
    private String policyName;
    private String category;
    private String subCategory;
    private double price;

    public Policy(int policyId, String policyName, String category, String subCategory, double price) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.category = category;
        this.subCategory = subCategory;
        this.price = price;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
