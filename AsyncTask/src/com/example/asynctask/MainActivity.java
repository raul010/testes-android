package com.example.asynctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.asyntask.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ProgressDialog dialog;
	private Button startButton;
	private ImageView imageView;
	private DownloadImageTask task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageView = (ImageView) findViewById(R.id.image_view);
		startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog = ProgressDialog.show(MainActivity.this,
						getString(R.string.download),
						getString(R.string.downloading));
				task = new DownloadImageTask();
				task.execute("http://k19.com.br/css/img/main-header-logo.png");
			}
		});

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				return downloadBitmap(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result != null) {
				imageView.setImageBitmap(result);
			}
		}

		private Bitmap downloadBitmap(String url) throws IOException {
			URL imageUrl = null;
			try {
				imageUrl = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
			Bitmap bitmapImage = null;
			try {
				HttpURLConnection conn = (HttpURLConnection) imageUrl
						.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				bitmapImage = BitmapFactory.decodeStream(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return bitmapImage;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
