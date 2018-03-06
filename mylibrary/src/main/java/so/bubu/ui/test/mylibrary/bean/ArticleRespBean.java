package so.bubu.ui.test.mylibrary.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author linhuan on 16/6/28 上午11:53
 */
public class ArticleRespBean implements Serializable {

    private String id;
    private int index;
    private String title;
    private String desc;
    private String bestTime;
    private String notes;
    private int favorites;
    private int reviewCount;
    private int likes;
    private int allDay;
    private int day;
    private String tips;
    private String checkListItems;
    private String links;
    private String rowStatus;
    private List<ImageRespBean> photos;
//    private List<PlaceRespBean> places;
    private String objectId;
    private String suggestedPrice;
    private String suggestedBrands;
    private String rating;
    private String website;

    public ArticleRespBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBestTime() {
        return bestTime;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAllDay() {
        return allDay;
    }

    public void setAllDay(int allDay) {
        this.allDay = allDay;
    }

    public String getCheckListItems() {
        return checkListItems;
    }

    public void setCheckListItems(String checkListItems) {
        this.checkListItems = checkListItems;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(String rowStatus) {
        this.rowStatus = rowStatus;
    }

    public List<ImageRespBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ImageRespBean> photos) {
        this.photos = photos;
    }

//    public List<PlaceRespBean> getPlaces() {
//        return places;
//    }
//
//    public void setPlaces(List<PlaceRespBean> places) {
//        this.places = places;
//    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(String suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public String getSuggestedBrands() {
        return suggestedBrands;
    }

    public void setSuggestedBrands(String suggestedBrands) {
        this.suggestedBrands = suggestedBrands;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
