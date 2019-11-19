package com.bionagro.bionintelligence.data.pojo;

import java.util.List;

public class MinMaxListPojo {
    public List<MinMaxPojo> value;

    public MinMaxListPojo(List<MinMaxPojo> value) {
        this.value = value;
    }

    public List<MinMaxPojo> getValue() {
        return value;
    }

    public void setValue(List<MinMaxPojo> value) {
        this.value = value;
    }
}
