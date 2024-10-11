package at.htlgkr.pos;

import java.util.ArrayList;
import java.util.List;

public class TimeWidget {
    private String value;
    private String unit;
    private List<Converter> converterList = new ArrayList<>();

    public TimeWidget(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addConverter(Converter converter) {
        converterList.add(converter);
    }
}
