package ru.itis.shop.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemForm {
    @NotBlank
    @Size(min = 4, max = 30)
    private String title;

    @NotBlank
    @Size(min = 10, max = 100)
    private String description;

    @NotEmpty
    private String price;

    @NotEmpty
    @Min(1)
    private String amount;

    private MultipartFile file;
}
