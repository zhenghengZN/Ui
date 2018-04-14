package Util;

import java.util.HashMap;

/**
 * Created by zhengheng on 18/2/11.
 */
public class UrlUtils {

    public static void openUrl(String url) {
        if (url.contains("redirectAppPage?")) {
            HashMap<String, Object> parammap = url2Map(url);
            String action = (String) parammap.get("action");

            switch (action) {
                case "ActionSheet":
//                    new ActionSheet()
            }
        }
    }


//    public
//
//    public void setDoAction() {
//
//    }

    public static HashMap url2Map(String url) {
        HashMap<String, Object> parammap = null;
        if (url != null && !(url.isEmpty())) {
            String[] urlstring = url.split("\\?");
            String[] parameters = urlstring[1].split("&");
            parammap = new HashMap<>();
            for (int i = 0; i < parameters.length; i++) {
                String[] keyAndvalue = parameters[i].split("=");
                parammap.put(keyAndvalue[0], keyAndvalue[1]);
            }
        }
        return parammap;
    }
}
