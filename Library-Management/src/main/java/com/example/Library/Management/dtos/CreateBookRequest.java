package com.example.Library.Management.dtos;

import com.example.Library.Management.models.Author;
import com.example.Library.Management.models.Book;
import com.example.Library.Management.models.Genre;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorEmail;

    public Book to() {

        Author author = Author.builder()
                .name(this.authorName)
                .email(this.authorEmail)
                .build();
        return Book.builder()
                .name(this.name)
                .genre(this.genre)
                .my_author(author)
                .build();
    }


}
