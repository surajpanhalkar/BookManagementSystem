package in.javabysuraj.app.service;

import in.javabysuraj.app.DTO.AuthorRequestDTO;
import in.javabysuraj.app.DTO.AuthorResponseDTO;
import in.javabysuraj.app.DTO.BookRequestDTO;
import in.javabysuraj.app.DTO.BookResponseDTO;
import in.javabysuraj.app.entity.Author;
import in.javabysuraj.app.entity.Book;
import in.javabysuraj.app.repository.AuthorRepository;
import in.javabysuraj.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImp implements AuthorServiceInt {

    @Autowired
    AuthorRepository authRepo;

    @Autowired
    BookRepository bookRepo;

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());

        if (dto.getBooks() != null) {
            for (BookRequestDTO b : dto.getBooks()) {
                Book book = new Book();
                book.setTitle(b.getTitle());
                book.setPrice(b.getPrice());
                book.setAuthor(author);
                author.getBooks().add(book);
            }
        }
        Author savedAuthor = authRepo.saveAndFlush(author);

        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(savedAuthor.getId());
        authorResponseDTO.setName(savedAuthor.getName());

        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();

        for (Book b : author.getBooks()) {
            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            bookResponseDTO.setId(b.getId());
            bookResponseDTO.setTitle(b.getTitle());
            bookResponseDTO.setPrice(b.getPrice());
            bookResponseDTOList.add(bookResponseDTO);
        }

        authorResponseDTO.setBooks(bookResponseDTOList);

        return authorResponseDTO;
    }
}
