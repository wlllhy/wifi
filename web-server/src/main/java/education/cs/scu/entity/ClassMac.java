package education.cs.scu.entity;

import java.io.Serializable;

public class ClassMac implements Serializable{

    private static final long serialVersionUID = -8211531756489532620L;
    private Integer stu_id;
    private String name;
    private String mac;
    private String classid;
    public ClassMac(){
    }
    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }
}
