package at.htlgkr.pos;

import java.util.ArrayList;
import java.util.List;

public class Widget {
    private int value;
    private String unit;
    private List<Converter> converterList = new ArrayList<>();

    public Widget(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addConverter(Converter converter) {
        converterList.add(converter);
    }
}
