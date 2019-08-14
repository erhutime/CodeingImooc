package com.timeday.time.error;

public interface CommonError {
    int getErrCode();

    String getErrMsg();

    CommonError setErrMsg(String errMsg);
}
