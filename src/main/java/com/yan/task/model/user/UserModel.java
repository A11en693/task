package com.yan.task.model.user;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yan.utils.taskenum.YesOrNo;
import com.yan.utils.taskenum.base.EnumValue;
import com.yan.utils.taskenum.base.MethodEnumJasonFormatter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonSerialize(using = MethodEnumJasonFormatter.class)
@ColumnWidth(20) //设置列长度 20
@HeadFontStyle(fontHeightInPoints = 10) //头部字体大小设置成20 extends BaseRowModel
public class UserModel{

    @ExcelIgnore
    private String id;

    @ExcelIgnore
    private String loginName;

    @ExcelProperty(index = 0,value = "用户名称")
    private String userName;

    @ExcelIgnore
    private String passWord;

    @ExcelProperty(index = 1,value = "用户手机号")
    private String mobilePhone;

    @ExcelProperty(index = 2,value = "用户固定电话")
    private String linePhone;

    @ExcelIgnore
    private String deptId;

    @ExcelProperty(index = 3,value = "是否全权限")
    @EnumValue(enumClass = YesOrNo.class)
    private String active;

    @ExcelProperty(index = 4,value = "创建日期")
    private String createDate;

    @ExcelProperty(index = 5,value = "创建时间")
    private String createTime;

}
