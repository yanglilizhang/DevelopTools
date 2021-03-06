package com.wxq.commonlibrary.pay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.util.Xml;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wxq.commonlibrary.constant.GlobalContent;
import com.wxq.commonlibrary.util.ToastUtils;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by zh on 2017/10/9.
 * describe: 微信支付
 */

public class WeiXinPay {
    private static WeiXinPay instance = null;
    private String appId = "", partnerId = "";

    private WeiXinPay() {
            appId = GlobalContent.WEIXIN_APPID;
        partnerId = GlobalContent.WEIXIN_MCHID;
    }

    public static WeiXinPay getInstance() {
        if (instance == null) {
            instance = new WeiXinPay();
        }
        return instance;
    }

    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    public Map<String, String> decodeXml(String content) {
        try {
            Map<String, String> xml = new HashMap<>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:

                        if (!"xml".equals(nodeName)) {
                            //实例化student对象
                            xml.put(nodeName, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                event = parser.next();
            }
            return xml;
        } catch (Exception e) {
        }
        return null;
    }


    private void genPayReq(PayReq req, Map<String, String> result, String apiKey) {
        req.appId = appId;
        req.partnerId = result.get("mch_id");
        req.prepayId = result.get("prepay_id");
        req.packageValue = "Sign=WXPay";
        req.nonceStr = genNonceStr();
        req.timeStamp = String.valueOf(genTimeStamp());
        StringBuilder sb = new StringBuilder(100);
        sb.append("appid").append('=').append(req.appId).append('&');
        sb.append("noncestr").append('=').append(req.nonceStr).append('&');
        sb.append("package").append('=').append(req.packageValue).append('&');
        sb.append("partnerid").append('=').append(req.partnerId).append('&');
        sb.append("prepayid").append('=').append(req.prepayId).append('&');
        sb.append("timestamp").append('=').append(req.timeStamp).append('&');
        sb.append("key=").append(apiKey);
        Log.e("MD5.getMessageDigest",sb.toString());
        req.sign =MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();;
    }

    public void pay(Context mContext, String result, String apiKey,OnPayListener listener) {
        BroadcastReceiver receiver = new WXPayResultReceiver(listener);
        IntentFilter filter = new IntentFilter("com.Pay");
        filter.addAction("com.Pay.error");
        filter.addAction("com.Pay.cancle");
        mContext.getApplicationContext().registerReceiver(receiver, filter);
        Log.e("  getSign()", getSign(mContext));
         IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, GlobalContent.WEIXIN_APPID);
         PayReq req = new PayReq();
        Map<String, String> xml = decodeXml(result);
        genPayReq(req, xml, apiKey);
        msgApi.registerApp(appId);
        if (!msgApi.isWXAppInstalled()) {
            ToastUtils.showShort("请安装微信客户端");
        } else {
            msgApi.sendReq(req);
        }
    }


    public static String getSign(Context mContext) {
        PackageManager pm = mContext.getPackageManager();
        List<PackageInfo> apps = pm
                .getInstalledPackages(PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> iter = apps.iterator();
        while (iter.hasNext()) {
            PackageInfo packageinfo = iter.next();
            String packageName = packageinfo.packageName;
            if (packageName.equals(mContext
                    .getPackageName())) {
                return packageinfo.signatures[0].toCharsString();
            }
        }
        return "";
    }

    class WXPayResultReceiver extends BroadcastReceiver {

        private OnPayListener onPayListener;

        public WXPayResultReceiver(OnPayListener onPayListener) {
            this.onPayListener = onPayListener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(this);
            if (intent.getAction().equals("com.Pay")) {
                if (onPayListener != null) {
                    onPayListener.paySuccess("支付成功");
                }
            } else if (intent.getAction().equals("com.Pay.error")) {
                if (onPayListener != null) {
                    onPayListener.payFailure("支付失败");
                }
            } else if (intent.getAction().equals("com.Pay.cancle")) {
                if (onPayListener != null) {
                    onPayListener.payFailure("支付取消");
                }
            }
        }
    }
}
