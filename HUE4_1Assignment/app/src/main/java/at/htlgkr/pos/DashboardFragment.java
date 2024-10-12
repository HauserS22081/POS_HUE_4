package at.htlgkr.pos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.htlgkr.pos.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private final List<TextView> values = new ArrayList<>();
    private final List<TextView> units = new ArrayList<>();
    private final List<Button> leftButtons = new ArrayList<>();
    private final List<Button> rightButtons = new ArrayList<>();
    private final Dashboard dashboard = new Dashboard();


    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        addToLists();

        setButtonTexts();

        for (Button leftButton : leftButtons) {
            leftButton.setOnClickListener(button -> {
                dashboard.setLeftSwitch(true);
                setTexts(button);
            });
        }

        for (Button rightButton : rightButtons) {
            rightButton.setOnClickListener(button -> {
                dashboard.setLeftSwitch(false);
                setTexts(button);
            });
        }

        setInitialTexts();

        return binding.getRoot();
    }

    private void setInitialTexts() {
        List<String> initialTexts = dashboard.getInitialTexts();

        for (int i = 0; i < initialTexts.size(); i++) {
            values.get(i).setText(initialTexts.get(i).split(" ")[0]);

            String unit = initialTexts.get(i).split(" ")[1];

            if (i == 3 && !("am".equals(unit)) && !("pm".equals(unit))) unit = " ";

            units.get(i).setText(unit);
        }
    }

    private void setTexts(View button) {
        String type = String.valueOf(button.getContentDescription());
        String widgetText;

        switch (type) {
            case "temperature":
                widgetText = dashboard.displayableTemperature();
                values.get(0).setText(widgetText.split(" ")[0]);
                units.get(0).setText(widgetText.split(" ")[1]);
                break;
            case "pressure":
                widgetText = dashboard.displayablePressure();
                values.get(1).setText(widgetText.split(" ")[0]);
                units.get(1).setText(widgetText.split(" ")[1]);
                break;
            case "speed":
                widgetText = dashboard.displayableSpeed();
                values.get(2).setText(widgetText.split(" ")[0]);
                units.get(2).setText(widgetText.split(" ")[1]);
                break;
            case "time":
                widgetText = dashboard.displayableTime();
                values.get(3).setText(widgetText.split(" ")[0]);

                String unit = widgetText.split(" ")[1];
                if ("_".equals(unit)) unit = "";
                units.get(3).setText(unit);
                break;
            default:
                System.out.println("Error with ButtonDescriptions");
                break;
        }
    }

    private void setButtonTexts() {
        for (Button leftButton : leftButtons) {
            leftButton.setText("<");
        }

        for (Button rightButton : rightButtons) {
            rightButton.setText(">");
        }
    }

    private void addToLists() {
        values.add(binding.tvTemperatureValue);
        values.add(binding.tvPressureValue);
        values.add(binding.tvSpeedValue);
        values.add(binding.tvTimeValue);

        units.add(binding.tvTemperatureUnit);
        units.add(binding.tvPressureUnit);
        units.add(binding.tvSpeedUnit);
        units.add(binding.tvTimeUnit);

        leftButtons.add(binding.btTemperatureLeft);
        leftButtons.add(binding.btPressureLeft);
        leftButtons.add(binding.btSpeedLeft);
        leftButtons.add(binding.btTimeLeft);

        rightButtons.add(binding.btTemperatureRight);
        rightButtons.add(binding.btPressureRight);
        rightButtons.add(binding.btSpeedRight);
        rightButtons.add(binding.btTimeRight);
    }
}