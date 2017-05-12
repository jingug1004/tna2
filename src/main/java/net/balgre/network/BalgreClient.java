package net.balgre.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.balgre.domain.MainResponse;
import net.balgre.domain.Product;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class BalgreClient {

    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    private Balgre balgre = null;

    public BalgreClient() {
        this.balgre = this.create();
    }

    public MainResponse getMain() {
        Call<MainResponse> call = this.balgre.getMain();
        try {
            Response<MainResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().getResultCode());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Product getProductDetail(Long productId) {
        Call<Product> call = this.balgre.getProductDetail(productId);
        try {
            Response<Product> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    protected Balgre create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(Balgre.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

}
