package com.example.httpandjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView nameText = (TextView) findViewById(R.id.name_text);
		TextView phoneText = (TextView) findViewById(R.id.phone_text);
		TextView addressText = (TextView) findViewById(R.id.address_text);
		TextView cityText = (TextView) findViewById(R.id.city_text);
		TextView likesText = (TextView) findViewById(R.id.likes_text);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		String response = makeRequest("http://graph.facebook.com/k19treinamentos");

		try {
			JSONObject json = new JSONObject(response);
			String name = json.getString("name");
			String phone = json.getString("phone");
			int likes = json.getInt("likes");
			
			String address = json.getJSONObject("location").getString("street");
			String city = json.getJSONObject("location").getString("city");
			
			nameText.setText(name);
			phoneText.setText(getString(R.string.phone_label, phone));
			addressText.setText(getString(R.string.address_label, address));
			cityText.setText(getString(R.string.city_label, city));
			likesText.setText(getString(R.string.likes_label, likes));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private String makeRequest(String urlAddress) {
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		try {
			url = new URL(urlAddress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}

		return response;
	}

	private String readStream(HttpURLConnection con) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		String line = null;

		try {
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
