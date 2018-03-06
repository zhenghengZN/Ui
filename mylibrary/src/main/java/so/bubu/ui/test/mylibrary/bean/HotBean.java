package so.bubu.ui.test.mylibrary.bean;

import java.util.List;

/**
 * Created by zhengheng on 17/12/26.
 */
public class HotBean {

    /**
     * backgroundImage : {"createdAt":"2017-11-06T08:27:52.124Z","file":{"dataAvailable":true,"dirty":false,"fileObject":{"@type":"com.avos.avoscloud.AVObject","objectId":"5a001d07570c3500639ca320","updatedAt":null,"createdAt":null,"className":"_File","serverData":{"@type":"java.util.HashMap"}},"metaData":{"__source":"external","_name":"迪士尼(2).png"},"name":"5a001d07570c3500639ca320","objectId":"5a001d07570c3500639ca320","originalName":"迪士尼(2).png","size":-1,"url":"http://ac-egDFgpKJ.clouddn.com/ce2a27409638e6dc83db.png"},"objectId":"5a001d0867f356006332a341","originalName":"迪士尼(2).png","updatedAt":"2017-11-06T08:27:52.124Z"}
     * backgroundImageUrl350 : http://ac-egDFgpKJ.clouddn.com/ce2a27409638e6dc83db.png?imageView/2/w/350/h/350/q/100/format/png
     * desc : 迪士尼里基本每个项目都值得去体验一番，能在一天内刷遍所有项目固然是好，但如果你觉得赶刷项目太累，喜欢慢节奏的体验，就去体验这些项目，保证让你有不虚此行的快感！
     * favorites : 292
     * iconUrl :
     * id : 58a68d1b128fe1006475fbc6
     * isFeature : true
     * isPublished : true
     * location : {"id":"581c98028ac247004fe7998c"}
     * objectId : 58a68d1b128fe1006475fbc6
     * objects : [{"objectId":"58a69067128fe1006cb5b105"},{"objectId":"58a691f661ff4b78fca37896"},{"objectId":"58a693608d6d81005827e6c0"},{"objectId":"58a696da61ff4b0062acfcc0"},{"objectId":"58a6970761ff4b006c49aae7"},{"objectId":"58a69b082f301e006d8ea402"},{"objectId":"58a69d4261ff4b006c49d768"},{"objectId":"58a6a0b6570c350069987d3e"}]
     * subtitle : 错过这些怎么对得起门票钱？
     * tags : 经典,探险,情侣,刺激
     * title : 上海迪士尼必玩 8 大项
     * type : Attraction
     * weight : 2000
     */

    private BackgroundImageBean backgroundImage;
    private String backgroundImageUrl350;
    private String desc;
    private int favorites;
    private String iconUrl;
    private String id;
    private boolean isFeature;
    private boolean isPublished;
    private LocationBean location;
    private String objectId;
    private String subtitle;
    private String tags;
    private String title;
    private String type;
    private int weight;
    private List<ObjectsBean> objects;

    public BackgroundImageBean getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BackgroundImageBean backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundImageUrl350() {
        return backgroundImageUrl350;
    }

    public void setBackgroundImageUrl350(String backgroundImageUrl350) {
        this.backgroundImageUrl350 = backgroundImageUrl350;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsFeature() {
        return isFeature;
    }

    public void setIsFeature(boolean isFeature) {
        this.isFeature = isFeature;
    }

    public boolean isIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<ObjectsBean> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsBean> objects) {
        this.objects = objects;
    }

    public static class BackgroundImageBean {
        /**
         * createdAt : 2017-11-06T08:27:52.124Z
         * file : {"dataAvailable":true,"dirty":false,"fileObject":{"@type":"com.avos.avoscloud.AVObject","objectId":"5a001d07570c3500639ca320","updatedAt":null,"createdAt":null,"className":"_File","serverData":{"@type":"java.util.HashMap"}},"metaData":{"__source":"external","_name":"迪士尼(2).png"},"name":"5a001d07570c3500639ca320","objectId":"5a001d07570c3500639ca320","originalName":"迪士尼(2).png","size":-1,"url":"http://ac-egDFgpKJ.clouddn.com/ce2a27409638e6dc83db.png"}
         * objectId : 5a001d0867f356006332a341
         * originalName : 迪士尼(2).png
         * updatedAt : 2017-11-06T08:27:52.124Z
         */

        private String createdAt;
        private FileBean file;
        private String objectId;
        private String originalName;
        private String updatedAt;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public FileBean getFile() {
            return file;
        }

        public void setFile(FileBean file) {
            this.file = file;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public static class FileBean {
            /**
             * dataAvailable : true
             * dirty : false
             * fileObject : {"@type":"com.avos.avoscloud.AVObject","objectId":"5a001d07570c3500639ca320","updatedAt":null,"createdAt":null,"className":"_File","serverData":{"@type":"java.util.HashMap"}}
             * metaData : {"__source":"external","_name":"迪士尼(2).png"}
             * name : 5a001d07570c3500639ca320
             * objectId : 5a001d07570c3500639ca320
             * originalName : 迪士尼(2).png
             * size : -1
             * url : http://ac-egDFgpKJ.clouddn.com/ce2a27409638e6dc83db.png
             */

            private boolean dataAvailable;
            private boolean dirty;
            private FileObjectBean fileObject;
            private MetaDataBean metaData;
            private String name;
            private String objectId;
            private String originalName;
            private int size;
            private String url;

            public boolean isDataAvailable() {
                return dataAvailable;
            }

            public void setDataAvailable(boolean dataAvailable) {
                this.dataAvailable = dataAvailable;
            }

            public boolean isDirty() {
                return dirty;
            }

            public void setDirty(boolean dirty) {
                this.dirty = dirty;
            }

            public FileObjectBean getFileObject() {
                return fileObject;
            }

            public void setFileObject(FileObjectBean fileObject) {
                this.fileObject = fileObject;
            }

            public MetaDataBean getMetaData() {
                return metaData;
            }

            public void setMetaData(MetaDataBean metaData) {
                this.metaData = metaData;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getOriginalName() {
                return originalName;
            }

            public void setOriginalName(String originalName) {
                this.originalName = originalName;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class FileObjectBean {
                private String objectId;
                private Object updatedAt;
                private Object createdAt;
                private String className;

                public String getObjectId() {
                    return objectId;
                }

                public void setObjectId(String objectId) {
                    this.objectId = objectId;
                }

                public Object getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(Object updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public Object getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(Object createdAt) {
                    this.createdAt = createdAt;
                }

                public String getClassName() {
                    return className;
                }

                public void setClassName(String className) {
                    this.className = className;
                }

            }

            public static class MetaDataBean {
                /**
                 * __source : external
                 * _name : 迪士尼(2).png
                 */

                private String __source;
                private String _name;

                public String get__source() {
                    return __source;
                }

                public void set__source(String __source) {
                    this.__source = __source;
                }

                public String get_name() {
                    return _name;
                }

                public void set_name(String _name) {
                    this._name = _name;
                }
            }
        }
    }

    public static class LocationBean {
        /**
         * id : 581c98028ac247004fe7998c
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ObjectsBean {
        /**
         * objectId : 58a69067128fe1006cb5b105
         */

        private String objectId;

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }
    }
}
