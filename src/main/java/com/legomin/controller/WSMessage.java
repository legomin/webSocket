package com.legomin.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Виталий on 10.02.2016.
 */
public class WSMessage {
    private TypeMessage type;
    private String seqence_id;
    private Map<String,String> data;

    public WSMessage() {
        data = new HashMap<>();
    }

    public TypeMessage getType() {
        return type;
    }

    public void setType(TypeMessage type) {
        this.type = type;
    }

    public String getSeqence_id() {
        return seqence_id;
    }

    public void setSeqence_id(String seqence_id) {
        this.seqence_id = seqence_id;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }


}
