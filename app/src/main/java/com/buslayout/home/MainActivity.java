package com.buslayout.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.buslayout.R;
import com.buslayout.home.adapter.BusAdapter;
import com.buslayout.home.model.HomeModel;
import com.buslayout.home.model.SeatMap;
import com.buslayout.retrofit.APIService;
import com.buslayout.retrofit.ApiInterface;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private APIService apiService;
    private RecyclerView recyclerViewBusLayout;
    private Button btnRetry;
    private ProgressBar progressBar;
    private LinearLayout errorLayout;
    private TextView txtError;
    private BusAdapter adapter;
    private ArrayList<String> seatMap = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){

        apiService = ApiInterface.getClient().create(APIService.class);

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        errorLayout = (LinearLayout) findViewById(R.id.error_layout);
        btnRetry = (Button) findViewById(R.id.error_btn_retry);
        txtError = (TextView) findViewById(R.id.error_txt_cause);
        recyclerViewBusLayout = (RecyclerView) findViewById(R.id.recyclerViewBusLayout);

        try {
            callBusLayout();
        } catch (NoSuchAlgorithmException e)
        {
            System.out.println(TAG+" NoSuchAlgorithmException "+e.getMessage());
            e.printStackTrace();
        }

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    callBusLayout();
                } catch (NoSuchAlgorithmException e)
                {
                    System.out.println(TAG+" NoSuchAlgorithmException "+e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    private void callBusLayout() throws NoSuchAlgorithmException {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewBusLayout.setVisibility(View.GONE);

        //int value = getRandomNumberInRange(1,4);
        int value = 2;
        generateKey(value);
    }


    private void showErrorView(Throwable throwable) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(fetchErrorMessage(throwable));
        }
    }

    private  void showErrorView(String error) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(error);
        }
    }

    private String fetchErrorMessage(Throwable throwable) {
        String errorMsg = getResources().getString(R.string.error_msg_unknown);

        if (!isNetworkAvailable()) {
            errorMsg = getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
            errorMsg = getResources().getString(R.string.error_msg_timeout);
        }

        return errorMsg;
    }

   public  boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void getSHA(String input,String toDo,int value)
    {
        try {
            MessageDigest md = MessageDigest.getInstance(toDo);
            System.out.println(TAG +" Switch "+toDo);
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);

            while (md5.length() < 32)
                md5 = "0" + md5;

            System.out.println(TAG +" Switch "+md5);

            apiService.callBusLayoutApi(String.valueOf(value),"1234").enqueue(new Callback<HomeModel>() {
                @Override
                public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                    progressBar.setVisibility(View.GONE);
                    HomeModel model = response.body();
                    if(model.getStatus() == 200){

                        seatMap.add(model.getSeatMap().get(0).getSeatRow1());
                        seatMap.add(model.getSeatMap().get(1).getSeatRow2());
                        seatMap.add(model.getSeatMap().get(2).getSeatRow3());
                        seatMap.add(model.getSeatMap().get(3).getSeatRow4());
                        seatMap.add(model.getSeatMap().get(4).getSeatRow5());
                        seatMap.add(model.getSeatMap().get(5).getSeatRow6());
                        seatMap.add(model.getSeatMap().get(6).getSeatRow7());
                        seatMap.add(model.getSeatMap().get(7).getSeatRow8());
                        seatMap.add(model.getSeatMap().get(8).getSeatRow9());
                        seatMap.add(model.getSeatMap().get(9).getSeatRow10());
                        seatMap.add(model.getSeatMap().get(10).getSeatRow11());
                        seatMap.add(model.getSeatMap().get(11).getSeatRow12());
                        seatMap.add(model.getSeatMap().get(12).getSeatRow13());

                        recyclerViewBusLayout.setVisibility(View.VISIBLE);
                        recyclerViewBusLayout.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
                        adapter = new BusAdapter(MainActivity.this,seatMap);
                        recyclerViewBusLayout.setAdapter(adapter);

                    }else {
                        recyclerViewBusLayout.setVisibility(View.GONE);
                        showErrorView(model.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<HomeModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    recyclerViewBusLayout.setVisibility(View.GONE);
                    showErrorView(t);
                }
            });
        } catch (NoSuchAlgorithmException e) {
            System.out.println(TAG +" Switch "+e.getMessage());

        }
    }

    private String generateKey(int case_id) throws NoSuchAlgorithmException {
        System.out.println(TAG +" Switch "+case_id);
        switch (case_id){
            case 1:
                getSHA(String.valueOf(case_id),"MD5",case_id);
                break;
            case 2:
                getSHA(String.valueOf(case_id),"SHA-1",case_id);
                break;
            case 3:
                getSHA(String.valueOf(case_id),"SHA-256",case_id);
                break;
            case 4:
                getSHA(String.valueOf(case_id),"SHA-512",case_id);
                break;
        }
        return null;
    }
}