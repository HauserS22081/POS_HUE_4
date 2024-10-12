import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tests implements IWeapons{
    private static List<Weapon> weapons;

    public void test(String csvFilePath) {
        weapons = readInFile(csvFilePath);

        Player player = createPlayer(weapons);

        printPlayer(player);

        System.out.println("\n\nTest Implemented Methods");

        testImplementedMethod(player);
    }

    private void testImplementedMethod(Player player) {
        System.out.println("\nWeapons name start with '0'");
        findWhereItemIDStartsWith("0").forEach(System.out::println);

        System.out.println("\nWeapons with itemId 'UXRTWQ'");
        System.out.println(findWithItemID("UXRTWQ"));

        System.out.println("\nWeapons with category 'dagger'");
        findWithCategory("dagger").forEach(System.out::println);

        System.out.println("\nSum of price");
        System.out.println(sumPrice());

        System.out.println("\nAverage attack points");
        System.out.println(averageAttackPoints());

        System.out.println("\nWeapon with most attack points");
        System.out.println(findWithMostAttackPoints());

        System.out.println("\nWeapons with weight under '18'");
        findWeightUnder(18).forEach(System.out::println);

        System.out.println("\nCarrayable weapons for player");
        carryAbleWeapons(player).forEach(System.out::println);
    }

    @Override
    public List<Weapon> findWhereItemIDStartsWith(String searchString) {
        return weapons.stream()
                .filter(weapon -> weapon.getItemID().startsWith(searchString))
                .collect(Collectors.toList());
    }

    @Override
    public Weapon findWithItemID(String itemID) {
        return weapons.stream()
                .filter(weapon -> weapon.getItemID().equals(itemID))
                .toList()
                .getFirst();
    }

    @Override
    public List<Weapon> findWithCategory(String category) {
        return weapons.stream()
                .filter(weapon -> weapon.getCategory().toString().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public int sumPrice() {
        return weapons.stream()
                .mapToInt(Weapon::getPrice)
                .sum();
    }

    @Override
    public int averageAttackPoints() {
        return weapons.stream()
                .mapToInt(Weapon::getAttackPoints)
                .sum() / weapons.size();
    }

    @Override
    public Weapon findWithMostAttackPoints() {
        return weapons.stream()
                .max(Comparator.comparingInt(Weapon::getAttackPoints))
                .get();
    }

    @Override
    public List<Weapon> findWeightUnder(float weight) {
        return weapons.stream()
                .filter(weapon -> weapon.getWeight() < weight)
                .collect(Collectors.toList());
    }

    @Override
    public List<Weapon> carryAbleWeapons(Player player) {
        double currently = player.getWeapons().stream()
                .mapToDouble(Weapon::getWeight)
                .sum();

        return weapons.stream()
                .filter(weapon -> weapon.getWeight() < player.getMaxWeight() - currently)
                .collect(Collectors.toList());
    }



    private void printPlayer(Player player) {
        PlayerToString playerToString = p -> {
            String output = String.format("%s - %.1f kg / %.1f kg", p.getName(), p.getCarrying(), p.getMaxWeight());

            for (Weapon weapon : p.getWeapons()) {
                output += String.format("\n%s", weapon.toString());
            }

            return output;
        };

        System.out.println(playerToString.playerToString(player));
    }

    private Player createPlayer(List<Weapon> weapons) {
        List<Weapon> playerWeapons = List.of(weapons.get(0), weapons.get(3), weapons.get(7));
        double weaponsWeigth = playerWeapons.stream()
                .mapToDouble(Weapon::getWeight)
                .sum();

        return new Player("temp", 40.4, weaponsWeigth, playerWeapons);
    }

    private List<Weapon> readInFile(String csvFilePath) {
        try {
            return Files.lines(Path.of(csvFilePath))
                    .skip(1)
                    .map(this::getWeapon)
                    .filter(weapon -> weapon != null)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error in readInFile");
        }

        return null;
    }

    private Weapon getWeapon(String line) {
        String[] parts = line.split(";");

        if(parts.length != 6) return null;
        for (String part : parts) {
            if (part.isEmpty()) return null;
        }

        String weightStrings[] = parts[3].split(",");
        double weight = Double.parseDouble(weightStrings[0]) + (weightStrings.length != 2 ? 0 : Integer.parseInt(weightStrings[1]) * 0.1);

        return new Weapon(parts[0], Integer.parseInt(parts[1]), Category.valueOf(line.split(";")[2]), weight,
                Integer.parseInt(parts[4]), parts[5]);
    }
}
