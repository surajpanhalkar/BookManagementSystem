package in.javabysuraj.app.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AuthorRequestDTO {
    private String name;
    private List<BookRequestDTO> books;
}
