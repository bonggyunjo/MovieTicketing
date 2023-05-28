/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/* 
    ��ȭ������ ���� ĸ��ȭ 
    ���� ���� ����
 */

public class MovieGenreDto {

    private String movie; // �̸�
    private String genre;
   

    public String getMovie() {
        return movie;
    }
 
    public String getGenre() {
        return genre;
    }

   
    public MovieGenreDto() {
        super();
    }
       public void setGenre(String genre){
        this.genre=genre;
    }
          public void setMovie(String movie){
        this.movie=movie;
    }
                    //���� ����
    private MovieGenreDto(Builder builder) {
        super();
        this.movie = builder.movie;
        this.genre =  builder.genre;
       
    }

    public static class Builder {

        private String movie;
        private String genre;
      

        public Builder() {
        }

        public Builder setMovie(String movie) {
            this.movie = movie;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

       public MovieGenreDto build() {
          return new MovieGenreDto(this);
        }

    }
}


