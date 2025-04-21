import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    Movie[] movies = new Movie[] {
            new Movie("You've Got Mail", Movie.REGULAR),
            new Movie("Matrix", Movie.REGULAR),
            new Movie("Cars", Movie.CHILDREN),
            new Movie("Fast & Furious X", Movie.NEW_RELEASE)
    };

    Map<Movie, Integer> daysRented = new HashMap<>();
    daysRented.put(movies[0], 3);
    daysRented.put(movies[1], 1);
    daysRented.put(movies[2], 8);
    daysRented.put(movies[3], 7);

    Customer customer = new Customer("Eduardo Forell");

    for (Movie movie : movies) {
      Integer daysRent = daysRented.get(movie);
      if (daysRent != null) {
        customer.addRental(new MovieRental(movie, daysRent));
      } else {
        System.out.println("Rental duration not found");
      }
    }
    
    System.out.println(customer.statement());
  }
}
