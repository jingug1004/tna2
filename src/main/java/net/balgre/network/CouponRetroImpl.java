package net.balgre.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.balgre.domain.Coupon;
import net.balgre.domain.EventCouponResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class CouponRetroImpl {

    private static final String API_URL = "http:/digiserver.cafe24.com:10000";

    private CouponRetro couponRetro = null;

    public CouponRetroImpl() {
        this.couponRetro = this.create();
    }

//    public MainResponse getMain() {
//        Call<MainResponse> call = this.couponRetro.getMain();
//        try {
//            Response<MainResponse> response = call.execute();
//            if (response.isSuccessful()) {
//                System.out.println(response.body().getResultCode());
//                return response.body();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    /*event coupon list*/
    public EventCouponResponse eventCoupon(Long e_id) {

        Call<EventCouponResponse> call = this.couponRetro.eventCoupon(e_id);

        try {
            Response<EventCouponResponse> response = call.execute();

            if (response.isSuccessful()) {
                System.out.println(response.body());

                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*coupon list*/
    public Coupon coupon(Long e_id) {

        Call<Coupon> call = this.couponRetro.coupon(e_id);

        try {
            Response<Coupon> response = call.execute();

            if (response.isSuccessful()) {
                System.out.println(response.body());

                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected CouponRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(CouponRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

}
