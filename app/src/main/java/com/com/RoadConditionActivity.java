package com.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RoadConditionActivity extends AppCompatActivity {

    Spinner spinner;
    Button btn_send,btn_status;
    String Road,RoadId;
    String UserName = "user1";
    JSONObject jsonObject;
    Button btn_etc,btn_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_condition);
        intViews();
    }

    private void intViews() {
        spinner=findViewById(R.id.spinner);
        btn_send = findViewById(R.id.btn_send);
        btn_status = findViewById(R.id.btn_status);
        btn_etc = findViewById(R.id.btn_etc);
        btn_pay = findViewById(R.id.btn_pay);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Road = RoadConditionActivity.this.getResources().getStringArray(R.array.road)[position];
                GetRoadId(Road);
                Log.d("QQQ",Road);
                GetJson(RoadId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData();
            }
        });
        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RoadConditionActivity.this,EtcListActivity.class);
                startActivity(intent);
            }
        });
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RoadConditionActivity.this,RechargeListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetJson(String roadId) {

        Map<String,String> map = new HashMap<>();
        map.put("UserName",UserName);
        map.put("RoadId",RoadId);
        jsonObject = new JSONObject(map);

    }

    private void GetRoadId(String road) {
        switch (road){
            case "学院路":RoadId = "1";break;
            case "联想路":RoadId = "2";break;
            case "医院路":RoadId = "3";break;
            case "幸福路":RoadId = "4";break;
            case "停车场":RoadId = "5";break;
        }
    }

    private void GetData() {

//        {"RoadId":1,"UserName":"user1"}
        Log.d("TAG",jsonObject.toString());

        String url="http://120.76.210.221/SkillExam/getroad";
        RequestQueue requestQueue = Volley.newRequestQueue(RoadConditionActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("qqq","qqqqqqqqqqqqqqqqqqqqqq");
                        Gson gson = new Gson();
                        RoadCondBean roadBean = gson.fromJson(jsonObject.toString(), RoadCondBean.class);
                        int status= roadBean.getStatus();
                        Log.d("TAG1", String.valueOf(status));
                        BtnColor(status);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("TAG", "onErrorResponse: "+volleyError);
                    }
                }
        );
        requestQueue.add(request);
    }


    private void BtnColor(int status) {
        switch (status){
            case 0:
                Log.d("st", "BtnColor: 11111111111111111");
                btn_status.setBackgroundResource(R.color.zero);
                btn_status.setText("畅通");
                break;
            case 1:
                btn_status.setBackgroundResource(R.color.one);
                btn_status.setText("缓行");
                break;
            case 2:
                btn_status.setBackgroundResource(R.color.two);
                btn_status.setText("一般拥堵");
                break;
            case 3:
                btn_status.setBackgroundResource(R.color.three);
                btn_status.setText("中度拥堵");
                break;
            case 4:
                btn_status.setBackgroundResource(R.color.four);
                btn_status.setText("严重拥堵");
                break;
            case 5:
                btn_status.setBackgroundResource(R.color.three);
                btn_status.setText("一般拥堵");
                break;
            default:
                Log.d("TAG", "BtnColor: 数据不存在");
                break;
        }
    }
}
