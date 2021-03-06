package com.wxq.commonlibrary.constant;

import android.os.Environment;

/**
 * @author nat.xue
 * @date 2017/10/21
 * @description 全局常量
 */

public class GlobalContent {

    public static final int P720 = 720;
    public static final int P1080 = 1080;
    public static final int P1500 = 1500;

    /**
     * 是否登录,家长版需要，暂时设置成true，默认是false
     */
    public static boolean isLogin = true;
    public static final String ENCODING = "UTF-8";
    public static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DevelopTools/";

    public static final String AT_SPECIAL_WORD = " ";
    public static final String AT_WORD = "@";

    public static final String  APKFILENAME = "common.apk";

    /**
     * 语音存放路径
     */
    public static String VOICEPATH = filePath + "audio/";

    /**
     * 视频存放路径
     */
    public static String VIDEOPATH = filePath + "video/";

    /**
     * 课件保存路径
     */
    public static String COURSEWARE = filePath + "courseware/";


    /**
     * 图片保存路径
     */
    public static String SAVEPICTURE = filePath + "savepictures/";

    /**
     * 选图片缓存和glide缓存的图片路径
     */
    public static String imgPath = filePath + "savepictures/";

    /**
     * 文件的存放地址
     */
    public static String SAVEFILEPATH = filePath + "savefile/";

    /**
     * log路径
     */
    public static String logPath = filePath + "log/";

    /**
     * baseurl
     */
//    public static final String BASE_URL = "http://www.wanandroid.com/tools/mockapi/2547/";
    public static final String BASE_URL = "http://39.98.211.162:28081/";


    public static final int REFRESH = 0;
    public static final int LOADMORE = 1;

    public static final String ACCESSTOKEN = "AccessToken";


  // 微信支付相关 包子

    public static final String WEIXIN_APPID = "wx6fc0584cba2079c5";

    // 微信商户id
    public static final String WEIXIN_MCHID ="" ;


//  、、七牛云图片地址默认根路径
    public static final String SEVENCLOUDDIR ="http://test.image.lwbudoo.cn/" ;


}
