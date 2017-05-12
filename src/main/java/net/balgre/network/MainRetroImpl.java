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

public class MainRetroImpl {

    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    private MainRetro mainRetro = null;

    public MainRetroImpl() {
        this.mainRetro = this.create();
    }

    public MainResponse getMain() {
        Call<MainResponse> call = this.mainRetro.getMain();
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

    public Product getProduct(Long productId) {
        Call<Product> call = this.mainRetro.getProduct();
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

    protected MainRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(MainRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);

    }

}
