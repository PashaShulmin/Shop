package ru.itis.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    private Long id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String mimeType;
}
