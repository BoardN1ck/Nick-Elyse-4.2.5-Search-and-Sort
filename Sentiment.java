/*Anne of Green Gables, L.M. Montgomery, 4.25
The Secret Garden, Frances Hodgson Burnett, 4.13
A Little Princess, Frances Hodgson Burnett, 4.20
The Wonderful Wizard of Oz, L. Frank Baum, 3.98
Peter Pan, J.M. Barrie, 4.08
The Railway Children, E. Nesbit, 4.00
The Tale of Peter Rabbit, Beatrix Potter, 4.17
Pollyanna, Eleanor H. Porter, 3.97
 /* Project 4.1.5
 *
 * A class to manage the sentiments from the consumer lab
 */
public class Sentiment
{
  private String phrase;
  private double value;

  /**
   * The constructor for a Book. All Books require
   * a title, an author, and a rating. 
   */
  public Sentiment(String phrase, double value)
  {
    this.phrase = phrase;
    this.value = value;
  }
  
  /** 
   * Gets the phrase of the sentiment
   * 
   * @return the sentiment phrase
   * */
  public String getPhrase()
  {
    return this.phrase;
  }

    /** 
   * Gets the phrase of the sentiment
   * 
   * @return the Book's title
   * */
  public double getValue()
  {
    return this.value;
  }
    
}