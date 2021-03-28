package com.bjtu.airport.contant;

import lombok.ToString;

/**
 * @author: LiuYi
 * @date: 2019/4/18 22:16
 */

@ToString
public enum ResultCode {

    SUCCESS(true, 10000, "操作成功！"),
    FAIL(false, 19999, "操作失败！"),
    UNAUTHENTICATED(false, 10001, "此操作需要登陆系统！"),
    UNAUTHORISE(false, 10002, "权限不足，无权操作！"),
    NULL(false, 10003, "空值异常！"),
    TIMEOUT(false, 10004, "服务器连接超时！"),

    USERNAME_PASSWORD_ERROR(false, 10005, "用户名或密码错误"),
    TOKEN_EXPIRED_ERROR(false, 10006, "登录凭证过期，请重新登录"),
    MISS_REQUEST_PARAMETER(false, 10007, "缺少必要请求参数"),
    MISS_PATH_VARIABLE(false, 10008, "缺少必要路径变量"),
    BAD_PATAMETER(false, 10009, "数据校验失败"),

    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！");


//    CMS_ADDPAGE_EXISTSNAME(false, 24001, "页面名称已存在！"),
//    CMS_GENERATEHTML_DATAURLISNULL(false, 24002, "从页面信息中找不到获取数据的url！"),
//    CMS_GENERATEHTML_DATAISNULL(false, 24003, "根据页面的数据url获取不到数据！"),
//    CMS_GENERATEHTML_TEMPLATEISNULL(false, 24004, "页面模板为空！"),
//    CMS_GENERATEHTML_HTMLISNULL(false, 24005, "生成的静态html为空！"),
//    CMS_GENERATEHTML_SAVEHTMLERROR(false, 24005, "保存静态html出错！"),
//    CMS_COURSE_PERVIEWISNULL(false, 24007, "预览页面为空！"),
//    CMS_TEMPLATEFILE_ERROR(false, 24008, "模板文件需要.ftl后缀！"),
//    CMS_TEMPLATEFILE_NULL(false, 24009, "模板文件为空！"),
//    CMS_TEMPLATEFILE_EXCEPTION(false, 24010, "解析模板文件异常！"),
//    CMS_TEMPLATEFILE_FAIL(false, 24011, "模板文件存储失败！"),
//    CMS_TEMPLATEFILE_DELETE_ERROR(false, 24012, "模板文件删除失败！"),
//    CMS_Config_NOTEXISTS(false, 24013, "不存在该数据模型！"),
//    CMS_PAGE_NULL(false, 24014, "不存在该页面数据！"),
//    CMS_GENERATEHTML_CONTENT_FAIL(false, 24014, "获取页面模板失败！");


    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

