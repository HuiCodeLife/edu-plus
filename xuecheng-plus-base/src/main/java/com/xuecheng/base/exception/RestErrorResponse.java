package com.xuecheng.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 错误响应参数包装
 * @author: Lin
 * @since: 2023-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse implements Serializable {
    private String errMessage;
}
