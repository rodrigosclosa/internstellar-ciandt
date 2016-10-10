package com.ciandt.internstellarapi.helper;

import com.google.api.server.spi.config.ApiMethod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rodrigosclosa on 05/01/16.
 */
public class RestClient {

    public RestClient() {
    }

    public String Post(String URL, String data, ArrayList<RestHeaders> headers) {

        StringBuilder output = new StringBuilder();

        try {

            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            //conn.setRequestProperty("Content-Type", contentType); //"Content-Type", "application/json"

            if(headers != null) {
                for (RestHeaders header : headers) {
                    conn.setRequestProperty (header.getKey(), header.getValue());
                }
            }

            if(data != null && !data.isEmpty()) {
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
                writer.write(data);
                writer.close();
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String retorno = "";
                while ((retorno = br.readLine()) != null) {
                    System.out.println(retorno);
                    output.append(retorno);
                }
                conn.disconnect();
                throw new RuntimeException(String.format("Failed : HTTP error code : %s, Http Content: %s", conn.getResponseCode(), output.toString()));
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String retorno = "";

            while ((retorno = br.readLine()) != null) {
                System.out.println(retorno);
                output.append(retorno);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public String Get(String URL, String data, String acceptType, ArrayList<RestHeaders> headers) {

        StringBuilder output = new StringBuilder();

        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(ApiMethod.HttpMethod.GET);
            conn.setRequestProperty("Accept", acceptType);

            if(headers != null) {
                for (RestHeaders header : headers) {
                    conn.setRequestProperty (header.getKey(), header.getValue());
                }
            }

            if(data != null && !data.isEmpty()) {
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
                writer.write(data);
                writer.close();
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String retorno = "";
                while ((retorno = br.readLine()) != null) {
                    System.out.println(retorno);
                    output.append(retorno);
                }
                conn.disconnect();
                throw new RuntimeException(String.format("Failed : HTTP error code : %s, Http response: %s", conn.getResponseCode(), output.toString()));
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String retorno = "";

            while ((retorno = br.readLine()) != null) {
                System.out.println(retorno);
                output.append(retorno);
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}
