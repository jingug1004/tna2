package net.balgre.network;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.balgre.domain.CouponUserResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponRetroImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(CouponRetroImpl.class);
	
	private static final String API_URL = "http://digiserver.cafe24.com:10000";
	
	private CouponRetro couponRetro = null;
	
	public CouponRetroImpl() {
		this.couponRetro = this.create();
	}
	
	
	/*coupon list*/
	public CouponUserResponse myCouponList2(String token) {
		
		logger.info("[RetroImpl] 서비스에서 받은 token : " + token);
		
		Call<CouponUserResponse> call = this.couponRetro.myCouponList(token, 
				"application/x-www-form-urlencoded");
		
		try {
			Response<CouponUserResponse> response = call.execute();
			logger.info("response : " + response);
			if (response.isSuccessful()) {
				logger.info("response.body : " + response.body());
				
				return response.body();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*coupon insert*/
	public CouponUserResponse myCouponInsert2(String token, String c_id) {
		
		logger.info("[RetroImpl] 서비스에서 받은 token : " + token);
		
		Call<CouponUserResponse> call = this.couponRetro.myCouponInsert(token, 
				"application/x-www-form-urlencoded", c_id);
		
		try {
			Response<CouponUserResponse> response = call.execute();
			logger.info("response : " + response);
			if (response.isSuccessful()) {
				logger.info("response.body : " + response.body());
				
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
