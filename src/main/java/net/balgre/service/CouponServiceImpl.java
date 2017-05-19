package net.balgre.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.balgre.domain.CouponUserResponse;
import net.balgre.network.CouponRetroImpl;

@Service
public class CouponServiceImpl implements CouponService {
	
	private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

	/*coupon list*/
	@Override
	public CouponUserResponse myCouponList2(String token) {
		// TODO Auto-generated method stub
		
		logger.info("[ServiceImpl] 컨트롤러에서 받은 token : " + token);
		
		CouponRetroImpl CRI = new CouponRetroImpl();
		
		CouponUserResponse res = CRI.myCouponList2("Bearer " + token);
		
		logger.info("[ServiceImpl] 레트로에서 받은 token : " + token);
		
		if (res == null) {
			
			return null;
		}
		
		if (res.getResultCode().equals("200")) {
			logger.info("성공 : " + res.getMessage());
			logger.info(res.getTimestamp());
			
			return res;
		} else {
			logger.info("실패 : " + res.getMessage());
			
			return null;
		}
	}

	
	/*coupon insert*/
	@Override
	public CouponUserResponse myCouponInsert2(String token, String c_id) {
		// TODO Auto-generated method stub
		
		logger.info("[ServiceImpl] 컨트롤러에서 받은 token" + token);
		
		CouponRetroImpl CRI = new CouponRetroImpl();
		
		CouponUserResponse res = CRI.myCouponInsert2("Bearer " + token, c_id);
		
		if (res == null) {
			
			return null;
		}
		
		if (res.getResultCode().equals("200")) {
			logger.info("성공 : " + res.getMessage());
			logger.info(res.getTimestamp());
			
			return res;
		} else {
			logger.info("실패 : " + res.getMessage());
			
			return null;
		}
	}
}
