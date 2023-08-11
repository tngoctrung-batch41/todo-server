package com.exercise.todo.response;

import com.exercise.todo.common.WrapResponseStatus;
import lombok.Data;

@Data
public class WrapResponse <T> {
    private int code;
    private T data;
    private String errorMessage;

    public static <T> WrapResponse ok(T data){
        WrapResponse res = new WrapResponse();
        res.setCode(WrapResponseStatus.SUCCESS);
        res.setData(data);
        res.setErrorMessage("");
        return res;
    }

    public static <T> WrapResponse error(int code, String errorMessage){
        WrapResponse res = new WrapResponse();
        res.setCode(code);
        res.setData(null);
        res.setErrorMessage(errorMessage);
        return res;
    }

}
