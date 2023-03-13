package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.shop.services.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class FilesController {

    private final FilesService filesService;

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        filesService.addFileToResponse(fileName, response);
    }

}

