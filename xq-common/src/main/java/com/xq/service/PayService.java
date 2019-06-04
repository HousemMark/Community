package com.xq.service;

import com.alipay.api.AlipayApiException;

public interface PayService {

	String excute(String flow_id, String fee_id, String fee_type, String fee_sum) throws AlipayApiException;

}
