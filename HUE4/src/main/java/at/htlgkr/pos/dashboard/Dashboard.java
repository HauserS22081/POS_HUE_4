package at.htlgkr.pos.dashboard;

import java.util.ArrayList;
import java.util.List;

public class Dashboard implements IDashboard{

    private List<List<Widget>> widgets = new ArrayList<>();

    public Dashboard() {
        implementTemperature();
        implementPressure();
        implementVelocity();
        implementTime();
    }

    private void implementTime() {
    }

    private void implementVelocity() {

    }

    private void implementPressure() {

    }

    private void implementTemperature() {


    }

    @Override
    public String displayableTemperature() {
        return null;
    }

    @Override
    public String displayablePressure() {
        return null;
    }

    @Override
    public String displayableSpeed() {
        return null;
    }

    @Override
    public String displayableTime() {
        return null;
    }
}
