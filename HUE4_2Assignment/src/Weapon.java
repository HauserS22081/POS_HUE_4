import java.util.Objects;

public class Weapon {
    private String name;
    private int attackPoints;
    private Category category;
    private double weight;
    private int price;
    private final String itemID;

    public Weapon(String name, int attackPoints, Category category, double weight, int price, String itemID) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.category = category;
        this.weight = weight;
        this.price = price;
        this.itemID = itemID;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s | %d attack points | %.1f kg | %d €", itemID, name, category, attackPoints, weight, price);
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return attackPoints == weapon.attackPoints && Double.compare(weight, weapon.weight) == 0 && price == weapon.price && Objects.equals(name, weapon.name) && category == weapon.category && Objects.equals(itemID, weapon.itemID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attackPoints, category, weight, price, itemID);
    }
}
