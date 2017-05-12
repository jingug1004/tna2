package net.balgre.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.balgre.domain.Plan;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class PlanRetroImpl {

    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    private PlanRetro planRetro = null;

    public PlanRetroImpl() {
        this.planRetro = this.create();
    }

    public Plan getPlan(Integer pid2) {
        Call<Plan> call = this.planRetro.getPlan(pid2);
        try {
            Response<Plan> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Product getPlanDetail() {
//        Call<Plan> call = this.planRetro.getPlanDetail();
//        try {
//            Response<Plan> response = call.execute();
//            if (response.isSuccessful()) {
//                System.out.println(response.body());
//                return response.body();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }


    protected PlanRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(PlanRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);

    }

}
