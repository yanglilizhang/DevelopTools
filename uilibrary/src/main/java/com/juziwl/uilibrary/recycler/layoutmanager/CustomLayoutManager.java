package com.juziwl.uilibrary.recycler.layoutmanager;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

/**
 * author:wxq
 * email:805380422@qq.com
 * time:2019/03/07
 * desc:自定义layoutMangere
 * version:1.0
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {

//    这张图讲的是Rv和Lv的缓存机制对比，作者视图结合用Lv的2级缓存来让我去理解Rv的4级缓存机制。

//    这个方法就是RecyclerView Item的布局参数，换种说法，就是RecyclerView 子 item 的 LayoutParameters，若是想修改子Item的布局参数（比如：宽/高/margin/padding等等），那么可以在该方法内进行设置。
//    一般来说，没什么特殊需求的话，则可以直接让子item自己决定自己的宽高即可（wrap_content）。

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    /**
     *
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e("onLayoutChildren","onLayoutChildren"+state.toString());
        super.onLayoutChildren(recycler, state);
    }




}



//以下方法动态设置间距方便
//    CardView ll_inner = helper.getView(R.id.cardView_item_root);
//    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) ll_inner.getLayoutParams();
//                        if (position % 2 == 0) {
//                                //左边的item
//                                layoutParams.setMargins(DisplayUtils.dip2px(15), DisplayUtils.dip2px(5), DisplayUtils.dip2px(5), DisplayUtils.dip2px(5));
//                                } else {
//                                //右边的item
//                                layoutParams.setMargins(DisplayUtils.dip2px(5), DisplayUtils.dip2px(5), DisplayUtils.dip2px(15), DisplayUtils.dip2px(5));
//                                }


