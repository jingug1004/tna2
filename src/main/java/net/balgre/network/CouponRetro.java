package net.balgre.network;

import net.balgre.domain.Coupon;
import net.balgre.domain.EventCouponResponse;
import net.balgre.domain.MainResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CouponRetro {

    @GET("/api/main")
    Call<MainResponse> getMain();

    /*event coupon list*/
    @GET("/api/coupon/list")
    Call<EventCouponResponse> eventCoupon(
            @Query("e_id") Long e_id
    );

    @GET("api/coupon/list")
    Call<Coupon> coupon(
            @Query("e_id") Long e_id
    );


}
