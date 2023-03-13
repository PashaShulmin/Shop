package ru.itis.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shop.FileInfo;

import java.util.Optional;

public interface FilesRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findByStorageFileName(String fileName);
}
