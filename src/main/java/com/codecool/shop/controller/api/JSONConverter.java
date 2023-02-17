package com.codecool.shop.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract interface JSONConverter {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

}
