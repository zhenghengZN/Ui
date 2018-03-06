package so.bubu.ui.test.myapplication;

import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import Utils.MyJsonUtil;
import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.button.SolidButton;
import so.bubu.ui.test.mylibrary.input.ChooseInputView;
import so.bubu.ui.test.mylibrary.input.FormView;
import so.bubu.ui.test.mylibrary.input.SwitchLayout;
import so.bubu.ui.test.mylibrary.input.TextArea;
import so.bubu.ui.test.mylibrary.input.TextEditText;
import so.bubu.ui.test.mylibrary.page.common.BaseActivity;
import so.bubu.ui.test.mylibrary.wiget.TitleView;
import so.bubu.ui.test.mylibrary.wiget.TypeTitleView;
import so.bubu.ui.test.mylibrary.input.checkBox.AboutCheckBox;
import so.bubu.ui.test.mylibrary.input.checkBox.CheckGroup;
import so.bubu.ui.test.mylibrary.input.checkBox.CheckListLayout;

/**
 * Created by zhengheng on 18/1/26.
 */
public class TestActivity extends BaseActivity {

    @Override
    public View addBaseContenetView(LinearLayout view1) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);


//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View layout = LayoutInflater.from(this).inflate(R.layout.test, null, false);
        LinearLayout view = layout.findViewById(R.id.parent);
//        View submit = findViewById(R.id.submit);

        final TextEditText text = new TextEditText(this);

        text.setAllTextColor(0xffff0000, 0xff00ff00);
//        text.isTitle(false);
//        text.setAllTextSize(20);
        text.setTitletEms(4);
        text.setTitle("姓名");
        text.setTextEditTextHint("请输入名字");
//        text.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
//        text.setBackground();
//        text.setNumberDecimalStyle();
        text.setRadius(2);
        text.setBackgroundStrokeColor(0xffff0000);
        text.setBackgroundStrokeWidth(2);

        text.setTextWatcherImpl(new TextEditText.TextWatcherImpl() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Toast.makeText(TestActivity.this, "beforeTextChanged", Toast.LENGTH_SHORT).show();
                Log.e("zhengheng", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Toast.makeText(TestActivity.this, "onTextChanged", Toast.LENGTH_SHORT).show();
                Log.e("zhengheng", "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
//                Toast.makeText(TestActivity.this, "afterTextChanged", Toast.LENGTH_SHORT).show();
                Log.e("zhengheng", "afterTextChanged");
            }
        });

        ArrayList<String> lis = new ArrayList<>();
        lis.add("aaa");
        lis.add("bbbb");
//        final ListSingleButton listbutton = findViewById(R.id.listbutton);
//        listbutton.setTitles(lis);

//        MoreCheckbox MoreCheckbox = findViewById(R.id.more_checkbox);
//        MoreCheckbox.setTitles(lis);

//        CheckGroup group = findViewById(R.id.CheckGroup);
//        OptionWrapper optionWrapper = new OptionWrapper(true);
//        optionWrapper.setOptions(lis);
//        optionWrapper.setChecked(0);
//        group.setOptionWrapper(optionWrapper);
//        group.addFooterView();

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TestActivity.this, text.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        Log.e("zhengheng", text.getText().toString());

        final ArrayList<LinkedHashMap<String, Object>> array = new ArrayList<>();
        String input = MyJsonUtil.getJson(this, "getInput");
        try {
            JSONObject jsonObject = new JSONObject(input);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if ("widgets".equalsIgnoreCase(key)) {
                    JSONArray jsonArray = jsonObject.getJSONArray(key);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            Iterator<String> keys1 = jsonObject1.keys();
                            LinkedHashMap<String, Object> weights = new LinkedHashMap<>();
                            while (keys1.hasNext()) {
                                String key1 = keys1.next();
                                Object o = jsonObject1.get(key1);
                                weights.put(key1, o);
                                //保存了所有控件, 去除控件红的object,内部可能会有不同
//                    SparseArray
                            }
                            array.add(weights);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if ("hasMore".equalsIgnoreCase(key)) {

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (LinkedHashMap<String, Object> object : array) {
            String type = (String) object.get("type");

            JSONArray objects = (JSONArray) object.get("objects");


            switch (type) {
                case "SingleCheckList":
                    CheckListLayout checkGroup = new CheckListLayout(this);
                    checkGroup.init(object, CheckGroup.TYPE_SINGLE);
                    view.addView(checkGroup);
                    break;
                case "MoreCheckList":
                    CheckListLayout checkGroup1 = new CheckListLayout(this);
                    checkGroup1.init(object, CheckGroup.TYPE_MORE);
                    view.addView(checkGroup1);
                    break;
                case "Form":
                    FormView formView = new FormView(this);
                    formView.init(object);
                    view.addView(formView);
                    break;
                case "SwitchView":
                    ArrayList<JSONObject> jsonObjects = JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : jsonObjects) {
                        SwitchLayout switchLayout = new SwitchLayout(this);
                        switchLayout.init(jsonObject);
                        view.addView(switchLayout);
                    }
                    break;
                case "TextView":
                    ArrayList<JSONObject> textViewObject = JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : textViewObject) {
                        TextEditText textView = new TextEditText(this);
                        textView.initView(jsonObject);
                        view.addView(textView);
                    }
                    break;
                case "TextArea":
                    ArrayList<JSONObject> textAreaObject = JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : textAreaObject) {
                        TextArea textArea = new TextArea(this);
                        textArea.init(jsonObject);
                        view.addView(textArea);
                    }
                    break;
                case "ChooseInputView":
                    ArrayList<JSONObject> phoneChooseObject = JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : phoneChooseObject) {
                        ChooseInputView chooseInputView = new ChooseInputView(this);
                        chooseInputView.init(jsonObject);
                        view.addView(chooseInputView);
                    }
                    break;
                case "AboutCheckBox":
                    AboutCheckBox aboutCheckBox = new AboutCheckBox(this);
                    aboutCheckBox.init(object);
                    view.addView(aboutCheckBox);
                    break;
                case "TitleView":
                    ArrayList<JSONObject> titleViewObject = JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : titleViewObject) {
                        TitleView titleview = new TitleView(this);
                        titleview.init(jsonObject);
                        view.addView(titleview);
                    }
                    break;
                case "TypeTitleView":
                    ArrayList<JSONObject> typeTitleObject = JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : typeTitleObject) {
                        TypeTitleView titleview = new TypeTitleView(this);
                        titleview.init(jsonObject);
                        view.addView(titleview);
                    }
                    break;
                case "SolidButton":
//                    ArrayList<JSONObject> SolidButtonObject = JsonArray2JsonObject(objects);
//                    for (JSONObject jsonObject : SolidButtonObject) {
//                        SolidButton solidButton = new SolidButton(this);
//                        solidButton.init(jsonObject);
//                        view.addView(solidButton);
//                    }
                    ArrayList<JSONObject> SolidButtonObject = JsonArray2JsonObject(objects);
                    for (JSONObject jsonObject : SolidButtonObject) {
                        SolidButton solidButton = new SolidButton(this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(ResourceUtil.Dp2Px(10),ResourceUtil.Dp2Px(20),ResourceUtil.Dp2Px(10),ResourceUtil.Dp2Px(10));
                        solidButton.setSubmitButton(jsonObject, array);
//                    solidButton.init(jsonObject);
                        view.addView(solidButton);
                        break;


                    }
            }

//        view.addView(text);
        }

        //    LinkedHashMap<String, Object> paramAndSelect = new LinkedHashMap<String, Object>();
        return layout;

    }

    @Override
    public void doInCreateView() {

    }

//    public void getSelectValue(ArrayList<LinkedHashMap<String, Object>> array) {
//        for (LinkedHashMap<String, Object> object : array) {
//            String type = (String) object.get("type");
//            String paramName = (String) object.get("paramName");
////            if (paramName != null && paramName.isEmpty())
//            switch (type) {
//                case "SingleCheckList":
//                    String selectedValue = (String) object.get("selectedValue");
//                    ParamAndSelect pas = new ParamAndSelect(paramName, selectedValue);
//                    paramAndSelect.add(pas);
////                    this.paramAndSelect.put(paramName, selectedValue);
//                    break;
//                case "MoreCheckList":
//                    List<CharSequence> selectValueList = (List<CharSequence>) object.get("selectedValue");
//                    ArrayList<CharSequence> strings = new ArrayList<>();
//                    strings.addAll(selectValueList);
////                    for (int i = 0; i < selectvalue.length(); i++) {
////                        try {
////                            JSONObject jsonObject = selectvalue.getJSONObject(i);
////                            String value = (String) jsonObject.get("value");
////                            strings.add(value);
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                    }
//
////                    String[] strings1 = strings.toArray(new String[strings.size()]);
//                    StringBuffer sb = new StringBuffer();
//                    for(CharSequence title: strings){
//                        sb.append(title+ " " );
//                    }
//                    ParamAndSelect paramAndSelect = new ParamAndSelect(paramName, sb.toString());
//                    this.paramAndSelect.add(paramAndSelect);
//                    break;
//            }
//        }
//    }

    public ArrayList<JSONObject> JsonArray2JsonObject(JSONArray objects) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < objects.length(); i++) {
            try {
                JSONObject jsonObject = objects.getJSONObject(i);
                jsonObjects.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }

    @Override
    protected void doBack(int keyCode, KeyEvent event) {

    }
}
