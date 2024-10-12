package at.htlgkr.pos;

import java.util.ArrayList;
import java.util.List;

public class Dashboard implements IDashboard{

    private final int TEMPERATURESTARTVALUE = 20;
    private final int PRESSURESTARTVALUE = 80000000;
    private final int SPEEDSTARTVALUE = 42;
    private final String TIMESTARTVALUE = "5:23:49";
    private final String TEMPERATURESTARTUNIT = "°C";
    private final String PRESSURESTARTUNIT = "Pa";
    private final String SPEEDSTARTUNIT = "m/s";
    private final String TIMESTARTUNIT = "hh:mm:ss";

    private final Widget temperatureWidget = new Widget(TEMPERATURESTARTVALUE, TEMPERATURESTARTUNIT);
    private final Widget pressureWidget = new Widget(PRESSURESTARTVALUE, PRESSURESTARTUNIT);
    private final Widget speedWidget = new Widget(SPEEDSTARTVALUE, SPEEDSTARTUNIT);
    private final TimeWidget timeWidget = new TimeWidget(TIMESTARTVALUE, TIMESTARTUNIT);

    private boolean isLeftSwitch = false;

    public Dashboard() {
        addTemperatureCalculations();
        addPressureCalculations();
        addSpeedCalculations();
        addTimeCaclulations();
    }

    public List<String> getInitialTexts() {
        List<String> initialTexts = new ArrayList<>();

        initialTexts.add(temperatureWidget.getValue() + " " + temperatureWidget.getUnit());
        initialTexts.add(pressureWidget.getValue() + " " + pressureWidget.getUnit());
        initialTexts.add(speedWidget.getValue() + " " + speedWidget.getUnit());
        initialTexts.add(timeWidget.getValue() + " " + timeWidget.getUnit());

        return initialTexts;
    }

    public void setLeftSwitch(boolean leftSwitch) {
        isLeftSwitch = leftSwitch;
    }

    @Override
    public String displayableTemperature() {
        if (isLeftSwitch) {
            temperatureWidget.convertLeft();
        } else {
            temperatureWidget.convertRight(false);
        }
        return temperatureWidget.getValue() + " " + temperatureWidget.getUnit();
    }

    @Override
    public String displayablePressure() {
        if (isLeftSwitch) {
            pressureWidget.convertLeft();
        } else {
            pressureWidget.convertRight(false);
        }

        return pressureWidget.getValue() + " " + pressureWidget.getUnit();
    }

    @Override
    public String displayableSpeed() {
        if (isLeftSwitch) {
            speedWidget.convertLeft();
        } else {
            speedWidget.convertRight(false);
        }

        return speedWidget.getValue() + " " + speedWidget.getUnit();
    }

    @Override
    public String displayableTime() {
        if (isLeftSwitch) {
            timeWidget.convertLeft();
        } else {
            timeWidget.convertRight(false);
        }

        String unit = timeWidget.getUnit();
        if (!("am".equals(unit)) && !("pm".equals(unit))) unit = "_";

        return timeWidget.getValue() + " " + unit;
    }

    private void addTemperatureCalculations() {
        // value ist immer in °C

        addTemperatureCalculation(value -> value, "°C");
        addTemperatureCalculation(value -> (int) (value + 273.15), "K");
        addTemperatureCalculation(value -> value * 9 / 5 + 32, "°F");
    }

    private void addPressureCalculations() {
        // value ist immer in Pa

        addPressureCalculation(value -> value, "Pa");
        addPressureCalculation(value -> value / 100000, "bar");
    }

    private void addSpeedCalculations() {
        // value ist immer in m/s

        addSpeedCalculation(value -> value, "m/s");
        addSpeedCalculation(value -> (int) (value * 3.6), "km/h");
        addSpeedCalculation(value -> (int) (value * 2.237), "mph");
    }

    private void addTimeCaclulations() {
        // value ist immer in hh:mm:ss

        addTimeCalculation(value -> value, "hh:mm:ss");

        addTimeCalculation(value -> {
            String[] parts = value.split(":");
            return parts[0] + ":" + parts[1];
        }, "hh:mm");

        addTimeCalculation(value -> {
            String[] parts = value.split(":");
            int hours = Integer.parseInt(parts[0]);
            if (hours > 12) {
                hours = hours - 12;
            }

            if (hours < 1) {
                return "12" + ":" + parts[1];
            }
            return hours + ":" + parts[1];

        }, Integer.parseInt(timeWidget.getValue().split(":")[0]) < 12 ? "am" : "pm");
    }

    public void removeTemperatureCalculation(String unit) {
        temperatureWidget.removeConverter(unit);
    }

    public void addTemperatureCalculation(Converter converter, String unit) {
        // ausgehend von °C
        temperatureWidget.addConverter(converter, unit);
    }

    public void removePressureCalculations(String unit) {
        pressureWidget.removeConverter(unit);
    }

    public void addPressureCalculation(Converter converter, String unit) {
        // ausgehend von Pa
        pressureWidget.addConverter(converter, unit);
    }

    public void removeSpeedCalculation(String unit) {
        speedWidget.removeConverter(unit);
    }

    public void addSpeedCalculation(Converter converter, String unit) {
        // ausgehend von m/s
        speedWidget.addConverter(converter, unit);
    }

    public void removeTimeCalculation(String unit) {
        timeWidget.removeConverter(unit);
    }

    public void addTimeCalculation(TimeConverter converter, String unit) {
        // ausgehend von hh:mm:ss
        timeWidget.addConverter(converter, unit);
    }
}
