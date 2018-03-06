package so.bubu.ui.test.mylibrary.uploadimage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventEntity implements Serializable {
    public int what;
    public List<LocalMedia> medias = new ArrayList<>();

    public EventEntity() {
        super();
    }

    public EventEntity(int what) {
        super();
        this.what = what;
    }

    public EventEntity(int what, List<LocalMedia> medias) {
        super();
        this.what = what;
        this.medias = medias;
    }
}
