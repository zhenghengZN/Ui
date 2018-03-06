package so.bubu.ui.test.mylibrary.bean;

import java.io.Serializable;

/**
 * @author linhuan on 2017/1/19 上午11:33
 */
public class AuthorRespBean implements Serializable {

    private String id;
    private String name;
    private String intro;
    private String profileUrl;

    public AuthorRespBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
