package net.balgre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.balgre.domain.EventCouponResponse;
import net.balgre.service.CouponService;

@Controller
public class CouponController {
	
	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
	
	@Autowired
	private CouponService couponService;
	
	/*event coupon list*/
	@RequestMapping("/eventCoupon")
	public String eventCouponList(Model model, long e_id) {
		
		logger.info("e_id : " + e_id);
		
		EventCouponResponse ecr = couponService.eventCoupon(e_id);
		
		logger.info("ecr : " + ecr);
		
		model.addAttribute("couponList", ecr);
		
		return "";
		
	}
	
}
