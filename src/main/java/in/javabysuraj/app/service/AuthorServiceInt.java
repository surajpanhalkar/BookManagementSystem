package in.javabysuraj.app.service;

import in.javabysuraj.app.DTO.AuthorRequestDTO;
import in.javabysuraj.app.DTO.AuthorResponseDTO;

import java.util.List;

public interface AuthorServiceInt {
    AuthorResponseDTO createAuthor(AuthorRequestDTO dto);

    List<AuthorResponseDTO> getAllAuthors();

    AuthorResponseDTO getAuthorById(Long id);

   AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO dto);
}
