package com.ezappx.builder.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {
    public static final String SUCCESS = "操作成功";
    public static final String FAIL = "操作失败";
    public static final String DB_FAIL = "数据库操作失败";

    private HttpStatus status;
    private String msg;
    private T data;
}
