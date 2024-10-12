package at.htlgkr.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeWidget {
    private String value;
    private String unit;
    private String startValue;
    private Map<String, TimeConverter> converterMap = new HashMap<>();

    public TimeWidget(String value, String unit) {
        this.value = value;
        startValue = value;
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addConverter(TimeConverter converter, String unit) {
        converterMap.put(unit, converter);
    }

    public void removeConverter(String unit) {
        converterMap.remove(unit);
    }

    public void convertRight(boolean nextIterationIsWanted) {
        for (Map.Entry<String, TimeConverter> entry : converterMap.entrySet()) {
            if (entry.getKey().equals(unit)) {
                nextIterationIsWanted = true;
            }
            if (nextIterationIsWanted) {
                value = entry.getValue().convert(startValue);
                unit = entry.getKey();
                nextIterationIsWanted = false;
            }
        }

        if (nextIterationIsWanted) {
            convertRight(nextIterationIsWanted);
        }
    }

    public void convertLeft() {
        String unit;
        TimeConverter converter;

        for (Map.Entry<String, TimeConverter> entry : converterMap.entrySet()) {
            unit = entry.getKey();
            converter = entry.getValue();

            if (unit.equals(this.unit)) {
                value = converter.convert(startValue);
                this.unit = unit;
                return;
            }
        }
    }
}
