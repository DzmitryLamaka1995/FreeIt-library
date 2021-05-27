package by.lamaka.library.bean;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private Genre genre;
    private Author author;
    private LocalDate dateCreated;

    public Book() {
        dateCreated = LocalDate.now();
    }

    public Book( String title, String genre, Author author, LocalDate dateCreated) {
        this.title = title;
        this.genre = Genre.valueOf(genre.toUpperCase());
        this.author = author;
        this.dateCreated = dateCreated;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = Genre.valueOf(genre.toUpperCase());
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && genre == book.genre && Objects.equals(author, book.author) && Objects.equals(dateCreated, book.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, author, dateCreated);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", author=" + author +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
