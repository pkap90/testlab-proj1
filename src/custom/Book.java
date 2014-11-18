package custom;

public class Book {
   private int id;
   private String title;
   private String firstAuthor;
   private int publishYear;

   public Book() {}
   public Book(String title, String first_author) {
      this.title = title;
      this.firstAuthor = first_author;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getFirstAuthor() {
     return firstAuthor;
   }
   public void setFirstAuthor(String first_author) {
     this.firstAuthor = first_author;
   }
   public int getPublishYear() {
     return publishYear;
   }
   public void setPublishYear(int publYear) {
     this.publishYear = publYear;
   }
   @Override
   public boolean equals(Object obj) {
      if (obj != null && obj instanceof Book) {
        Book other = (Book)obj;
        return this.id == other.getId() && this.title.equals(other.getTitle())
            && this.firstAuthor.equals(other.getFirstAuthor())
            && this.publishYear  == other.getPublishYear();
      }
      return false;
   }
   @Override
   public int hashCode() {
      return id + 13 * title.hashCode() + 31 * firstAuthor.hashCode()
          + 97 * publishYear;
   }
}