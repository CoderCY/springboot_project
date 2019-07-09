package cn.cy.model;

/**
 * Created by 30721 on 2019/7/6.
 */
public class SysUser {

    private int id;

    private String name;

    private int age;

    private String psw;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", psw='" + psw + '\'' +
                '}';
    }


}
