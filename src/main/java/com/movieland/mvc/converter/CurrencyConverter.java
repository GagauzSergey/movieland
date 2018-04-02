package com.movieland.mvc.converter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CurrencyConverter {
    private JSONObject jsonObject;

    private JSONObject getJsonFromUrl(String uri) {
        String json;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(uri);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream inputStream = httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream, "iso-8859-1"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            inputStream.close();
            json = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        // parsing to JSON
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("parsing error");
        }
        return jsonObject;
    }

    private Double getUsdFromJson(String uri) {

        JSONObject object = getJsonFromUrl(uri);
        JSONArray currencyArray = object.getJSONArray("organizations");

        JSONObject objectUniversalBank = currencyArray.getJSONObject(1);
        JSONObject currencyUniversalBankObject = objectUniversalBank.getJSONObject("currencies");

        JSONObject currencyUSDUniversalBank = currencyUniversalBankObject.getJSONObject("USD");
        String usdBid = currencyUSDUniversalBank.getString("bid");
        return Double.valueOf(usdBid);
    }

    public double usdConvert(Double ua) {
        String uri = "http://resources.finance.ua/ru/public/currency-cash.json";
        return ua / getUsdFromJson(uri);
    }
}
