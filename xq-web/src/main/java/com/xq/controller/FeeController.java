package com.xq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xq.exception.ServiceException;
import com.xq.pojo.BaseFee;
import com.xq.pojo.Guest;
import com.xq.pojo.Room;
import com.xq.service.FeeService;
import com.xq.service.GuestFeeService;
import com.xq.utils.GuestThreadLocal;
import com.xq.vo.WebResult;

@Controller
@RequestMapping("/fee/")
/*缴费模块*/
public class FeeController {

	@Reference(check=false)
	private FeeService feeService;

	@Reference(check=false)
	private GuestFeeService guestFeeService;

	@RequestMapping("doFeeEditUI")
	public String doFeeEditUI() {
		return "manager/fee_edit";
	}

	@RequestMapping("doAliPay")
	public String doAliPay() {
		return "alipay.index";
	}

	//根据搜索时间查询记录
	@RequestMapping("doFindObjectsByTime")
	@ResponseBody
	public WebResult doFindObjectsByTime(Integer pageCurrent,String time) {
		return feeService.findObjects(pageCurrent,time);
	}

	//新增账单
	@RequestMapping("doSaveObject")
	@ResponseBody
	public WebResult doSaveObject(BaseFee fee,Room room) {
		try {
			Integer row = feeService.saveObject(fee,room);
			return WebResult.ok(row);
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.fail();
		}
	}

	//删除账单
	@RequestMapping("doDeleteObjectById")
	@ResponseBody
	public WebResult doDeleteObject(Integer id) {
		try {
			Integer row = feeService.deleteObject(id);
			return WebResult.ok(row);
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.fail();
		}
	}

	//根据用户ID和时间查询账单
	@RequestMapping("doFindObjectsByUser")
	@ResponseBody
	public WebResult doFindObjectsByUser(Integer pageCurrent,String time) {
		//根据userId查找roomId
		Guest guest = GuestThreadLocal.getUser();
		if (guest == null)
			throw new ServiceException("无用户信息");
		return guestFeeService.findObjectsByUser(guest,pageCurrent,time);
	}
	
	//根据账单Id查询详细数据
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public WebResult doFindObjectById(Model model,Long id) {
		BaseFee fee = guestFeeService.findObjectByid(id);
		model.addAttribute("fee",fee);
		return WebResult.ok(fee);
	}
}
