package cn.cy.model;

/**
 * Created by 30721 on 2019/7/10.
 */
public class ResultBean<T> {

    private int state;
    private String msg;
    private T resultBean;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResultBean() {
        return resultBean;
    }

    public void setResultBean(T resultBean) {
        this.resultBean = resultBean;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", resultBean=" + resultBean +
                '}';
    }
}
