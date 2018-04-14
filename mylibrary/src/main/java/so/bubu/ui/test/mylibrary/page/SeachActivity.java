package so.bubu.ui.test.mylibrary.page;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.LinkedHashSet;
import java.util.List;

import Util.HistorySqlLite;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.page.common.BaseCompatActivity;
import so.bubu.ui.test.mylibrary.wiget.ClearEditText;
import so.bubu.ui.test.mylibrary.wiget.NoScrollListView;
import so.bubu.ui.test.mylibrary.wiget.flowlayout.FlowLayout;
import so.bubu.ui.test.mylibrary.wiget.flowlayout.TagAdapter;
import so.bubu.ui.test.mylibrary.wiget.flowlayout.TagFlowLayout;
import so.bubu.ui.test.mylibrary.wiget.flowlayout.TagView;

import android.widget.Toast;

/**
 * Created by zhengheng on 18/1/19.
 */
public abstract class SeachActivity extends BaseCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private TagFlowLayout hotFlowLayout, historyFlowLayout;
    private ClearEditText mClearEditText;
    private TextView searchBtn;
    private View historyLabel, searchListLayout;
    private LinearLayout historyLayout;
    private TagFlowLayout.OnTagClickListener onTagClickListener;
    private NoScrollListView list;
    private FrameLayout searchContent;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        View hotView = findViewById(R.id.hot_label);
        searchContent = (FrameLayout) findViewById(R.id.search_content);
        mClearEditText = (ClearEditText) findViewById(R.id.et_content_search);
        mClearEditText.setOnEditorActionListener(this);
        searchListLayout = findViewById(R.id.search_list_layout);
        View searchList = findViewById(R.id.search_list);
        list = (NoScrollListView) searchList.findViewById(R.id.nsll_select_history);
        mClearEditText.addTextWatcherImpl(new ClearEditText.TextWatcherImpl() {
            @Override
            public void afterTextChanged(Editable s) {
                if (ClearEditTextWatcherImpl != null) {
                    ClearEditTextWatcherImpl.afterTextChanged(s);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (ClearEditTextWatcherImpl != null) {
                    ClearEditTextWatcherImpl.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().length() > 0 && !s.toString().isEmpty()) {
                    searchListLayout.setVisibility(View.VISIBLE);
                    if (ClearEditTextWatcherImpl != null) {
                        ClearEditTextWatcherImpl.onTextChangedListVisible(s, start, count, after, list);
                    }
                } else {
                    searchListLayout.setVisibility(View.GONE);
                    if (ClearEditTextWatcherImpl != null) {
                        ClearEditTextWatcherImpl.onTextChangedListGone(s, start, count, after, list);
                    }
                }
            }
        });

        searchBtn = (TextView) findViewById(R.id.iv_search_poi);
        searchBtn.setOnClickListener(this);

        historyLayout = (LinearLayout) findViewById(R.id.history_layout);

        TextView hotTitle = (TextView) hotView.findViewById(R.id.search_label_title);
        hotTitle.setText(getResources().getString(R.string.search_label_text_hot));
        hotView.findViewById(R.id.img_layout).setVisibility(View.GONE);

        onTagClickListener = new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                TagView tag = (TagView) view;
                TextView textView = (TextView) tag.getTagView();
                String s = textView.getText().toString();
                mClearEditText.setText(s);
                mClearEditText.setSelection(s.length());
                if (OnTagItemClickListener != null) {
                    OnTagItemClickListener.onTagItemClick(view, position, parent);
                }

                return false;
            }
        };

//        initData();
        hotList.addAll(setHotStrings());
        hotFlowLayout = (TagFlowLayout) hotView.findViewById(R.id.id_flowlayout);
        setHotData();

        historyLabel = findViewById(R.id.history_label);
        historyLabel.findViewById(R.id.img_layout).setOnClickListener(this);

        historyFlowLayout = (TagFlowLayout) historyLabel.findViewById(R.id.id_flowlayout);
        getHistoryData();
    }

    private List<String> historyList = new ArrayList<>();
    private TagAdapter historyAdapter, hotAdapter;

    public void getHistoryData() {

        ArrayList<String> query = HistorySqlLite.getInstance(this).query();
        historyList.addAll(query);
        Collections.reverse(historyList);

        if (historyList.size() > 0) {
            historyLayout.setVisibility(View.VISIBLE);
        }
        historyAdapter = new TagAdapter(historyList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView view = (TextView) LayoutInflater.from(SeachActivity.this).inflate(R.layout.flowlayout_item, hotFlowLayout, false);
                view.setText((String) o);
                return view;
            }
        };

        historyFlowLayout.setOnTagClickListener(onTagClickListener);
        historyFlowLayout.setAdapter(historyAdapter);
    }

    public void setHotData() {
        hotAdapter = new TagAdapter(hotList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView view = (TextView) LayoutInflater.from(SeachActivity.this).inflate(R.layout.flowlayout_item, hotFlowLayout, false);
                view.setText((String) o);
                if (position == 0) {
                    view.setBackground(getResources().getDrawable(R.drawable.checked_bg));
                    view.setTextColor(Color.RED);
                }
                return view;
            }
        };

        hotFlowLayout.setOnTagClickListener(onTagClickListener);
        hotFlowLayout.setAdapter(hotAdapter);
    }

    private List<String> hotList = new ArrayList<>();

//    public void initData() {
//        String hotSearch = MyJsonUtil.getJson(this, "HotSearch");
//        try {
//            JSONObject jsonObject = new JSONObject(hotSearch);
//            JSONArray array = jsonObject.getJSONArray("hotSearch");
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject jsonObject1 = array.getJSONObject(i);
//                String hotkey = jsonObject1.getString("hotkey");
//                hotList.add(hotkey);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


    public abstract List<String> setHotStrings();

    @Override
    protected void doBack(int keyCode, KeyEvent event) {
        finish();
        this.overridePendingTransition(R.anim.pull_alpha_in, R.anim.pull_alpha_out);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_search_poi) {
            String s = mClearEditText.getText().toString();
            if (!s.isEmpty() && s != null) {
                isAddHistory(s);
                hideInputView();
            }
            if(SearchButtonOnclick != null) {
                SearchButtonOnclick.searchOnclick(v);
            }
            return;
        }

        if (i == R.id.img_layout) {
            historyList.clear();
            //清除数据库
            HistorySqlLite.getInstance(this).delete();
            historyAdapter.notifyDataChanged();
            historyLayout.setVisibility(View.GONE);
            return;
        }
    }

    public void isAddHistory(String key) {
        if (historyList.size() == 0) {
            historyList.add(key);
            HistorySqlLite.getInstance(this).insert(key);
            historyAdapter.notifyDataChanged();
        } else {

//            ArrayList<String> list = new ArrayList<>();
            boolean isAddHistory = true;
            for (int i = 0; i < historyList.size(); i++) {
                String s = historyList.get(i);
                if (key.equals(s)) {
                    //若已经存在,看是否在第一个,否则添加到第一个
                    isAddHistory = false;
                    if (i != 0) {
                        HistorySqlLite.getInstance(this).deleteSingle(key);
                        HistorySqlLite.getInstance(this).insert(key);
                        ArrayList<String> query = HistorySqlLite.getInstance(this).query();
                        historyList.clear();
                        historyList.addAll(query);
                        Collections.reverse(historyList);
                        historyAdapter.notifyDataChanged();
                    }
                }
            }
            if (isAddHistory) {
                historyList.add(0, key);
                HistorySqlLite.getInstance(this).insert(key);
                historyAdapter.notifyDataChanged();
            }

        }
        if (historyList.size() > 0 && historyList != null) {
            historyLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

            String keytag = mClearEditText.getText().toString().trim();

            if (TextUtils.isEmpty(keytag)) {
                Toast.makeText(SeachActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();

                return false;
            }
            // 搜索功能主体
            isAddHistory(keytag);
            hideInputView();
            return true;
        }
        return false;
    }

    public boolean hideInputView() {
        View inputView = getWindow().peekDecorView();
        if (inputView != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(inputView.getWindowToken(), 0);
            return true;
        }
        return false;
    }

    private OnTagItemClickListener OnTagItemClickListener;
    private ClearEditTextWatcherImpl ClearEditTextWatcherImpl;
    private SearchButtonOnclick SearchButtonOnclick;

    public interface SearchButtonOnclick {
        void searchOnclick(View v);
    }

    public interface OnTagItemClickListener {
        void onTagItemClick(View view, int position, FlowLayout parent);
    }

    public void setOnSearchButtonOnclick(SearchButtonOnclick SearchButtonOnclick) {
        this.SearchButtonOnclick = SearchButtonOnclick;
    }

    public void setOnTagItemClickListener(OnTagItemClickListener OnTagItemClickListener) {
        this.OnTagItemClickListener = OnTagItemClickListener;
    }

    public ClearEditText getClearEditText() {
        return mClearEditText;
    }

    public interface ClearEditTextWatcherImpl {
        void afterTextChanged(Editable s);

        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChangedListVisible(CharSequence s, int start, int count, int after, NoScrollListView list);

        void onTextChangedListGone(CharSequence s, int start, int count, int after, NoScrollListView list);

    }

    public void addClearEditTextWatcherImpl(ClearEditTextWatcherImpl ClearEditTextWatcherImpl) {
        this.ClearEditTextWatcherImpl = ClearEditTextWatcherImpl;
    }

    public FrameLayout getSearchContent() {
        return searchContent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
