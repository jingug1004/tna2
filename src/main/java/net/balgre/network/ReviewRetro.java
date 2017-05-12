/**
 * Created by user on 2017-04-27 오후 2:18
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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.HashMap;

/**
 * Created by user on 2017-04-27 오후 2:18
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


public interface ReviewRetro {

    @GET("/api/review/list/{page}")
    Call<HashMap<String, Object>> listPageGET(
            @Path("page") int page2,
            @Query("product_id") long product_id2,
            @Query("photo") int photo2,
            @Query("sort") int sort2
    );

//    @GET("/api/review/list/{page}")
//    Call<HashMap<String, Object>> listPageGET(
//            @Path("page") int page2,
//            @Query("product_id") long product_id2,
//            @Query("photo") int photo2,
//            @Query("sort") int sort2
//    );

}
