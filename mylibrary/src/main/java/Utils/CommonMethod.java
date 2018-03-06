package Utils;


import java.util.TimeZone;

/**
 * 公用方法
 *
 * Created by Administrator on 2016/3/22.
 */
public class CommonMethod {

    public static String getThumbUrl(String url, int width, int height) {
        return getThumbUrl(url, width, height, 90);
    }

    public static String getThumbUrl(String url, int width, int height, int quality) {
        if ( (url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg"))) {
            url += ("?imageView/1/w/" + width + "/h/" + height + "/q/" + quality + "/format/jpg");
        }
        return url;
    }


    public static String showTime(String time) {
        if (Helper.isEmpty(time)) {
            return "刚刚";
        }
        String showContent;
        time = TimeUtil.converTime(time, TimeZone.getDefault());
        long sendTime = TimeHelper.getTimeLong(time, TimeHelper.YYYYMMDDHHMMSS);
        long currentTime = TimeHelper.getTimestamp();
        long thisYearTime = TimeHelper.getTimesThisYear();
        long todayTimestamp = TimeHelper.getTimesmorning();
        long yesterdayTimestamp = todayTimestamp - 60 * 60 * 24;
        long beforeYesterdayTimestamp = yesterdayTimestamp - 60 * 60 * 24;
        int timeDifference = (int) (currentTime - sendTime);
        if (sendTime < thisYearTime) {
            showContent = TimeHelper.timeToData(sendTime, TimeHelper.YYYYMMDD);
        } else if (sendTime < beforeYesterdayTimestamp) {
            showContent = TimeHelper.timeToData(sendTime, TimeHelper.MMDD);
        } else if (sendTime < yesterdayTimestamp) {
            showContent = "前天 " + TimeHelper.timeToData(sendTime, TimeHelper.HHMM);
        } else if (sendTime < todayTimestamp) {
            showContent = "昨天 " + TimeHelper.timeToData(sendTime, TimeHelper.HHMM);
        } else if (sendTime == currentTime) {
            showContent = "刚刚";
        } else if (60 > timeDifference) {
            showContent = (0 > timeDifference ? 0 : timeDifference) + "秒前";
        } else if (60 * 60 > timeDifference) {
            timeDifference = timeDifference / 60;
            showContent = (0 > timeDifference ? 0 : timeDifference) + "分钟前";
        } else {
            timeDifference = timeDifference / 60 / 60;
            showContent = (0 > timeDifference ? 0 : timeDifference) + "小时前";
        }

        return showContent;
    }
}