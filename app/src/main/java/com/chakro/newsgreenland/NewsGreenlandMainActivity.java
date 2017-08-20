package com.chakro.newsgreenland;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class NewsGreenlandMainActivity extends AppCompatActivity
{
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;
    String url=null;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_greenland_main);

        try{
            //admob ads
            mAdView = (AdView) findViewById(R.id.adView);

            //AdRequest adRequest = new AdRequest.Builder().build();
           AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    // Check the LogCat to get your test device ID
                    .addTestDevice("5CED66B72B7A26A16FAD93785257BFAB")
                    .build();

            mAdView.loadAd(adRequest);



            //set grid view item
            Bitmap sermitsiaq = BitmapFactory.decodeResource(this.getResources(), R.drawable.sermitsiaq);
            Bitmap knr = BitmapFactory.decodeResource(this.getResources(), R.drawable.knr_logo);
            Bitmap nuuk = BitmapFactory.decodeResource(this.getResources(), R.drawable.nuuk_tv_logo);
            Bitmap googleNews = BitmapFactory.decodeResource(this.getResources(), R.drawable.google_news);
            Bitmap ekstrabladet = BitmapFactory.decodeResource(this.getResources(), R.drawable.ekstrabladet_logo);
            Bitmap dmi = BitmapFactory.decodeResource(this.getResources(), R.drawable.dmi_logo);
            Bitmap bt = BitmapFactory.decodeResource(this.getResources(), R.drawable.bt_logo);
            Bitmap gyllandsPosten = BitmapFactory.decodeResource(this.getResources(), R.drawable.jyllands_posten_logo);



//http://ekstrabladet.dk/
            gridArray.add(new Item(sermitsiaq,"Sermitsiaq"));
            gridArray.add(new Item(knr,"KNR - Kalaallit Nunaata Radioa"));
            gridArray.add(new Item(nuuk,"Nuuk TV"));
            gridArray.add(new Item(googleNews, "Google News - GreenLand"));
            gridArray.add(new Item(ekstrabladet, "Ekstra Bladet"));
            gridArray.add(new Item(dmi, "DMI"));
            gridArray.add(new Item(bt, "bt.dk"));
            gridArray.add(new Item(gyllandsPosten, "JP"));


            gridView = (GridView) findViewById(R.id.gvGridView1);
            customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
            gridView.setAdapter(customGridAdapter);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position,long id)
                {
                    //Toast.makeText(MainActivity.this,"----> "+ position + "  #Selected",Toast.LENGTH_SHORT).show();

                    switch (position)
                    {
                        case 0:
                            url = "http://sermitsiaq.ag/";
                            openWebsite(url);
                            break;
                        case 1:
                            url = "http://knr.gl/da";
                            openWebsite(url);
                            break;
                        case 2:
                            url = "http://www.nuuktv.gl/";
                            openWebsite(url);
                            break;
                        case 3:
                            url = "https://news.google.com/news/search/section/q/greenland/greenland?hl=en&ned=us";
                            openWebsite(url);
                            break;
                        case 4:
                            url = "http://ekstrabladet.dk/";
                            openWebsite(url);
                            break;
                        case 5:
                            url = "http://www.dmi.dk/vejr/";
                            openWebsite(url);
                            break;
                        case 6:
                            url = "http://www.bt.dk/";
                            openWebsite(url);
                            break;
                        case 7:
                            url = "http://jyllands-posten.dk/";
                            openWebsite(url);
                            break;


                        default:
                            url = "http://sermitsiaq.ag/";
                            openWebsite(url);
                            break;
                    }
                }
            });
        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }


    private void openWebsite(String url)
    {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
