package com.luoquan.reactserver.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @param
 * @author luoquan
 * 使用创建者模式构建
 */
@Data
public class RetCode<T> extends PageInfo<T> {
    private int status;
    private String msg = "";

    public RetCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static RetCode success(){
        return new RetCode(200,"SUCCESS");
    }

    public static<T> RetCode success(List<T> data) {
        RetCode retCode = new RetCode<>(data);
        retCode.setStatus(200);
        retCode.setMsg("SUCCESS");
        return retCode;
    }

    private RetCode(List<T> data) {
        super(data);
    }

}
