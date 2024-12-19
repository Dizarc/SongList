//Code By Omer-Faruk Yildiz ICE20390038.

public class Song {
    private String code;
    private String title;
    private int year;
    private int length;
    private String artist;
    private String genre;

    public Song(){

    }
    public Song(String code,String title,int year,int length, String artist,String genre){
        this.code = code;
        this.title = title;
        this.year = year;
       this.length=length;
        this.artist = artist;
        this.genre = genre;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCreator(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public String getCreator() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

}
