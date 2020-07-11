package com.yan.utils;

import com.yan.utils.commom.Constant;
import lombok.Data;
import sun.security.pkcs11.wrapper.Constants;

@Data
public class Result<T> {
    private T data;
    private Boolean success;
    private String code;
    private String msg;
    private int page;
    private int pageSize;
    private int totalSize;

    public Result(){

    }

   public Result(T data,int page,int pageSize,int totalSize){
      this.data = data;
      this.success = Constant.SUCCESS_STATUS;
      this.code = Constant.SUCCESS_CODE;
      this.msg = Constant.SUCCESS_MSG;
      this.page = page;
      this.pageSize = pageSize;
      this.totalSize = totalSize;
   }
}
