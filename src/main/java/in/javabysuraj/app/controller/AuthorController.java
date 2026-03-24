package in.javabysuraj.app.controller;

import in.javabysuraj.app.DTO.AuthorRequestDTO;
import in.javabysuraj.app.DTO.AuthorResponseDTO;
import in.javabysuraj.app.service.AuthorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorServiceImp service;

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO dto) {
        return ResponseEntity.ok(service.createAuthor(dto));
    }

    @GetMapping ("/all")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors(){
        return ResponseEntity.ok(service.getAllAuthors());
    }

    @GetMapping("/{id}")
    public AuthorResponseDTO getAuthorById(@PathVariable Long id) {
        return service.getAuthorById(id);
    }

}
