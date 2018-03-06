package so.bubu.ui.test.mylibrary.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author linhuan on 16/6/24 下午7:36
 */
public class GuideRespBean implements Serializable {

    private String id;
    private String objectId;
    private String title;
    private String subtitle;
    private String type;
    private String desc;
    private int weight;
    private boolean isFeature;
    private ImageRespBean backgroundImage;
    private ObjectIdRespBean location;
//    private List<ArticleRespBean> objects;
    private String tags;
    private AuthorRespBean author;


    public GuideRespBean() {

    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isFeature() {
        return isFeature;
    }

    public void setFeature(boolean feature) {
        isFeature = feature;
    }

    public ImageRespBean getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(ImageRespBean backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public ObjectIdRespBean getLocation() {
        return location;
    }

    public void setLocation(ObjectIdRespBean location) {
        this.location = location;
    }

//    public List<ArticleRespBean> getObjects() {
//        return objects;
//    }
//
//    public void setObjects(List<ArticleRespBean> objects) {
//        this.objects = objects;
//    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public AuthorRespBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorRespBean author) {
        this.author = author;
    }
}
