package com.htxx.controller;

import com.github.pagehelper.PageInfo;
import com.htxx.common.ResponseMessage;

import java.util.List;

public class BaseController {

    protected ResponseMessage responseMessage(List pageList) {
        return pageList.size() >= 0 ? ResponseMessage.successPage(pageList, (new PageInfo(pageList)).getTotal()) :ResponseMessage.error();
    }

    protected ResponseMessage responseMessage(Object data) {
        return data == null ? ResponseMessage.error() :ResponseMessage.success(data);
    }
    protected ResponseMessage responseMessage(int code,Object data,String message) {
        return new ResponseMessage(code, data,message,0);
    }

    protected ResponseMessage responseMessage(Boolean success,String message) {
        return success ? ResponseMessage.success(message):ResponseMessage.error(message) ;
    }
}
