package in.javabysuraj.app.repository;

import in.javabysuraj.app.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
