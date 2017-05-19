package net.balgre.network;


import net.balgre.domain.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.HashMap;
import java.util.List;

public interface ProductRetro {

    @GET("/api/product/best")
    Call<BestResponse> bestResponseGET();

    @GET("/api/product/category")
    Call<CategoryResponse> categoryResponseGET();

    @GET("/api/product/detail")
    Call<ProductResponse> detailGET(
            @Query("product_id") long product_id2
    );

    @GET("/api/product/list/{page}")
    Call<HashMap<String, Object>> listPageGET(
            @Path("page") int page2,
            @Query("brand_id") long brand_id2,
            @Query("sort") int sort2
    );

    @GET("/api/product/list/{parent}/{page}")
    Call<HashMap<String, Object>> listParentPageGET(
            @Path("parent") long parent2,
            @Path("page") int page2,
            @Query("menu_id") long menu_id2,
            @Query("sort") int sort2
    );

    @GET("/api/product/search")
    Call<List<Product>> searchGET(
            @Query("search") String search2
    );

    @GET("/api/product/sub_category")
    Call<List<Product>> sub_categoryGET(
            @Query("menu_id") String sub_category2
    );

    
    /*time sale list by minho*/
    @GET("/api/product/time")
    Call<List<ProductTimeSale>> timeSaleList(
    );
    
    
    /*balgeure box by minho*/
    @GET("/api/product/box")
    Call<List<Product>> balgeureBox(
    );
    
    
    /*new product by minho*/
    @GET("/api/product/list")
    Call<BestResponse> newProduct(
    );
    
    
    /*category list by minho*/
    @GET("/api/product/category2")
    Call<CategoryResponse2> categoryList(
    );


}
