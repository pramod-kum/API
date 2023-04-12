package com.geekster.weatherAPI;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherApiApplication {

	public static void main(String[] args)throws Exception {
		URL getUrl = new URL("https://api.chucknorris.io/jokes/random");
		//https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");


		int responseCode = connection.getResponseCode();

		if (responseCode == 200) {


			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder jsonResponseData = new StringBuilder();
			String readLine = null;


			while ((readLine = in.readLine()) != null) {
				jsonResponseData.append(readLine);
			}

			in.close();
			JSONObject masterData = new JSONObject(jsonResponseData.toString());

			System.out.println("created_at " + masterData.get("created_at"));
			System.out.println("icon_url " + masterData.get("icon_url"));
			System.out.println("id " + masterData.get("id"));
			System.out.println("updated_at " + masterData.get("updated_at"));
			System.out.println("url " + masterData.get("url"));
			System.out.println("value " + masterData.get("value"));

//			JSONObject currentWeatherObj = (JSONObject) masterData.get("current_weather");
//
//			System.out.println("created_at" + currentWeatherObj.get("created_at"));
//			System.out.println("icon_url " + currentWeatherObj.get("icon_url"));
		} else {
			System.out.println("This is not valid URL- " + responseCode);
		}
	}
}

