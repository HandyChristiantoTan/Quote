package com.if3a.quote.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.if3a.quote.R;
import com.if3a.quote.adapters.QuoteViewAdapter;
import com.if3a.quote.api.APIRequestData;
import com.if3a.quote.api.RetroServer;
import com.if3a.quote.models.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvQuote;
    private ProgressBar pbQuote;
    private List<Quote> listQuote;
    private QuoteViewAdapter adapterQuote;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuote = findViewById(R.id.rv_quote);
        pbQuote = findViewById(R.id.pb_quote);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);

        rvQuote.setLayoutManager(linearLayoutManager);

        retrieveQuote();
    }

    private void retrieveQuote() {
        pbQuote.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<List<Quote>> retrieveProcess = ardData.getQuote();

        retrieveProcess.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                listQuote = response.body();
                adapterQuote = new QuoteViewAdapter(listQuote);
                rvQuote.setAdapter(adapterQuote);
                pbQuote.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Gagal menghubungi server : " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                Log.d("CEK : ", t.getMessage());
                pbQuote.setVisibility(View.INVISIBLE);
            }
        });
    }
}