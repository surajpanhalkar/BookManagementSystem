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

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {

        List<Author> authors = authRepo.findAll();

        List<AuthorResponseDTO> authorResponseDTOList = new ArrayList<>();
        for (Author author : authors) {
            AuthorResponseDTO authDto = new AuthorResponseDTO();
            authDto.setId(author.getId());
            authDto.setName(author.getName());

            List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();

            for (Book book : author.getBooks()) {
                BookResponseDTO bookDto = new BookResponseDTO();
                bookDto.setId(book.getId());
                bookDto.setTitle(book.getTitle());
                bookDto.setPrice(book.getPrice());

                bookResponseDTOList.add(bookDto);
            }
            authDto.setBooks(bookResponseDTOList);
            authorResponseDTOList.add(authDto);
        }
        return authorResponseDTOList;

    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {

        Author author = authRepo.findById(id).orElse(null);

        if (author == null) {
            return null;
        }

        AuthorResponseDTO response = new AuthorResponseDTO();
        response.setId(author.getId());
        response.setName(author.getName());

        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();
        if (author.getBooks() != null) {
            for (Book books : author.getBooks()) {
                BookResponseDTO booResponseDTO = new BookResponseDTO();
                booResponseDTO.setId(books.getId());
                booResponseDTO.setTitle(books.getTitle());
                booResponseDTO.setPrice(books.getPrice());
                bookResponseDTOList.add(booResponseDTO);
            }
        }
        response.setBooks(bookResponseDTOList);
        return response;
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO dto) {
        Author author = authRepo.findById(id).orElse(null);

        if (author == null) {
            return null;
        }

        if (dto.getName() != null) {
            author.setName(dto.getName());
        }
        List<Book> books = new ArrayList<>();

        if (dto.getBooks() != null) {
            for (BookRequestDTO bookDto : dto.getBooks()) {
                Book book = new Book();
                book.setTitle(bookDto.getTitle());
                book.setPrice(bookDto.getPrice());
                book.setAuthor(author);
                books.add(book);
            }
        }

        author.setBooks(books);


        Author savedAuthor = authRepo.saveAndFlush(author);

        AuthorResponseDTO responseDTO = new AuthorResponseDTO();
        responseDTO.setId(savedAuthor.getId());
        responseDTO.setName(savedAuthor.getName());

        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();
        if (savedAuthor.getBooks() != null) {
            for (Book book : savedAuthor.getBooks()) {
                BookResponseDTO bookResponseDTO = new BookResponseDTO();
                bookResponseDTO.setId(book.getId());
                bookResponseDTO.setTitle(book.getTitle());
                bookResponseDTO.setPrice(book.getPrice());
                bookResponseDTOList.add(bookResponseDTO);
            }
        }
        responseDTO.setBooks(bookResponseDTOList);
        return responseDTO;
    }
}
