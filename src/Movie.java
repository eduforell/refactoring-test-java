public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private final int price;
    private final String title;

    public Movie(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return price;
    }
}
