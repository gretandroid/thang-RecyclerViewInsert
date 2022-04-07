package com.example.recyclerviewinsert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerviewinsert.controller.DataModel;
import com.example.recyclerviewinsert.controller.PersonDao;
import com.example.recyclerviewinsert.model.Person;
import com.example.recyclerviewinsert.recyclerview.PersonAdapter;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView personRecyclerView;
    EditText surnameText;
    EditText nameText;

    DataModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new ViewModelProvider(this).get(DataModel.class);

        // init fake data for model
//        model.addPerson(Arrays.asList(
//                new Person("PHAM", "Thang"),
//                new Person("PHAM", "Alexandre"),
//                new Person("PHAM", "Louis"),
//                new Person("TRINH", "Mai")
//        ));

        // init UI
        surnameText = findViewById(R.id.surnameText);
        nameText = findViewById(R.id.nameText);

        // init recyclerView
        personRecyclerView = findViewById(R.id.personRecyclerView);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PersonAdapter adapter = new PersonAdapter(Collections.unmodifiableList((model.getPersons().getValue())));
        personRecyclerView.setAdapter(adapter);


        // register live person list hook
        model.getPersons().observe(this, personList -> {
            // Demande MAJ personRecyclerView
            adapter.notifyItemRangeInserted(personList.size() - 1, 1);
        });

    }

    public void onClickAddButton(View view) {
        String surname = surnameText.getText().toString();
        String name = nameText.getText().toString();
        Person person = new Person(surname, name);
        model.addPerson(person);

        // clear UI
        surnameText.setText("");
        nameText.setText("");

        Toast.makeText(this, model.getLastPerson() + " was added", Toast.LENGTH_LONG).show();
    }
}