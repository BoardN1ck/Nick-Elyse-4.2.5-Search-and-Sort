 /*
 * Project 4.1.5
 * 
 * Manage data for search and sort analyses
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Data {
  public static ArrayList<Book> getBooks() { 
    ArrayList<Book> b = new ArrayList<Book>();
    try {
      // Use the file shown in your sidebar
      Scanner sc = new Scanner(new File("books.csv"));
      while (sc.hasNextLine()) {
        String line = sc.nextLine().trim();
        if (line.isEmpty()) continue;
        
        // Split by comma only, then trim any accidental spaces
        String[] tokens = line.split(",");
        b.add(new Book(tokens[0].trim()));
      }
      sc.close();
    } catch (Exception e) { 
        System.out.println("Error reading books: " + e); 
    }
    return b;
  }

  public static ArrayList<Sentiment> getSentiments() {
    ArrayList<Sentiment> s = new ArrayList<Sentiment>();
    try {
      Scanner sc = new Scanner(new File("sentiments.csv"));
      while (sc.hasNextLine()) {
        String line = sc.nextLine().trim();
        if (line.isEmpty()) continue;
        String[] tokens = line.split(",");
        s.add(new Sentiment(tokens[0].trim(), Double.parseDouble(tokens[1].trim())));
      }
      sc.close();
    } catch (Exception e) { 
        System.out.println("Error reading sentiments: " + e); 
    }
    return s;
  }

  public static void showBooks(ArrayList<Book> sortedBooks, String title) {
    System.out.println(title);
    for (Book b : sortedBooks)
      System.out.println("    " + b.getTitle());
  }
}