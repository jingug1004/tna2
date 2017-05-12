package net.balgre.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.balgre.domain.CommonResponse;
import net.balgre.domain.MainResponse;
import net.balgre.domain.User;
import net.balgre.domain.UserResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class UserJoinRetroImpl {
    // 인터페이스 구현해서 사용해도 됨.

    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    // 상수를 만들어서 서버 주소를 대입한다.
    private UserJoinRetro userJoinRetro = null;
    // Balgre인터페이스 인스턴스 변수 선언 후 초기화

    public UserJoinRetroImpl() {
        // 생성자
        this.userJoinRetro = this.create();
        // 현재 클래스의 인스턴스에 현재 클래스의 create 메서드 대입
    }


    public MainResponse getMain() {
        Call<MainResponse> call = this.userJoinRetro.getMain();
        try {
            Response<MainResponse> response = call.execute();
            if ( response.isSuccessful() ) {
                System.out.println(response.body().getResultCode());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /*user join*/
    public UserResponse userJoin2(User user) {
        // 해당 메서드는 serviceImpl에서 호출했으며 파라미터로 user를 전달받음.

        // retro interface userJoin 메서드를 호출하며, user를 파라미터로 전달해줌.
        // parameter값은 domain에서 꺼내서 넘겨주는 것이기 때문에 Retro가 아닌 domain 필드명을 따라간다.
        Call<UserResponse> call = this.userJoinRetro.userJoin(user.getUserId(), user.getEmail(),
                user.getName(), null, user.getGender(), user.getBirthday(),
                user.getPhone(), user.getJoinType(), user.getSkinType(), user.getSmsAgree(),
                user.getEmailAgree(), user.getMyCode());

        try {
            Response<UserResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*send sms*/
    public CommonResponse phone_check(String phone) {

        Call<CommonResponse> call = this.userJoinRetro.phone_check(phone);

        try {
            Response<CommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*sms_cert*/
    public CommonResponse phone_check2(String phone, String cert) {

        Call<CommonResponse> call = this.userJoinRetro.phone_check2(phone, cert);

        try {
            Response<CommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected UserJoinRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(UserJoinRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

}
