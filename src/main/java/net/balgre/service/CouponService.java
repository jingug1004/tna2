package net.balgre.service;

import net.balgre.domain.Coupon;
import net.balgre.domain.EventCouponResponse;

public interface CouponService {

    /*event coupon list*/
    public EventCouponResponse eventCoupon(Long e_id);

    /*coupon*/
    public Coupon coupon(Long e_id);

}
