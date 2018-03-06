package so.bubu.ui.test.mylibrary.uploadimage.observable;

import java.util.List;

import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMedia;
import so.bubu.ui.test.mylibrary.uploadimage.model.LocalMediaFolder;

public interface SubjectListener {
    void add(ObserverListener observerListener);

    void notifyFolderObserver(List<LocalMediaFolder> folders);

    void notifySelectLocalMediaObserver(List<LocalMedia> medias);

    void remove(ObserverListener observerListener);
}
