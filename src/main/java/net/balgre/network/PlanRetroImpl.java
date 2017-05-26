package net.balgre.network;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.balgre.domain.PagePlan;
import net.balgre.domain.Plan;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanRetroImpl {

    private static final Logger logger = LoggerFactory.getLogger(PlanRetroImpl.class);
    
    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    
    private PlanRetro planRetro = null;
    
    public PlanRetroImpl() {
    	this.planRetro = this.create();
    }
    
    
    /*plan list by minho*/
    public PagePlan planList2(int page) {
    	logger.info("[PlanRetroImpl] 서비스에서 받은 값 : " + page);
    	
    	Call<PagePlan> call = this.planRetro.planList(page);
    	
    	try {
    		Response<PagePlan> response = call.execute();
    		if (response.isSuccessful()) {
    			
    			return response.body();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
    /*plan detail by minho*/
    public Plan planDetail2(long pid) {
    	logger.info("[PlanRetroImpl] 서비스에서 받은 값 : " + pid);
    	
    	Call<Plan> call = this.planRetro.planDetail(pid);
    	
    	try {
    		Response<Plan> response = call.execute();
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


    protected PlanRetro create() {

		Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(buildGsonConverter()).build();

		return retrofit.create(PlanRetro.class);
	}

	protected GsonConverterFactory buildGsonConverter() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		Gson myGson = gsonBuilder.create();

		return GsonConverterFactory.create(myGson);
	}

}
