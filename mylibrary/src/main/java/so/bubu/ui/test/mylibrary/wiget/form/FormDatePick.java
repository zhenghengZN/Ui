package so.bubu.ui.test.mylibrary.wiget.form;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import so.bubu.ui.test.mylibrary.Dialog.PickerDialog;
import so.bubu.ui.test.mylibrary.R;

import so.bubu.ui.test.mylibrary.wiget.pickTimeView.PickTimeView;

/**
 * Created by zhengheng on 18/2/1.
 */
public class FormDatePick extends LinearLayout {
    public FormDatePick(Context context) {
        this(context, null);
    }

    private TextView title, pick;
    private Context ctx;
    private PickerDialog timePick;

    public FormDatePick(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        View view = LayoutInflater.from(context).inflate(R.layout.formdatepick, this, true);
        title = (TextView) view.findViewById(R.id.title);
        pick = (TextView) view.findViewById(R.id.pick);
        timePick = new PickerDialog((Activity) context);

        pick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ((Activity)ctx).getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) ((Activity)ctx).getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                timePick.show();
            }
        });

        timePick.setOnSucessClickListener(new PickerDialog.OnSucessClickListener() {
            @Override
            public void sucessClick(String date, View v) {
                if (date != null && !date.isEmpty()) {
                    pick.setText(date);
                    try {
                        object.put("value",date);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private JSONObject object;
    public void init(JSONObject object) {
        this.object = object;
        try {
            String title = (String) object.get("title");
            setTitle(title);

            int pickType = (int) object.get("pickType");
            setType(pickType);

            object.put("value", pick.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String s) {
        title.setText(s);
    }

    public void setType(int type) {
        timePick.setType(type);
        Calendar calendar = Calendar.getInstance();
        if (type == PickTimeView.TYPE_PICK_DATE) {
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String months, days;
            if (day < 10) {
                days = "0" + day;
            } else {
                days = day + "";
            }

            if (month < 10) {
                months = "0" + month;
            } else {
                months = month + "";
            }
            pick.setText(calendar.get(Calendar.YEAR) + "-" + months + "-" + days);
        } else if (type == PickTimeView.TYPE_PICK_TIME) {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            String hours, mins;
            if (hour < 10) {
                hours = "0" + hour;
            } else {
                hours = hour + "";
            }

            if (min < 10) {
                mins = "0" + min;
            } else {
                mins = min + "";
            }
            pick.setText(hours + ":" + mins);

        }
    }
}
