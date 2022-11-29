package com.if3a.quote.api;

import com.if3a.quote.models.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("quotes")
    Call<List<Quote>> getQuote();


}
