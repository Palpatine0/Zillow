package com.example.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BaseResult {
    // status code, 200(success) or 500(failed)
    private int status;
    // result result
    private Object results;
    private String msg;
    // return data
    private Object data;
    // pagination adopted, this is to check whether there's more that current page is not showing
    private boolean hasMore;
    // clock down for the reservation
    private long time;
    private int cnt;


    public static BaseResult ok(){
        BaseResult BaseResult = new BaseResult();
        BaseResult.setStatus(200);
        return BaseResult;
    }

    public static BaseResult ok(Object data){
        BaseResult result = new BaseResult();
        result.setStatus(200);
        result.setData(data);
        return result;
    }

    public static BaseResult error(){
        BaseResult result = new BaseResult();
        result.setStatus(500);
        return result;
    }

    public static BaseResult error(String msg){
        BaseResult result = new BaseResult();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }
}
