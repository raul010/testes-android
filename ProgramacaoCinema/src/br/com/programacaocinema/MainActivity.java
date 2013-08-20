package br.com.programacaocinema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.codifico.model.Cinema;
import br.com.codifico.model.Endereco;
import br.com.codifico.model.Filme;
import br.com.codifico.model.apresentacao.EnderecoResumo;
import br.com.codifico.model.apresentacao.FilmeCartaz;
import br.com.codifico.model.element.CinemaElement;
import br.com.programacaocinema.view.CinemaElementView;
import br.com.programacaocinema.view.EnderecoResumoView;
import br.com.programacaocinema.view.FilmeCartazView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {
	List<CinemaElementView> cinemas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		cinemas = recuperaLista();
		
		ArrayAdapter<CinemaElementView> cinemaAdapter = new ArrayAdapter<CinemaElementView>(
				this, android.R.layout.simple_list_item_1, cinemas);
		
		ListView listaCinema = (ListView) findViewById(R.id.lista_cinema);
		listaCinema.setAdapter(cinemaAdapter);
		
		listaCinema.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {
				CinemaElementView cinema = cinemas.get(position);
				
				ArrayAdapter<Filme> filmeAdapter = new ArrayAdapter<Filme>(new FilmesActivity(), 
						android.R.layout.simple_list_item_1,  cinema.getFilmes());
				
				ListView listaFilme = (ListView) findViewById(R.id.lista_filme);
				listaFilme.setAdapter(filmeAdapter);
			}
			
		});
		
		for (CinemaElement cinemaElement : cinemas) {
			System.out.println("CINEMA: " + cinemaElement.getNome());
			System.out.println("ENDERECO: "
					+ ((EnderecoResumo) cinemaElement.getEndereco())
							.getDadosRecebidos());
			System.out.println("************************************");

			for (Filme filme : cinemaElement.getFilmes()) {
				System.out.println("---------------------------------");
				System.out.println("FILME: " + filme.getNome());
				System.out.println("---------------------------------");

				for (String hr : ((FilmeCartaz) filme).getHorarios()) {
					System.out.println("HORARIO: " + hr);
				}
			}
		}
	}
	
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		Cinema cinema = cinemas.get(position);
//		ArrayAdapter<Filme> filmesAdapter = new ArrayAdapter<Filme>(
//				this, android.R.layout.simple_list_item_1, cinema.getFilmes());
//		setListAdapter(filmesAdapter);
//		
//		super.onListItemClick(l, v, position, id);
//	}

	private List<CinemaElementView> recuperaLista() {
		String response = fazRequest("http://192.168.1.9:8080/cineserver/cinemas/");

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Cinema.class, new CinemaElementAdapter())
				.registerTypeAdapter(Endereco.class,
						new EnderecoResumoAdapter())
				.registerTypeAdapter(Filme.class, new FilmeCartazAdapter())
				.create();

		Type collectionType = new TypeToken<ArrayList<CinemaElementView>>() {
		}.getType();
		List<CinemaElementView> cinemas = gson.fromJson(response,
				collectionType);
		return cinemas;
	}

	private class CinemaElementAdapter implements
			JsonDeserializer<CinemaElementView> {
		@Override
		public CinemaElementView deserialize(JsonElement json, Type t,
				JsonDeserializationContext context) throws JsonParseException {
			return context.deserialize(json.getAsJsonObject(),
					CinemaElementView.class);
		}
	}

	private class EnderecoResumoAdapter implements
			JsonDeserializer<EnderecoResumoView> {
		public EnderecoResumoView deserialize(JsonElement json, Type t,
				JsonDeserializationContext context) throws JsonParseException {
			return context.deserialize(json.getAsJsonObject(),
					EnderecoResumoView.class);
		}
	}

	private class FilmeCartazAdapter implements JsonDeserializer<FilmeCartazView> {
		public FilmeCartazView deserialize(JsonElement json, Type t,
				JsonDeserializationContext context) throws JsonParseException {
			return context.deserialize(json.getAsJsonObject(),
					FilmeCartazView.class);
		}

	}

	private String fazRequest(String enderecoUrl) {
		HttpURLConnection con = null;
		URL url = null;
		String response = null;

		try {
			url = new URL(enderecoUrl);
			con = (HttpURLConnection) url.openConnection();
			InputStream is = con.getInputStream();
			response = leituraDoStream(is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;

	}

	private String leituraDoStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();

		reader = new BufferedReader(new InputStreamReader(in));
		String linha = null;

		try {
			while ((linha = reader.readLine()) != null) {
				builder.append(linha + "\n");
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
