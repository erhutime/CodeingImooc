package com.timeday.time.error;

public enum EmBusinessError implements CommonError {
    USER_NOT_EXIST(1001,"用户不存在"),
    USER_LOGIN_FAIL(1002,"手机号或密码错误"),
    USER_NOT_LOGIN(1003,"用户还未登录，请登录"),


    STOCK_NOT_ENOUGH(3001,"库存不足"),


    UNKNOWN_ERROR(9001,"未知错误"),
    PARAMETER_VALIDATION_ERROR(8001,"参数错误");


    private EmBusinessError(int errCode, String  errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
