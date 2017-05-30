/**
 * Created by user on 2017-04-27 오후 2:24
 * Prac / net.balgre.network
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 김진국
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/21  김진국          최초 생성
 *  </pre>
 * @since 2017/04/11
 */

package net.balgre.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.balgre.domain.CommonResponse;
import net.balgre.domain.MyReviewResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-04-27 오후 2:24
 * Prac / net.balgre.network
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 개발팀 김진국
 * @since   2017/04/10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/27  김진국          최초 생성
 *  </pre>
 */


public class ReviewRetroImpl {

    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    private ReviewRetro reviewRetro = null;

    public ReviewRetroImpl() {
        this.reviewRetro = this.create();
    }

    public HashMap<String, Object> listPageGET(int page3, long product_id3, int photo3, int sort3) {
        Call<HashMap<String, Object>> call = this.reviewRetro.listPageGET(page3, product_id3, photo3, sort3);
        try {
            Response<HashMap<String, Object>> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().toString());
                System.out.println(response.body().get("user"));
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CommonResponse reviewAddPOST(String token, long order_id, List files, String content, int star, int skin_type) {
        Call<CommonResponse> call = this.reviewRetro.reviewAddPOST(
                token, "application/x-www-form-urlencoded", order_id, files, content, star, skin_type);
        try {
            Response<CommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().toString());
                System.out.println(response.body().getMessage());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CommonResponse reviewDelDELETE(String token, long review_id) {
        Call<CommonResponse> call = this.reviewRetro.reviewDelDELETE(
                token, "application/x-www-form-urlencoded", review_id);
        try {
            Response<CommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().toString());
                System.out.println(response.body().getMessage());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CommonResponse reviewLikePOST(String token, long review_id) {
        Call<CommonResponse> call = this.reviewRetro.reviewLikePOST(
                token, "application/x-www-form-urlencoded", review_id);
        try {
            Response<CommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().toString());
                System.out.println(response.body().getMessage());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, Object> reviewListPageGET(String token, int page, long product_id, int photo, int sort) {
        Call<HashMap<String, Object>> call = this.reviewRetro.reviewListPageGET(
                token, "application/x-www-form-urlencoded", page, product_id, photo, sort);
        try {
            Response<HashMap<String, Object>> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().toString());
//                System.out.println(response.body().getMessage());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MyReviewResponse reviewMyreviewPOST(String token) {
        Call<MyReviewResponse> call = this.reviewRetro.reviewMyreviewPOST(
                token, "application/x-www-form-urlencoded");
        try {
            Response<MyReviewResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().toString());
//                System.out.println(response.body().getMessage());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    protected ReviewRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(ReviewRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);

    }

}