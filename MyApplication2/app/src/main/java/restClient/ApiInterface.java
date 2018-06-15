package restClient;

import com.tribikram.myapplication.models.Flower;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Tribikram on 5/18/2018.
 */

public interface ApiInterface {

    @GET("feeds/flowers.json")
    Call<ArrayList<Flower>> getFlowers();

    @FormUrlEncoded
    @POST("Flowers/flower.php")
    Call<String> createFlower(@Field("name") String name,
                              @Field("category") String category,
                              @Field("instruction")String instruction,
                              @Field("price") float price);

   /* @POST("feeds/new")
    Call<Flower> createFlower(@Body Flower flower);*/

}
