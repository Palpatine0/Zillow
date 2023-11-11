package com.example.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ZillowResult {
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


    public static ZillowResult ok(){
        ZillowResult ZillowResult = new ZillowResult();
        ZillowResult.setStatus(200);
        return ZillowResult;
    }

    public static ZillowResult ok(Object data){
        ZillowResult result = new ZillowResult();
        result.setStatus(200);
        result.setData(data);
        return result;
    }

    public static ZillowResult error(){
        ZillowResult result = new ZillowResult();
        result.setStatus(500);
        return result;
    }

    public static ZillowResult error(String msg){
        ZillowResult result = new ZillowResult();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }
}
