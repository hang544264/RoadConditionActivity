package com.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtcListActivity extends AppCompatActivity {

    ListView listView;
    TextView txt_title_headline;
    ImageView img_title_back;

    String CarId ="2";
    String UserName = "user1";
    JSONObject jsonObject;

    ArrayList<HashMap<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_list);
        intViews();
        GetDate();
    }

    private void GetDate() {
        String Url = "http://120.76.210.221/SkillExam/etc";
        RequestQueue requestQueue = Volley.newRequestQueue(EtcListActivity.this);
        Log.d("ETC", "GetDate: "+jsonObject);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        data = new ArrayList<HashMap<String, Object>>();
                        HashMap<String,Object> jsmap;

                        Gson gson = new Gson();
                        EtcBean etcBean = gson.fromJson(jsonObject.toString(),EtcBean.class);
                        List<EtcBean.ListBean> list = etcBean.getList();
                        for (int i = 0;i<list.size();i++){
                            EtcBean.ListBean listBean =list.get(i);
                            String CarId = String.valueOf(listBean.getCarId());
                            String EtcInTime = listBean.getEtcInTime();
                            String EtcOutTime =listBean.getEtcOutTime();
                            String Money = String.valueOf(listBean.getMoney());

                            jsmap = new HashMap<>();
                            jsmap.put("CarId",CarId);
                            jsmap.put("EtcInTime",EtcInTime);
                            jsmap.put("EtcOutTime",EtcOutTime);
                            jsmap.put("Money",Money);
                            data.add(jsmap);

                            Log.d("etc", "onResponse: "+jsmap);
                        }
                            setAdapters();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ETC", "onErrorResponse: "+volleyError);
                    }
                }
        );
        requestQueue.add(request);
    }

    private void setAdapters() {
        Log.d("etc", "setAdapters: 11111111");
        SimpleAdapter adapter = new SimpleAdapter(
            EtcListActivity.this,
                data,
                R.layout.item_layout,
                new String[]{"CarId","EtcInTime","EtcOutTime","Money"},
                new int[]{R.id.txt_item_CarId,R.id.txt_item_EtcInTime,R.id.txt_item_EtcOutTime,R.id.txt_item_Money}
        );
        listView.setAdapter(adapter);
    }

    private void intViews() {

        listView = findViewById(R.id.listview);
        txt_title_headline = findViewById(R.id.txt_title_headline);
        img_title_back = findViewById(R.id.img_title_back);

//        更改标题栏样式
        txt_title_headline.setText("ETC记录");
        img_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        封装入参
//     {"CarId":2,"UserName":"user1"}
        Map<String,String> map = new HashMap<>();
        map.put("UserName",UserName);
        map.put("CarId",CarId);
        jsonObject = new JSONObject(map);

    }
}
