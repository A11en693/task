package com.yan.utils.taskenum;

import com.yan.utils.taskenum.base.BaseEnum;

public enum YesOrNo implements BaseEnum {

    NO_CODE("0","否"),
    YES_CODE("1","是");

    private String code;
    private String desc;

    YesOrNo(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
