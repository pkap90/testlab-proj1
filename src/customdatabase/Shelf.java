/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package customdatabase;

import java.util.*;

public class Shelf {
   private int id;
   private String name;
   private String location;
   private Set books;

   public Shelf() {}
   public Shelf(String name, String loc) {
      this.name = name;
      this.location = loc;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getLocation() {
      return location;
   }
   public void setLocation(String loc) {
      this.location = loc;
   }
   public Set getBooks() {
      return books;
   }
   public void setBooks(Set books) {
      this.books = books;
   }
}