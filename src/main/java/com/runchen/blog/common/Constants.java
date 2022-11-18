package com.runchen.blog.common;

public interface Constants {

    //session key
    String IMAGE_CODE = "IMAGE_CODE";
    String LOGIN_USERS = "LOGIN_USERS";

    //上传文件的默认url前缀，根据部署设置自行修改
    String FILE_UPLOAD_DIC = "D:/upload/";

    //常用
    String MASK = "******";
    String SUCCESS = "success";
    String ERROR = "error";

    //中英文和数字的字符串正则表达式
    String KEYWORD_REGEX = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
    //邮箱正则表达式
    String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    //网址正则表达式
    String URL_REGEX = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
}
