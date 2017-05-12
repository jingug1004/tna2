package net.balgre.service;

import org.springframework.stereotype.Service;

import net.balgre.domain.Coupon;
import net.balgre.domain.EventCouponResponse;
import net.balgre.network.CouponRetroImpl;

@Service
public class CouponServiceImpl implements CouponService {

    @Override
    /*event coupon list*/
    public EventCouponResponse eventCoupon(Long e_id) {
        // TODO Auto-generated method stub

        CouponRetroImpl CRI = new CouponRetroImpl();
        EventCouponResponse res = CRI.eventCoupon(e_id);

        if (res == null) {
            return null;
        }

        if (res.getResultCode().equals("200")) {
            System.out.println("성공 : " + res.getMessage());
            System.out.println(res.getCouponList());
            return res;
        } else {
            System.out.println("실패 : " + res.getMessage());

            return null;
        }
    }


    @Override
	/*coupon*/
    public Coupon coupon(Long e_id) {
        // TODO Auto-generated method stub

        CouponRetroImpl CRI = new CouponRetroImpl();
        Coupon res = CRI.coupon(e_id);

        return res;
    }

}
