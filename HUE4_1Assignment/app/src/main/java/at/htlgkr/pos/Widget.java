package at.htlgkr.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Widget {
    private int value;
    private String unit;
    private int startValue;
    private Map<String, Converter> converterMap = new HashMap<>();

    public Widget(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addConverter(Converter converter, String unit) {
        converterMap.put(unit, converter);
    }
}
