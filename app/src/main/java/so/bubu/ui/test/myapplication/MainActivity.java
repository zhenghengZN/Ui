package so.bubu.ui.test.myapplication;


import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import Util.MyJsonUtil;
import so.bubu.ui.test.mylibrary.Adapter.common.RecyclerViewHolder;
import so.bubu.ui.test.mylibrary.item.ItemSelection;
import so.bubu.ui.test.mylibrary.item.SharePopMenu;
import so.bubu.ui.test.mylibrary.page.common.BaseActivity;
import so.bubu.ui.test.mylibrary.wiget.TypeTitleView;

public class MainActivity extends BaseActivity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.a);
//        LinearLayout viewById = findViewById(R.id.parent);
//        viewById.addView(addBaseContenetView());
//
//
//    }

    @Override
    protected void doBack(int keyCode, KeyEvent event) {

    }

    @Override
    public void doInCreateView() {

    }

    private LinearLayout view;

    public View addBaseContenetView(LinearLayout view11) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        view = inflate.findViewById(R.id.parent);
//        RecyclerView rcv = (RecyclerView) inflate.findViewById(R.id.rcv);

        //4ge  griditem
//        rcv.setLayoutManager(new GridLayoutManager(this, 4));
//        LinkedList<HashMap<String, Object>> mDatasList = new LinkedList<>();
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, Object> objectHashMap = new HashMap<>();
//            objectHashMap.put("title", "title");
//            objectHashMap.put("url", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//            mDatasList.add(objectHashMap);
//        }
//        rcv.setAdapter(new GridItemAdapter(this, mDatasList) {
//            @Override
//            public void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position) {
//
//            }
//
//        });

        //攻略游记
//        LinkedList<HashMap<String, Object>> mDatasList = new LinkedList<>();
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, Object> objectHashMap = new HashMap<>();
//            objectHashMap.put("title", "title");
//            objectHashMap.put("subtitle", "subtitle");
//            objectHashMap.put("url", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//            mDatasList.add(objectHashMap);
//        }
//        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rcv.setAdapter(new TravelsAdapter(this, mDatasList) {
//            @Override
//            public void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position) {
//
//            }
//
//        });

        //推荐布局
//        LinkedList<HashMap<String, Object>> mDatasList = new LinkedList<>();
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, Object> objectHashMap = new HashMap<>();
//            objectHashMap.put("title", "title");
//            objectHashMap.put("content", "subtitleahttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg" +
//                    "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//            objectHashMap.put("like", "subtitle");
//            objectHashMap.put("comment", "subtitle");
//            objectHashMap.put("url", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//            mDatasList.add(objectHashMap);
//        }
//        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rcv.setAdapter(new RecommendedAdapter(this, mDatasList) {
//
//            @Override
//            public void doOther(RecyclerViewHolder viewHolder, HashMap item, int position) {
//
//            }
//        });

        //带图片的编辑view
//        DrawableClearEditText editText = new DrawableClearEditText(this);
//        editText.getLeftDrawable().setImageResource(R.drawable.loading);
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) editText.getLayout().getLayoutParams();
//        layoutParams.setMargins(10,0,10,0);
//        inflate.addView(editText.getView());

        //评论

//        LinkedList<HashMap<String, Object>> mDatasList = new LinkedList<>();
//        LinkedList<String>  urls=new LinkedList<>();
//        urls.add("http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//        urls.add("http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//        urls.add("http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, Object> objectHashMap = new HashMap<>();
//            objectHashMap.put("content", "desc");
//            objectHashMap.put("time", "2017-10-11'T'20:30:10");
//            objectHashMap.put("userName", "name");
//            objectHashMap.put("userPicUrl", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//            objectHashMap.put("urls", urls);
//
//            mDatasList.add(objectHashMap);
//        }
//        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rcv.setAdapter(new CommentItemAdapter(this, mDatasList) {
//            @Override
//            public void setGridItemOnclick(Activity mContext, int position, List<String> urls, AdapterView<?> parent, View view) {
//                ImageActivity.imageBrower((Activity) mContext, ImageActivity.class, position, urls.toArray(new String[urls.size()]));
//            }
//
//            @Override
//            public void doOther(RecyclerViewHolder viewHolder, HashMap<String, Object> item, int position) {
//
//            }
//
//        });
//        RecyclerView rcv = new RecyclerView(this);
//        rcv.setNestedScrollingEnabled(false);
//        rcv.addItemDecoration(new DividerGridItemDecoration(this));
//        HashMap<String, Object> objectHashMap = new HashMap<>();
//        objectHashMap.put("title", "title");
//        objectHashMap.put("content", "subtitleahttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg" +
//                "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpghttp://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//        objectHashMap.put("like", "subtitle");
//        objectHashMap.put("comment", "subtitle");
//        objectHashMap.put("url", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//
//        HashMap<String, Object> object = new HashMap<>();
//        object.put("biz30Day", 37);
//        object.put("finalPrice", 15);
//        object.put("title", "火烈鸟 梦幻卷翘璀璨浓密精细纤长睫毛膏");
//        object.put("couponShareUrl", "https://uland.taobao.com/coupon/edetail?e=tqIfYdOhq%2BAN%2BoQUE6FNzGPHTcqQ4ufIGZPSe9jdjR088L0dUrFHspPccAg7n0MpqjwLHlkAIdkCVzRffmjsmRpywujSvOp2nUIklpPPqYLEXRJ%2BB1et7NS8cpu%2BWjLoqwE8NLNX5iBD%2FBmXgrUwH%2BYsfTfahS6g&pid=mm_119950409_20916506_70766512&af=1&tj1=1&tj2=1");
//        object.put("platform", "淘宝");
//        object.put("couponAmount", 10);
//        object.put("discountPrice", 25);
//        object.put("picUrl", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//
//
//        HashMap<String, Object> recomond = new HashMap<>();
//        object.put("biz30Day", 37);
//        object.put("finalPrice", 15);
//        object.put("title", "火烈鸟 梦幻卷翘璀璨浓密精细纤长睫毛膏");
//        object.put("couponShareUrl", "https://uland.taobao.com/coupon/edetail?e=tqIfYdOhq%2BAN%2BoQUE6FNzGPHTcqQ4ufIGZPSe9jdjR088L0dUrFHspPccAg7n0MpqjwLHlkAIdkCVzRffmjsmRpywujSvOp2nUIklpPPqYLEXRJ%2BB1et7NS8cpu%2BWjLoqwE8NLNX5iBD%2FBmXgrUwH%2BYsfTfahS6g&pid=mm_119950409_20916506_70766512&af=1&tj1=1&tj2=1");
//        object.put("platform", "淘宝");
//        object.put("couponAmount", 10);
//        object.put("discountPrice", 25);
//        object.put("picUrl", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//
//
//        HashMap<String, Object> object1 = new HashMap<>();
//        object1.put("title", "title");
//        object.put("url", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
//
//        LinkedList<MultipleItem> multipleItems = new LinkedList<>();
//
//        GridLayoutManager manager = new GridLayoutManager(this, 4);
//        rcv.setLayoutManager(manager);
////        rcv.addItemDecoration(new Deco);
//        TypeDiffItemAdapter adapter = new TypeDiffItemAdapter(multipleItems) {
//
//            @Override
//            public void doOther(BaseViewHolder viewHolder, MultipleItem multipleItem) {
//
//            }
//
//            @Override
//            public void setGridItemOnclick(Activity mContext, int position, List<String> urls, AdapterView<?> parent, View view) {
//
//            }
//        };
//        rcv.setAdapter(adapter);
//        MultipleItem multipleItem = new MultipleItem(MultipleItem.RECOMMEND, objectHashMap);
//        MultipleItem multipleItem1 = new MultipleItem(MultipleItem.GRIDCOUPONITEM, object);
////        TypeDiffItemAdapter.MultipleItem multipleItem2 = adapter.new MultipleItem(TypeDiffItemAdapter.MultipleItem.GRIDITEM, object1);
//
////        multipleItems.add(multipleItem2);
////        multipleItems.add(multipleItem2);
////        multipleItems.add(multipleItem2);
////        multipleItems.add(multipleItem2);
//
//        multipleItems.add(multipleItem1);
//        multipleItems.add(multipleItem1);
//        multipleItems.add(multipleItem1);
//        multipleItems.add(multipleItem1);
//        multipleItems.add(multipleItem);
//        multipleItems.add(multipleItem);
//        multipleItems.add(multipleItem);
//        multipleItems.add(multipleItem1);
////        multipleItems.add(multipleItem1);
//
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                Toast.makeText(MainActivity.this, "onclick" + i, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        StrokeButton strokeButton = new StrokeButton(this);
//        strokeButton.setText("ddd");
//        strokeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, TestSearchActivity.class));
//            }
//        });
//        adapter.addHeaderView(strokeButton);
//
//
//        LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.a, null, false);
////        inflate.addView(rcv);
//        View id = inflate.findViewById(R.id.mydcet);
//        ImageView view = id.findViewById(R.id.v_icon_user);
//        Log.e("zhengheng", "" + view);
//        view.setImageResource(R.drawable.loading);
//
//        DrawableClearEditText drawableClearEditText = new DrawableClearEditText(this);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        drawableClearEditText.setLayoutParams(layoutParams);
//        drawableClearEditText.setmTextColor(0xffff0000);
//        drawableClearEditText.setmStrokeColor(0xff00ff00);
//        inflate.addView(drawableClearEditText);
//
//        new SolidButton(this);

        String mainJson = MyJsonUtil.getJson(this, "MainJson");
        ArrayList<LinkedHashMap<String, Object>> weightList = MyJsonUtil.getWeightList(mainJson);
        feachWeight(weightList);

        return inflate;
    }


    public void feachWeight(ArrayList<LinkedHashMap<String, Object>> list) {
        for (LinkedHashMap<String, Object> object : list) {
            String type = (String) object.get("type");
            JSONArray objects = (JSONArray) object.get("objects");
            switch (type) {
                case "ItemSelection":
//                    ArrayList<JSONObject> itemselectionobject = MyJsonUtil.JsonArray2JsonObject(objects);
//                    for(JSONObject jsonObject : itemselectionobject) {
//                        ItemSelection itemSelection = new ItemSelection(this);
//                        itemSelection.init();
//                    }
                    ItemSelection itemSelection = new ItemSelection(this);
                    String headerTitle = (String) object.get("headerTitle");
                    itemSelection.init(objects, headerTitle);
                    view.addView(itemSelection);
                    break;
                case "TypeTitleView":
                    ArrayList<JSONObject> typeTitleObject = MyJsonUtil.JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : typeTitleObject) {
                        TypeTitleView titleview = new TypeTitleView(this);
                        titleview.init(jsonObject);
                        view.addView(titleview);
                    }
                    break;
            }
        }
    }
//    @Override
//    public void doInCreateView() {
//        Button btn = (Button) findViewById(R.id.test);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
//            }
//        });
////        setBackClick();
//    }

    public void solidbtn(View view) {
        Toast.makeText(this, "SolidButton", Toast.LENGTH_SHORT).show();
        //popupwindw分享
        final LinkedList<HashMap<String, Object>> mDatasList = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, Object> objectHashMap = new HashMap<>();
            objectHashMap.put("title", "title");
            objectHashMap.put("url", "http://gd2.alicdn.com/imgextra/i2/1810079026/TB2vMVedZbI8KJjy1zdXXbe1VXa_!!1810079026.jpg");
            mDatasList.add(objectHashMap);
        }
        SharePopMenu<HashMap<String, Object>> sharePopMenu = new SharePopMenu<HashMap<String, Object>>(this, mDatasList) {
//            @Override
//            public void addList(LinkedList<HashMap<String, Object>> shareBeanList) {
//                shareBeanList = mDatasList;
//            }

            @Override
            public void doSomethingInconvert(RecyclerViewHolder viewHolder, Object item, int position) {

            }
        };
        sharePopMenu.setPopWindowItemClick(new SharePopMenu.PopWindowItemClick() {
            @Override
            public void onItemClick() {
                Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick() {
                Toast.makeText(MainActivity.this, "onItemLongClick", Toast.LENGTH_SHORT).show();

            }
        });
        sharePopMenu.showPopupWindow(view, MainActivity.this);

    }

    public void strokebtn(View view) {
//        startActivity(new Intent(MainActivity.this,TabActivity.class));
        startActivity(new Intent(MainActivity.this, ImageTopActivity.class));
//        startActivity(new Intent(MainActivity.this, MyBannerActivity.class));
//        startActivity(new Intent(MainActivity.this, TestSearchActivity.class));
    }
}
