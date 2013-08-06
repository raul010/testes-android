package com.example.sqlite;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	private NotesDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		dao = new NotesDao(this);
		dao.open();
		
	}

	@Override
	protected void onResume() {
		dao.open();
		super.onResume();
		
		List<Note> notes = dao.getAll();

		ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,
				android.R.layout.simple_list_item_1, notes);
		
		setListAdapter(adapter);
	}

	@Override
	protected void onPause() {
		dao.close();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.add_note) {
			Intent intent = new Intent(this, AddNoteActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
