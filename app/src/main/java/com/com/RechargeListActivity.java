package com.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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

public class RechargeListActivity extends AppCompatActivity {

    TextView txt_title_headline;
    ImageView img_title_back;

    Button btn_inquire;
    Spinner spi_id;
    ListView list_re;

    String UserName = "user1";
    JSONObject jsonObject;

    ArrayList<HashMap<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_list);
        intViews();
    }

    private void intViews() {
        txt_title_headline = findViewById(R.id.txt_title_headline);
        img_title_back = findViewById(R.id.img_title_back);
        btn_inquire = findViewById(R.id.btn_inquire);
        spi_id =findViewById(R.id.spi_id);
        list_re = findViewById(R.id.list_re);

//        更改标题栏样式
        txt_title_headline.setText("小车充值记录查询");
        img_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        查询单击事件
//        1.获取Spinner的值
//        2.将Spinner的值和UserName转化为Json
//        3.开始数据解析
        btn_inquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData();
            }
        });
//        1.获取Spinner的值
        spi_id.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String carId = RechargeListActivity.this.getResources().getStringArray(R.array.carpay)[position];
                getJson(carId);
                Log.d("spinner", "onItemSelected: "+carId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void GetData() {
        String url = "http://120.76.210.221/SkillExam/etcrecharge";
        RequestQueue requestQueue = Volley.newRequestQueue(RechargeListActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject j) {
                        data = new ArrayList<HashMap<String, Object>>();
                        HashMap<String,Object> charmap;

                        Gson gson = new Gson();
                        RechargeBean rechargeBean = gson.fromJson(j.toString(), RechargeBean.class);
                        Log.d("1111111",j.toString());
                        Log.d("1111111",jsonObject.toString());
                        Log.d("1111111",rechargeBean.toString());
                        List<RechargeBean.ListBean> list = rechargeBean.getList();
                        Log.d("1111111",list.toString());
                        for (int i = 0; i < list.size(); i++) {
                            RechargeBean.ListBean listBean = list.get(i);
                            String id = String.valueOf(listBean.getId());
                            String Money = String.valueOf(listBean.getMoney());
                            String RechargeTime = String.valueOf(listBean.getRechargeTime());
                            String Balance = String.valueOf(listBean.getBalance());

                            charmap = new HashMap<>();
                            charmap.put("id",id);
                            charmap.put("Money",Money);
                            charmap.put("RechargeTime",RechargeTime);
                            charmap.put("Balance",Balance);
                            data.add(charmap);
                        }
                        SetAdapter();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("Error", "onErrorResponse: "+volleyError);
                    }
                }
        );
        requestQueue.add(request);
    }

    private void SetAdapter() {
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                RechargeListActivity.this,
                data,
                R.layout.item_pay_layout,
                new String[]{"id","Money","RechargeTime","Balance"},
                new int[]{R.id.txt_item_pay_id,R.id.txt_item_pay_money,R.id.txt_item_pay_time,R.id.txt_item_pay_Balance}
        );
        list_re.setAdapter(simpleAdapter);
    }

    //     2.将Spinner的值和UserName转化为Json
    private void getJson(String carId) {
        Map<String,String> map = new HashMap<>();
        map.put("UserName",UserName);
        map.put("CarId",carId);
        jsonObject = new JSONObject(map);
        Log.d("getJson", "getJson: "+jsonObject);
    }
}
