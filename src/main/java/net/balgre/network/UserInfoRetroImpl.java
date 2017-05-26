package net.balgre.network;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.balgre.domain.UserResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInfoRetroImpl {
	
private static final Logger logger = LoggerFactory.getLogger(UserInfoRetroImpl.class);
	
	private static final String API_URL = "http://digiserver.cafe24.com:10000";
	
    private UserInfoRetro userInfoRetro = null;
	
	public UserInfoRetroImpl() {
		this.userInfoRetro = this.create();
	}
	
	
	/*user info by minho*/
	public UserResponse getUserInfo2(String token) {
		
		logger.info("[UserInfoRetroImpl] Service에서 받은 token : " + token);
		
		Call<UserResponse> call = this.userInfoRetro.getUserInfo(token, 
				"application/x-www-form-urlencoded");
		
		try {
			Response<UserResponse> response = call.execute();
			logger.info("response : " + response);
			if (response.isSuccessful()) {
				System.out.println(response.body());
				return response.body();
			}
		} catch (IOException e) {
		}
		return null;
	}
	
	
	
	
	
	
	protected UserInfoRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(UserInfoRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }
}
