package at.htlgkr.pos;

public class Dashboard implements IDashboard{

    private final int TEMPERATURESTARTVALUE = 20;
    private final int PRESSURESTARTVALUE = 80;
    private final int VELOCITYSTARTVALUE = 42;
    private final String TIMESTARTVALUE = "22:23";
    private final String TEMPERATURESTARTUNIT = "°C";
    private final String PRESSURESTARTUNIT = "Pa";
    private final String VELOCITYSTARTUNIT = "m/s";
    private final String TIMESTARTUNIT = "hh:mm:ss";

    private final Widget temperatureWidget = new Widget(TEMPERATURESTARTVALUE, TEMPERATURESTARTUNIT);
    private final Widget pressureWidget = new Widget(PRESSURESTARTVALUE, PRESSURESTARTUNIT);
    private final Widget velocityWidget = new Widget(VELOCITYSTARTVALUE, VELOCITYSTARTUNIT);
    private final TimeWidget timeWidget = new TimeWidget(TIMESTARTVALUE, TIMESTARTUNIT);

    public Dashboard() {
        addTemperatureCalculations();
        addPressureCalculations();
        addVelocityCalculations();
        addTimeCaclulations();
    }

    @Override
    public String displayableTemperature() {
        return "";
    }

    @Override
    public String displayablePressure() {
        return "";
    }

    @Override
    public String displayableSpeed() {
        return "";
    }

    @Override
    public String displayableTime() {
        return "";
    }

    public void removeTemperatureCalculation(String unit) {
        temperatureWidget.removeConverter(unit);
    }

    public void addTemperatureCalculation(Converter converter, String unit) {

    }

    private void addTemperatureCalculations() {
        int startValue = temperatureWidget.getStartValue();

        addTemperatureCalculation(value -> startValue, "°C");
        addTemperatureCalculation(value -> (int) (startValue + 273.15), "K");
        addTemperatureCalculation(value -> (int) (startValue * 9 / 5) + 32, "°F");

        // °F -> °C
//        temperatureWidget.addConverter(value -> (value - 32) * 5 / 9, "°C");

        // °C -> K
//        temperatureWidget.addConverter(value -> (int) (value + 273.15), "K");

        // K -> °F
//        temperatureWidget.addConverter(value -> (int) ((value - 273.15) * 9 / 5 + 32), "°F");
    }

    private void addPressureCalculations() {

        int startValue = pressureWidget.getStartValue();


        pressureWidget.addConverter(() -> startValue, "Pa");

        pressureWidget.addConverter(() -> startValue / 100000, "bar");
        
        // bar -> Pa
        pressureWidget.addConverter(value -> value * 100000, "Pa");

        // Pa -> bar
        pressureWidget.addConverter(value -> startValue / 100000, "bar");
    }

    private void addVelocityCalculations() {
        // mph -> m/s
        velocityWidget.addConverter(value -> (int) (value / 2.237), "m/s");

        // m/s -> km/h
        velocityWidget.addConverter(value -> (int) (value * 3.6), "km/h");

        // km/h -> mph
        velocityWidget.addConverter(value -> (int) (value / 1.609), "km/h");
    }

    private void addTimeCaclulations() {
        // am/pm -> hh:mm:ss
        timeWidget.addConverter(value -> timeWidget.getStartValue(), "hh:mm:ss");

        // hh:mm:ss -> hh:mm
        timeWidget.addConverter(value -> {
            String[] parts = value.split(":");
            return parts[0] + ":" + parts[1];
        }, "hh:mm");

        // hh:mm -> am/pm
        timeWidget.addConverter(value -> {
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
}
