package com.example.recyclerviewinsert.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewinsert.R;
import com.example.recyclerviewinsert.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    List<Person> persons;

    public PersonAdapter(List<Person> model) {
        this.persons = model;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater permetre de creer (inflater) des views correspondants
        // aux fichiers xml
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.getSurnameTextView().setText(persons.get(position).getSurname());
        holder.getNameTextView().setText(persons.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView surnameTextView;
        TextView nameTextView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            surnameTextView = itemView.findViewById(R.id.surnameTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }

        public TextView getSurnameTextView() {
            return surnameTextView;
        }

        public TextView getNameTextView() {
            return nameTextView;
        }
    }
}
