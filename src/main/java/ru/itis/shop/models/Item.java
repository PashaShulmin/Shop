package ru.itis.shop.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer price;

    private Integer amount;

    @Column(name = "item_pic_storage_name")
    private String itemPictureStorageName;

    @ManyToMany(mappedBy = "favorites")
    private Set<Account> inFavorites;

}
