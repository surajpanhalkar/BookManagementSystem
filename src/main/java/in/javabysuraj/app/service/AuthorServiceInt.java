package in.javabysuraj.app.service;

import in.javabysuraj.app.DTO.AuthorRequestDTO;
import in.javabysuraj.app.DTO.AuthorResponseDTO;

import java.util.List;

public interface AuthorServiceInt {
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto);
//    public List<AuthorResponseDTO> getAllAuthors();
}
