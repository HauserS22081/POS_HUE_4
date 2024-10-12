import java.util.List;
import java.util.Objects;

public class Player {
    private String name;
    private double maxWeight;
    private double carrying;
    private List<Weapon> weapons;

    public Player(String name, double maxWeight, double carrying, List<Weapon> weapons) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.carrying = carrying;
        this.weapons = weapons;
    }

    public String getName() {
        return name;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getCarrying() {
        return carrying;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Double.compare(maxWeight, player.maxWeight) == 0 && Double.compare(carrying, player.carrying) == 0 && Objects.equals(name, player.name) && Objects.equals(weapons, player.weapons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxWeight, carrying, weapons);
    }
}
