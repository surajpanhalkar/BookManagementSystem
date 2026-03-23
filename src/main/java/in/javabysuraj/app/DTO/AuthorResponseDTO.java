package in.javabysuraj.app.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private List<BookResponseDTO> books;
}
