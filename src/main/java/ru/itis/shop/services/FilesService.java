package ru.itis.shop.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.shop.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface FilesService {
    Optional<FileInfo> saveFileToStorage(MultipartFile multipartFile);

    void addFileToResponse(String fileName, HttpServletResponse response);
}
