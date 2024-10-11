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
        leftButtonsOnClickListener();
        rightButtonsOnClickListener();

        return binding.getRoot();
    }

    private void leftButtonsOnClickListener() {
        for (Button leftButton : leftButtons) {
            leftButton.setOnClickListener(button -> {

            });
        }
    }


    private void rightButtonsOnClickListener() {
        for (Button rightButton : rightButtons) {
            rightButton.setOnClickListener(button -> {

            });
        }

    }

    private void addToLists() {
        values.add(binding.tvTemperatureValue);
        values.add(binding.tvPressureValue);
        values.add(binding.tvVelocityValue);
        values.add(binding.tvTimeValue);

        units.add(binding.tvTemperatureUnit);
        units.add(binding.tvPressureUnit);
        units.add(binding.tvVelocityUnit);
        units.add(binding.tvTimeUnit);

        leftButtons.add(binding.btTemperatureLeft);
        leftButtons.add(binding.btPressureLeft);
        leftButtons.add(binding.btVelocityLeft);
        leftButtons.add(binding.btTimeLeft);

        rightButtons.add(binding.btTemperatureRight);
        rightButtons.add(binding.btPressureRight);
        rightButtons.add(binding.btVelocityRight);
        rightButtons.add(binding.btTimeLeft);
    }
}