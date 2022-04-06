package com.example.recyclerviewinsert.controller;

public class PersonDao {
    static PersonDao instance;
    DataModel model;

    private PersonDao(DataModel model) {
        this.model = model;
    }

    public static PersonDao getInstance() {
        return instance;
    }
    public static PersonDao useWithModel(DataModel model) {
        if (instance == null) {
            instance = new PersonDao(model);
        }
        else {
            instance.model = model;
        }

        return instance;
    }


}
