package com.mazouri.ketangpai.common.result;


/**
 * @author mazouri
 */
public interface ResultCode {
    /**
     * 操作成功返回码
     */
    Integer SUCCESS = 20000;
    /**
     * 操作失败返回码
     */
    Integer ERROR = 20001;

    Integer NO_AUTHORITY = 403;
}
