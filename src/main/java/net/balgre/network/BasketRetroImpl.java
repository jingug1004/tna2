/**
 * Created by user on 2017-04-30 오후 5:51
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
import net.balgre.domain.BasketResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by user on 2017-04-30 오후 5:51
 * Prac / net.balgre.network
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see
 * @since 2017/04/10
 * <p>
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/30  김진국          최초 생성
 *  </pre>
 */

public class BasketRetroImpl {

    private static final Logger logger = LoggerFactory.getLogger(BasketRetroImpl.class);

    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    private BasketRetro basketRetro = null;

    public BasketRetroImpl() {
        this.basketRetro = this.create();
    }

    public BasketResponse basketListGET(String token) {

        logger.info("retroImpl Token : " + token);

        Call<BasketResponse> call = this.basketRetro.basketGET(token,
                "application/x-www-form-urlencoded");

        logger.info("token : " + token);

        try {
            Response<BasketResponse> response = call.execute();

            logger.info("response : " + response);

            if (response.isSuccessful()) {

                logger.info("response.toString() : " + response.toString());
//                System.out.println(response.body());

                return response.body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BasketResponse basketAddPOST(String token3,  long itemId, int itemCount) {

        Call<BasketResponse> call = this.basketRetro.basketAddPOST
                (token3, "application/x-www-form-urlencoded", itemId, itemCount);

        try {
            Response<BasketResponse> response = call.execute();

            if (response.isSuccessful()) {

                logger.info("lll~~~ @BasketRetroImpl " + response.toString() + " @BasketRetroImpl lll~~~");

                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected BasketRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(BasketRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

}