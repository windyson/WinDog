package com.yunbot.windog.agent;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class QuoteAPI {
    public static void main(String[] args) {
        //获取历史行情数据
        String ret = HttpRequest.sendGet("http://127.0.0.1:5000/getHistoryQuotes", "datetime=202303291440");
        Map<String, Object> result = JSONObject.parseObject(ret);
        Map<String, Object> stocks = JSONObject.parseObject(result.get("data").toString());
        System.out.println(stocks.get("sh600031"));

        //获取当前行情数据
        ret = HttpRequest.sendGet("http://127.0.0.1:5000/getRTQuotes", "stocklist=sh600031,bj430047");
//        ret = HttpRequest.sendGet("http://127.0.0.1:5000/getRTQuotes", "");
        result = JSONObject.parseObject(ret);
        stocks = JSONObject.parseObject(result.get("data").toString());
        System.out.println(stocks.get("sh600031"));
        System.out.println(stocks.get("bj430047"));
    }
}
