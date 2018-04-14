package so.bubu.ui.test.mylibrary.input.checkBox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import Util.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;
import so.bubu.ui.test.mylibrary.wiget.NoScrollListView;
import so.bubu.ui.test.mylibrary.input.checkBox.entity.Option;
import so.bubu.ui.test.mylibrary.input.checkBox.entity.OptionWrapper;

public class CheckGroup extends NoScrollListView implements AdapterView.OnItemClickListener {

    public static final int CIRCLE = 0;//画圆
    public static final int SQUARE = 1;//画正方形

    public int mShape = CIRCLE;

    private Context mContext;

    private OptionWrapper mOptionWrapper;

    /**
     * 选项适配器
     */
    private OptionsAdapter mAdapter;

    /**
     * 选中时的颜色
     */
    private int mCheckedColor = -1;

    private int leftPadding;
    private boolean isLeftCheckBox;

    private int mDivider = ResourceUtil.Dp2Px(0.5f);

    public CheckGroup(Context context) {
        this(context, null);
    }

    public CheckGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        Drawable drawable = context.getResources().getDrawable(R.drawable.list_divider);
        setDivider(drawable);
        setDividerHeight(mDivider);
        setOnItemClickListener(this);
        mOptionWrapper = new OptionWrapper();
        mAdapter = new OptionsAdapter(mOptionWrapper.getOptions(), attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckBox);
        mShape = typedArray.getInt(R.styleable.CheckBox_shape, CIRCLE);
        isLeftCheckBox = typedArray.getBoolean(R.styleable.CheckBox_isleftcheckbox, true);
        typedArray.recycle();


//        View view = new View(context);
//        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ResourceUtil.Dp2Px(0.5f));
//        view.setLayoutParams(layoutParams);
//        view.setBackgroundColor(getResources().getColor(R.color.color_b2b2b2));
//        this.addFooterView(view);
//        this.addHeaderView(view);
    }

    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> clickUrls = new ArrayList<>();
    public static final int TYPE_SINGLE = 1;
    public static final int TYPE_MORE = 2;

    //    private Object selectedValue;
    private ArrayList<String> selectedValues = new ArrayList<>();
    private Object selectedValue;
    private ArrayList<Integer> seletItem = new ArrayList<>();
    private LinkedHashMap<String, Object> object;

    public void init(LinkedHashMap<String, Object> object, int type) {
        this.object = object;
        selectedValue = object.get("selectedValue");

        //初始化添加选中的值
        if (selectedValue instanceof String) {
            String value = (String) selectedValue;
            selectedValues.add(value);
        } else if (selectedValue instanceof JSONArray) {
            JSONArray value = (JSONArray) selectedValue;
            for (int i = 0; i < value.length(); i++) {
                try {
                    JSONObject jsonObject = value.getJSONObject(i);
                    String value1 = (String) jsonObject.get("value");
                    selectedValues.add(value1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                list.add()
            }
        }
        JSONArray objects = (JSONArray) object.get("objects");


        //初始化适配器
        OptionWrapper optionWrapper = new OptionWrapper(type == CheckGroup.TYPE_SINGLE);
        optionWrapper.setSingleChoice(type == CheckGroup.TYPE_SINGLE);
        if (type == CheckGroup.TYPE_SINGLE) {
            isLeftCheckBox = false;
        }

//        optionWrapper.setOptions(titles);
//        if (type == CheckGroup.TYPE_SINGLE) {
////            optionWrapper.setChecked(0);
//        }
//        setOptionWrapper(optionWrapper);


        //初始化titles
        for (int i = 0; i < objects.length(); i++) {
            try {
                JSONObject o = (JSONObject) objects.get(i);
                Iterator<String> keys = o.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Object o1 = o.get(next);
                    if ("title".equalsIgnoreCase(next)) {
                        titles.add((String) o1);
                        for (String title : selectedValues) {
                            if (title.equalsIgnoreCase((String) o1)) {
                                //初始化设置选中项
//                                mOptionWrapper.setChecked(i);
                                seletItem.add(i);
                            }
                        }
                    }
                    if ("url".equalsIgnoreCase(next)) {
                        clickUrls.add((String) o1);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        optionWrapper.setOptions(titles);
        optionWrapper.setChecked(seletItem);
        setOptionWrapper(optionWrapper);
        mAdapter.notifyDataSetChanged();

    }

    /**
     * 传入一个选项集合的封装类后,就可正常显示CheckGroup
     *
     * @param wrapper 选项集合的封装类
     */
    public void setOptionWrapper(OptionWrapper wrapper) {
        if (wrapper == null) return;
        mOptionWrapper.set(wrapper);
        setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!mOptionWrapper.isEnabled()) return;
        if (mOptionWrapper.isSingleChoice()) {
            handleSingleChoice(parent, view, position);
        } else {
            handleMultiChoice((CheckBox) view, position);
        }
        if (mOnChangeListener != null)
            mOnChangeListener.onChange(parent, view, position);
    }

    private void handleMultiChoice(CheckBox checkBox, int position) {
        checkBox.toggle(false);
        mOptionWrapper.getOptionAt(position).toggle();

        List<CharSequence> checkedText = mOptionWrapper.getCheckedText();
        object.put("selectedValue", checkedText);
    }

    /**
     * 处理单选时的情况,可能有人会问：<p>
     * 为什么要那么麻烦,为什么不直接循环实体类集合,并将当前单击的item设为true,其他一概设为false,然后刷新适配器即可
     * <p>原因:重新刷新适配器的话，所有的item都需要重新绘制一遍，这样就会加大cup的工作量
     * <p>而通过以下方法的话，只要刷新两个item即可
     */
    private void handleSingleChoice(AdapterView<?> parent, View view, int position) {
        CheckBox checkBox;
        boolean isSet = false;//用于判断有没有将当前单击的item设为true
        int chileCount = parent.getChildCount();//得到item条数

        for (int i = 0; i < chileCount; i++) {
            View v = parent.getChildAt(i);
            checkBox = (CheckBox) v;
            boolean isCheck = checkBox.isChecked();
            boolean isRightCheck = checkBox.isRightChecked();
            if (i == position && !isCheck) {
                //如果是当前单击的item且没有被选中,则设为选中
                checkBox.setChecked(true);
                checkBox.setRightCheck(true);
                mOptionWrapper.getOptionAt(i).setCheck(true);
                isSet = true;
            } else if (i != position && isCheck) {
                //如果不是当前单击的item且被选中,则设为不选中,即将上次选中的置为false,并结束循环
                checkBox.setChecked(false, true);
                checkBox.setRightCheck(false);
                mOptionWrapper.getOptionAt(i).setCheck(false);
                break;
            }
        }
        if (!isSet) {//如果没有将当前单击的item设为true,则直接通过View找到并设为true
            checkBox = (CheckBox) view;
            checkBox.setChecked(true);
            checkBox.setRightCheck(true);
            mOptionWrapper.getOptionAt(position).setCheck(true);
        }

        List<CharSequence> checkedText = mOptionWrapper.getCheckedText();

        object.put("selectedValue", checkedText.get(0).toString());
    }

    /**
     * 设置选中时CheckBox的颜色
     *
     * @param checkedColor 选中时的颜色
     */
    public void setCheckedColor(int checkedColor) {
        mCheckedColor = checkedColor;
    }

    /**
     * @return 选中的item集合
     */
    public List<Option> getChecked() {
        return mOptionWrapper.getChecked();
    }


    /**
     * @return 选中item的文本集合
     */
    public List<CharSequence> getCheckedText() {
        return mOptionWrapper.getCheckedText();
    }

    /**
     * @return 选中item的position 集合
     */
    public List<Integer> getCheckedIndex() {
        return mOptionWrapper.getCheckedIndex();
    }

    public void setShape(int shape) {
        mShape = shape;
    }

    public void isLeftCheckBox(boolean isLeftCheckBox) {
        this.isLeftCheckBox = isLeftCheckBox;
    }

    public void setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
    }

    class OptionsAdapter extends BaseAdapter {

        List<Option> mOptions;

        AttributeSet attrs;

        public OptionsAdapter(List<Option> options, AttributeSet attrs) {
            mOptions = options;
            this.attrs = attrs;
        }

        @Override
        public int getCount() {
            return mOptions.size();
        }

        @Override
        public Object getItem(int position) {
            return mOptions.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.checkbox, parent, false);
                convertView.setPadding(leftPadding, convertView.getPaddingTop(), convertView.getPaddingRight(), convertView.getPaddingBottom());
                holder = new ViewHolder(convertView);
                holder.checkBox.setShape(mShape);
                holder.checkBox.isLeftCheckBox(isLeftCheckBox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Option option = mOptions.get(position);
            if (mCheckedColor != -1)
                holder.checkBox.setCheckedColor(mCheckedColor);
            holder.checkBox.setChecked(option.isCheck());
            holder.checkBox.setRightCheck(option.isCheck());
            holder.checkBox.setText(option.getTitle());
            holder.checkBox.setShape(mShape);
            return convertView;
        }
    }

    class ViewHolder {
        CheckBox checkBox;

        public ViewHolder(View view) {
            this.checkBox = (CheckBox) view;
        }
    }


    /**
     * 外界调用的item监听器
     */
    private OnChangeListener mOnChangeListener;

    public interface OnChangeListener {
        void onChange(AdapterView<?> parent, View view, int position);
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        mOnChangeListener = onChangeListener;
    }

}
