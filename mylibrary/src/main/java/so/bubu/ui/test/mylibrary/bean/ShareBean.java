package so.bubu.ui.test.mylibrary.bean;


import android.graphics.drawable.Drawable;

/**
 * @author linhuan on 2016/12/1 下午2:44
 */
public class ShareBean {

    private Drawable shareIcon;
    private String shareTitle;
    private int shareColor;

    public ShareBean(Drawable shareIcon, String shareTitle, int shareColor) {
        this.shareIcon = shareIcon;
        this.shareTitle = shareTitle;
        this.shareColor = shareColor;
    }

    public Drawable getShareIcon() {
        return shareIcon;
    }

    public void setShareIcon(Drawable shareIcon) {
        this.shareIcon = shareIcon;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public int getShareColor() {
        return shareColor;
    }

    public void setShareColor(int shareColor) {
        this.shareColor = shareColor;
    }
}
