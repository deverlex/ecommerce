package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.base.BaseDomain;

import java.util.Date;

public class SubCategory extends BaseDomain {

    private static final long serialVersionUID = 166364535345345L;

    private String subCategoryName;
    private String refCategoryName;
    private short refLevel;
    private boolean isNext;
    private Date lastUpdatedTime;
    private long lastUpdatedBy;

    public SubCategory() {
        super();
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getRefCategoryName() {
        return refCategoryName;
    }

    public void setRefCategoryName(String refCategoryName) {
        this.refCategoryName = refCategoryName;
    }

    public short getRefLevel() {
        return refLevel;
    }

    public void setRefLevel(short refLevel) {
        this.refLevel = refLevel;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
