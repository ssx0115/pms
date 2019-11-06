package com.offcn.sys.bean;

public class RoleSources {
    private Integer rid;
    private Integer sid;

    public RoleSources() {
        super();
    }

    public RoleSources(Integer rid, Integer sid) {
        this.rid = rid;
        this.sid = sid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "RoleSources{" +
                "rid=" + rid +
                ", sid=" + sid +
                '}';
    }
}
