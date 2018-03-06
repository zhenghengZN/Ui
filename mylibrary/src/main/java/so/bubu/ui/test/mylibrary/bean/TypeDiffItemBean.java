package so.bubu.ui.test.mylibrary.bean;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by zhengheng on 18/1/16.
 */
public class TypeDiffItemBean {
    public String type;
    public LinkedList<HashMap> objects;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinkedList<HashMap> getObjects() {
        return objects;
    }

    public void setObjects(LinkedList<HashMap> objects) {
        this.objects = objects;
    }

    public TypeDiffItemBean(String type, LinkedList<HashMap> objects) {
        this.type = type;
        this.objects = objects;
    }
}
