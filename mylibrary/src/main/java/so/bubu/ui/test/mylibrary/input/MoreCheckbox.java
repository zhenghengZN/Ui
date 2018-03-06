package so.bubu.ui.test.mylibrary.input;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Utils.ResourceUtil;
import so.bubu.ui.test.mylibrary.R;

/**
 * Created by zhengheng on 18/1/30.
 */
public class MoreCheckbox extends LinearLayout {
    public MoreCheckbox(Context context) {
        super(context);
    }

    private ArrayList<String> titles = new ArrayList<>();
    private View more;
    private TextView footerTitle;
    private Context ctx;
    private SingleChoiceAdapter singleChoiceAdapter;

    public MoreCheckbox(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(VERTICAL);
        this.ctx = context;
        this.addView(Stroke());
        more = LayoutInflater.from(context).inflate(R.layout.more, null, false);
        footerTitle = (TextView) more.findViewById(R.id.more);
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.FooterClick(v);
                }
            }
        });

        View inflate = LayoutInflater.from(context).inflate(R.layout.listsinglebutton, null, false);
        ListView listView = (ListView) inflate.findViewById(R.id.list);
        singleChoiceAdapter = new SingleChoiceAdapter(context, titles);
//        singleChoiceAdapter.check(0);
        listView.setAdapter(singleChoiceAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                singleChoiceAdapter.check(position);
                SingleChoiceAdapter.ViewHolder viewHolder = (SingleChoiceAdapter.ViewHolder) view.getTag();
                viewHolder.radioButton.toggle();
                singleChoiceAdapter.addCheckPosition(position, viewHolder.radioButton.isChecked());
                if (listener != null) {
                    listener.CheckItem(parent, view, position, singleChoiceAdapter.getItem(position));
                }
            }
        });
        listView.addFooterView(more);
        this.addView(inflate);
        this.addView(Stroke());
    }

    public View Stroke() {
        View view = new View(ctx);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ResourceUtil.Dp2Px(0.5f));
        view.setLayoutParams(lp);
        view.setBackgroundColor(getResources().getColor(R.color.color_e2e2e2));
        return view;
    }

    private OnCheckItemListener listener;

    public void setOnCheckItemListener(OnCheckItemListener listener) {
        this.listener = listener;
    }

    public interface OnCheckItemListener {
        void CheckItem(AdapterView<?> parent, View view, int position, String title);

        void FooterClick(View v);
    }

    public void setFooterTitle(String s) {
        footerTitle.setText(s);
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles.addAll(titles);
        singleChoiceAdapter.notifyDataSetChanged();
    }

    private class SingleChoiceAdapter extends BaseAdapter {
        private final LayoutInflater layoutInflater;
        private List<String> data;

        private HashMap<Integer, Boolean> isCheckMap = new HashMap<>();

        public SingleChoiceAdapter(Context context, List<String> data) {
            layoutInflater = LayoutInflater.from(context);
            this.data = data;
            initMap();
            // 默认为-1，没有选择任何item
//            currentCheckedItemPosition = AbsListView.INVALID_POSITION; // -1
        }


        public void initMap() {
            for (int i = 0; i < data.size(); i++) {
                isCheckMap.put(i, false);
            }
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public String getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.singlecheckbox, parent, false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.title);
                convertView.findViewById(R.id.more_checkbox).setVisibility(VISIBLE);
                viewHolder.radioButton = (CheckBox) convertView.findViewById(R.id.more_checkbox);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

//            isCheckMap.put(position, false);
            if (isCheckMap.get(position) == null) {
                isCheckMap.put(position, false);
            }
            viewHolder.radioButton.setChecked(isCheckMap.get(position));

            viewHolder.textView.setText(getItem(position));

            return convertView;
        }


        public void addCheckPosition(int i, boolean ischeck) {
            isCheckMap.put(i, ischeck);
            notifyDataSetChanged();
        }

        class ViewHolder {
            TextView textView;
            CheckBox radioButton;
        }
    }
}
