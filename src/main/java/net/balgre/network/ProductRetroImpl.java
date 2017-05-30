package net.balgre.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.balgre.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class ProductRetroImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRetroImpl.class);

    private static final String API_URL = "http://digiserver.cafe24.com:10000";
    private ProductRetro productRetro = null;

    public ProductRetroImpl() {
        this.productRetro = this.create();
    }

    public BestResponse bestResponseGET() {
        Call<BestResponse> call = this.productRetro.bestResponseGET();
        try {
            Response<BestResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().getResultCode());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CategoryResponse categoryResponseGET() {
        Call<CategoryResponse> call = this.productRetro.categoryResponseGET();
        try {
            Response<CategoryResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().getResultCode());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductResponse detailGET(long product_id3) {
        Call<ProductResponse> call = this.productRetro.detailGET(product_id3);
        try {
            Response<ProductResponse> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body());
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, Object> listPageGET(int page3, long brand_id3, int sort3) {
        Call<HashMap<String, Object>> call = this.productRetro.listPageGET(page3, brand_id3, sort3);
        try {
            Response<HashMap<String, Object>> response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().toString());
                System.out.println(response.body().get("content"));
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        /*public HashMap<String, Object> listParentPageGET(long parent3, int page3, long menu_id3, int sort3) {
            Call<HashMap<String, Object>> call = this.productRetro.listParentPageGET(parent3, page3, menu_id3, sort3);
            try {
                Response<HashMap<String, Object>> response = call.execute();
                if (response.isSuccessful()) {
                    System.out.println(response.body().get("content"));
                    System.out.println(response.body().toString());
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }*/

        public List<Product> searchGET(String search3) {
            Call<List<Product>> call = this.productRetro.searchGET(search3);
            try {
                Response<List<Product>> response = call.execute();
                if (response.isSuccessful()) {
                    System.out.println(response.body().toString());
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    
    /*time sale list by minho*/
    public List<ProductTimeSale> timeSaleList() {
            
    	Call<List<ProductTimeSale>> call = this.productRetro.timeSaleList();
            
    	try {
            Response<List<ProductTimeSale>> response = call.execute();
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
    
    
    /*balgeure box by minho*/
    public List<Product> balgeureBox() {
    	
    	Call<List<Product>> call = this.productRetro.balgeureBox();
    	
    	try {
    		Response<List<Product>> response = call.execute();
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
    
    
    /*new product by minho*/
    public BestResponse newProduct() {
    	
    	Call<BestResponse> call = this.productRetro.newProduct();
    	
    	try {
    		Response<BestResponse> response = call.execute();
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
    
    
    /*category by minho*/
    public CategoryResponse categoryList() {
    	
    	Call<CategoryResponse> call = this.productRetro.categoryList();
    	
    	try {
    		Response<CategoryResponse> response = call.execute();
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
    
    
    /*category2 by minho*/
    public CategoryResponse subCategory(long menu_id) {
    	
    	Call<CategoryResponse> call = this.productRetro.subCategory(menu_id);
    	
    	try {
    		Response<CategoryResponse> response = call.execute();
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
    
    
    /*category list by minho*/
    public PageProduct categoryList2(long parent, int page, long menu_id, int sort) {
    	
    	Call<PageProduct> call = this.productRetro.categoryList(parent, page, menu_id, sort);
    	
    	try {
    		Response<PageProduct> response = call.execute();
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

//    public ProductRetro getProduct(Long productId) {
//        Call<Product> call = this.productRetro.getProduct();
//        try {
//            Response<Product> response = call.execute();
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

    protected ProductRetro create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .build();

        return retrofit.create(ProductRetro.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);

    }

}
