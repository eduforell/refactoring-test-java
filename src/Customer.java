import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Customer {
    private final String name;
    private final List<MovieRental> rentals;

    public Customer(String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
    }

    public void addRental(MovieRental rental) {
        if (!rentals.contains(rental)) rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    private static Logger getLogger() {
        return Logger.getLogger(Customer.class.getName());
    }

    public String statement() {
        double amount = 0;
        int frequentMemberPoints = 0;
        StringBuilder statementHeader = new StringBuilder("Rentals for " + getName()).append("\n\n");
        statementHeader.append(String.format("%-40.40s %4s %-8s\n", "Movie Title", "Days", "Price"));
        for (MovieRental rental : rentals) {
            double innerAmount = 0;
            switch (rental.getMovie().getPrice()) {
                case Movie.REGULAR:
                    innerAmount += 2;
                    if (rental.getDaysRented() > 2) innerAmount += 1.5 * (rental.getDaysRented() - 2);
                    break;

                case Movie.CHILDREN:
                    innerAmount = 1.5;
                    if (rental.getDaysRented() > 3) innerAmount += 1.5 * (rental.getDaysRented() - 3);
                    break;

                case Movie.NEW_RELEASE:
                    innerAmount = 3 * rental.getDaysRented();
                    break;

                default:
                    getLogger().warning("Movie " + rental.getMovie() + " has wrong movie type");
            }

            if (rental.getMovie().getPrice() == Movie.NEW_RELEASE) frequentMemberPoints += rental.getDaysRented();
            else frequentMemberPoints++;

            statementHeader.append(String.format("%-40.40s %3d %8.2f\n", rental.getMovie().getTitle(), rental.getDaysRented(), innerAmount));
            amount += innerAmount;
        }
        statementHeader.append(String.format("%-44.44s %8.2f\n", "Total Charges", amount));
        statementHeader.append(String.format("%-44.44s %5d\n", "Frequent Member Points earned", frequentMemberPoints));

        return statementHeader.toString();
    }
}
