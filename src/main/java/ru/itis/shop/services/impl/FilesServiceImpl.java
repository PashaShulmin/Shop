package ru.itis.shop.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.shop.exceptions.FileNotFoundException;
import ru.itis.shop.models.FileInfo;
import ru.itis.shop.repositories.FilesRepository;
import ru.itis.shop.services.FilesService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {
    @Value("${files.storage.path}")
    private String storagePath;

    private final FilesRepository filesRepository;

    @Transactional
    @Override
    public Optional<FileInfo> saveFileToStorage(MultipartFile multipartFile) {
        try {
            String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

            String storageFileName = UUID.randomUUID() + extension;

            FileInfo file = FileInfo.builder()
                    .mimeType(multipartFile.getContentType())
                    .originalFileName(multipartFile.getOriginalFilename())
                    .storageFileName(storageFileName)
                    .size(multipartFile.getSize())
                    .build();

            Files.copy(multipartFile.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));

            filesRepository.save(file);

            return Optional.of(file);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = filesRepository.findByStorageFileName(fileName).orElseThrow(FileNotFoundException::new);
        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getMimeType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalFileName() + "\"");
        try {
            IOUtils.copy(new FileInputStream(storagePath + "\\" + file.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
