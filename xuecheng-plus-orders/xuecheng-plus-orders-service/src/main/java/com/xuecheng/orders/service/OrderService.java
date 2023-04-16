package com.xuecheng.orders.service;

import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.orders.model.dto.AddOrderDto;
import com.xuecheng.orders.model.dto.PayRecordDto;
import com.xuecheng.orders.model.dto.PayStatusDto;
import com.xuecheng.orders.model.po.XcPayRecord;

/**
 * @author: Lin
 * @since: 2023-04-16
 */
public interface OrderService {


    /**
     * 创建商品订单
     * @param userId 用户id
     * @param addOrderDto 订单信息
     * @return PayRecordDto 支付记录(包括二维码)
     */
    public PayRecordDto createOrder(String userId, AddOrderDto addOrderDto);


    /**
     * 查询支付记录
     * @param payNo  交易记录号
     * @return com.xuecheng.orders.model.po.XcPayRecord
     */
    public XcPayRecord getPayRecordByPayno(String payNo);


    /**
     * 请求支付宝查询支付结果
     * @param payNo 支付记录id
     * @return 支付记录信息
     */
    public PayRecordDto queryPayResult(String payNo);


    /**
     * 保存alipay支付状态结果
     * @param payStatusDto
     */
    public void saveAliPayStatus(PayStatusDto payStatusDto);


    /**
     * 发送通知结果
     * @param message
     */
    public void notifyPayResult(MqMessage message);


}
