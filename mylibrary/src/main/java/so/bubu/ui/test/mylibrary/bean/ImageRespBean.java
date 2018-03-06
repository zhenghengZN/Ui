package so.bubu.ui.test.mylibrary.bean;

import java.io.Serializable;

/**
 * @author linhuan on 16/5/25 下午2:19
 */
public class ImageRespBean implements Serializable {

    private FileRespBean file;
    private String profileUrl;
//    private String originalName;
    private String url;
//    private String objectId;
//    private String createdAt;
//    private String updatedAt;
    private String thumbnail100;

    public ImageRespBean() {

    }

    public FileRespBean getFile() {
        return file;
    }

    public void setFile(FileRespBean file) {
        this.file = file;
    }

//    public String getOriginalName() {
//        return originalName;
//    }
//
//    public void setOriginalName(String originalName) {
//        this.originalName = originalName;
//    }
//
//    public String getObjectId() {
//        return objectId;
//    }
//
//    public void setObjectId(String objectId) {
//        this.objectId = objectId;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail100() {
        return thumbnail100;
    }

    public void setThumbnail100(String thumbnail100) {
        this.thumbnail100 = thumbnail100;
    }
}
