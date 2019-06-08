package com.xq.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xq.config.AlipayConfig;

/* 支付服务 */
@Service
public class PayServiceImpl implements PayService {

	@Override
	public String excute(String flow_id ,String fee_id, String fee_type, String fee_sum) throws AlipayApiException {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		//流水订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = flow_id;
		//订单号
		String fee_no = fee_id;
		//付款金额，必填
		String total_amount = fee_sum;
		//订单名称，必填
		String subject = fee_type;
		//商品描述，可空
//		String body = "用户订购商品个数：" + order.getBuyCounts();
		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    	String timeout_express = "1h";
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"fee_no\":\""+ fee_no +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		return result;
	}
	
}