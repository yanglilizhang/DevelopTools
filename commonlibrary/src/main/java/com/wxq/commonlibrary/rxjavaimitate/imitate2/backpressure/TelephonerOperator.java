package com.wxq.commonlibrary.rxjavaimitate.imitate2.backpressure;


/**
 * com.sxdsf.async.imitate2.TelephonerOperator
 *
 * @author SXDSF
 * @date 2017/11/12 下午10:27
 * @desc 操作符接口
 */

public interface TelephonerOperator<T, R> {

    Receiver<R> call(Receiver<T> callee);
}
