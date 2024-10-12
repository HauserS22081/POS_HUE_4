package at.htlgkr.pos;

import java.util.HashMap;
import java.util.Map;

public class Widget {
    private int value;
    private String unit;
    private int startValue;
    private Map<String, Converter> converterMap = new HashMap<>();

    public Widget(int value, String unit) {
        this.value = value;
        startValue = value;
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

    public void removeConverter(String unit) {
        converterMap.remove(unit);
    }

    public void convertRight(boolean nextIterationIsWanted) {
        for (Map.Entry<String, Converter> entry : converterMap.entrySet()) {
            if (nextIterationIsWanted) {
                value = entry.getValue().convert(startValue);
                unit = entry.getKey();
                return;
            }

            if (entry.getKey().equals(unit)) {
                nextIterationIsWanted = true;
            }
        }

        convertRight(nextIterationIsWanted);
    }

    public void convertLeft() {
        String tempUnit = "";
        Converter converter = null;

        for (Map.Entry<String, Converter> entry : converterMap.entrySet()) {
            tempUnit = entry.getKey();
            converter = entry.getValue();
        }

        for (Map.Entry<String, Converter> entry : converterMap.entrySet()) {
            if (entry.getKey().equals(this.unit)) {
                value = converter.convert(startValue);
                this.unit = tempUnit;
                return;
            }

            tempUnit = entry.getKey();
            converter = entry.getValue();
        }
    }
}
