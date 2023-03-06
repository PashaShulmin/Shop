package ru.itis.shop.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    @NotBlank
    private Long id;

    @NotBlank
    @Size(min = 4, max = 30)
    private String title;

    @NotBlank
    @Size(min = 10, max = 100)
    private String description;

    @NotEmpty
    private String itemPictureStorageName;

    @NotEmpty
    private Integer price;

    @NotEmpty
    @Min(1)
    private Integer amount;
}
