package com.example.recyclerviewinsert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.recyclerviewinsert.controller.DataModel;
import com.example.recyclerviewinsert.controller.PersonDao;
import com.example.recyclerviewinsert.model.Person;
import com.example.recyclerviewinsert.recyclerview.PersonAdapter;

import java.util.Arrays;

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

        // init Dao with model
        PersonDao.useWithModel(model);

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

        personRecyclerView = findViewById(R.id.personRecyclerView);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PersonAdapter adapter = new PersonAdapter(model);
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
        model.addPerson(new Person(surname, name));
    }
}