
package in.insiderapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("tag_id")
    @Expose
    private String tagId;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("is_primary_interest")
    @Expose
    private Boolean isPrimaryInterest;
    @SerializedName("is_pick")
    @Expose
    private Boolean isPick;
    @SerializedName("is_carousel")
    @Expose
    private Boolean isCarousel;
    @SerializedName("is_featured")
    @Expose
    private Boolean isFeatured;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsPrimaryInterest() {
        return isPrimaryInterest;
    }

    public void setIsPrimaryInterest(Boolean isPrimaryInterest) {
        this.isPrimaryInterest = isPrimaryInterest;
    }

    public Boolean getIsPick() {
        return isPick;
    }

    public void setIsPick(Boolean isPick) {
        this.isPick = isPick;
    }

    public Boolean getIsCarousel() {
        return isCarousel;
    }

    public void setIsCarousel(Boolean isCarousel) {
        this.isCarousel = isCarousel;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

}
