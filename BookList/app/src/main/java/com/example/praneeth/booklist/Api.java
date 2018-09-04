package com.example.praneeth.booklist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL="https://www.googleapis.com/books/v1/";

    @GET("volumes")
    Call<result> getresult(@Query("q") String name,
                            @Query("maxResults") int results);
}
