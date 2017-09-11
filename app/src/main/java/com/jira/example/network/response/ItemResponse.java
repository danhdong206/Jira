package com.jira.example.network.response;

import com.google.gson.annotations.SerializedName;
import com.jira.example.model.Item;

import java.util.List;

/**
 * Created by admin on 9/11/17.
 */

public class ItemResponse {
    @SerializedName("Items")
    private List<Item> items;
}
