package so.bubu.ui.test.mylibrary.bean;

import java.io.Serializable;

/**
 * @author linhuan on 16/6/24 下午7:38
 */
public class ObjectIdRespBean implements Serializable {

    private String objectId;

    public ObjectIdRespBean() {

    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
