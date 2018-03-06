package so.bubu.ui.test.myapplication;

/**
 * Created by zhengheng on 18/2/2.
 */
public class ParamAndSelect {

    private String param;
    private Object selectValue;

    public ParamAndSelect(String param, Object selectValue) {
        this.param = param;
        this.selectValue = selectValue;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Object getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(Object selectValue) {
        this.selectValue = selectValue;
    }

    @Override
    public String toString() {
        return "{" +
                "param='" + param + '\'' +
                ", selectValue='" + selectValue + '\'' +
                '}';
    }
}
