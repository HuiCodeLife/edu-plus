package com.xuecheng.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学成在线自定义异常类
 * @author: Lin
 * @since: 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XueChengPlusException extends RuntimeException{
    private String errMessage;

    public static void cast(CommonError commonError){
        throw new XueChengPlusException(commonError.getErrMessage());
    }
    public static void cast(String errMessage){
        throw new XueChengPlusException(errMessage);
    }

}
