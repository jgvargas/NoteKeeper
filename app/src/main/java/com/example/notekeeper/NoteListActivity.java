package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        // Final to reference on anonomus class item

        // Creates object with reference to list_notes activity
        final ListView listNotes = findViewById(R.id.list_notes);

        //
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        // Adapter that holds noteInfo, using built in Android resource simple_list_item_1
        ArrayAdapter<NoteInfo> adapterNotes = new ArrayAdapter<>( this,
                android.R.layout.simple_list_item_1, notes);

        // populates the list activity
        listNotes.setAdapter(adapterNotes);

        // Handle the user making a selection, or an Item being clicked
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Add to launch NoteActivity when selected
                Intent intent = new Intent(  NoteListActivity.this, MainActivity.class);

                // Get note info that correspodes to a selection
                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                intent.putExtra(MainActivity.NOTE_INFO, note);


                // Launch the activity by passing the intent
                startActivity(intent);
            }
        });

    }

}
