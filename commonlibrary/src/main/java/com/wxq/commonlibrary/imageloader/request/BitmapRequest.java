package com.wxq.commonlibrary.imageloader.request;

import androidx.annotation.NonNull;
import android.widget.ImageView;

import com.wxq.commonlibrary.imageloader.config.DisplayConfig;
import com.wxq.commonlibrary.imageloader.loader.SimpleImageLoader;
import com.wxq.commonlibrary.imageloader.policy.LoadPolicy;
import com.wxq.commonlibrary.imageloader.utils.MD5Utils;

import java.lang.ref.SoftReference;

/**
 * author:wxq
 * email:805380422@qq.com
 * time:2018/11/07
 * desc:图片请求
 * version:1.0
 */
public class BitmapRequest implements Comparable<BitmapRequest> {  //排序

//    、、需要的功能 1 加载策略 2编码                 需要哪些对象服务以来她

    private LoadPolicy loadPolicy = SimpleImageLoader.getInstance().getConfig().getLoadPolicy();

    private int serialNo;


    /**
     * c持有imageview 的软引用
     */
    private SoftReference<ImageView> imageViewSoft;

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 图片路径
     */
    private String imageUrl;

    public String getImageUriMD5() {


        return imageUriMD5;
    }

    /**
     * 加密图片路径
     */
    private String imageUriMD5;

    /**
     * 下载完成监听
     */
    public SimpleImageLoader.ImageListener imageListener;


    private DisplayConfig displayConfig;


    public BitmapRequest(ImageView imageView, String imageUrl, DisplayConfig displayConfig, SimpleImageLoader.ImageListener imageListener) {
        this.imageViewSoft = new SoftReference<>(imageView);
        this.imageUrl = imageUrl;
        this.imageUriMD5 = MD5Utils.toMD5(imageUrl);
        this.imageListener = imageListener;
        if (displayConfig != null) {
            this.displayConfig = displayConfig;
        }
        //设置可见的imageview 的tag 要下载的图片路径
        imageView.setTag(imageUrl);
    }


    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }


//    、、重新hascode 何equal方法  contain方法


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest that = (BitmapRequest) o;

        if (serialNo != that.serialNo) return false;
        return loadPolicy != null ? loadPolicy.equals(that.loadPolicy) : that.loadPolicy == null;
    }

    @Override
    public int hashCode() {
        int result = loadPolicy != null ? loadPolicy.hashCode() : 0;
        result = 31 * result + serialNo;
        return result;
    }


    public ImageView getImageView() {
        return imageViewSoft.get();
    }

    @Override
    public int compareTo(@NonNull BitmapRequest o) {
        return loadPolicy.compareto(o, this);
    }
}
