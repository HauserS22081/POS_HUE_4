package at.htlgkr.pos;

public class Dashboard implements IDashboard{
    private Widget temperatureWidget = new Widget(20);
    private Widget pressureWidget = new Widget(80);
    private Widget velocityWidget = new Widget(42);
    private TimeWidget timeWidget = new TimeWidget("22:23");

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
}
