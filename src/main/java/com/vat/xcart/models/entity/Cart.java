package com.vat.xcart.models.entity;


import com.vat.xcart.models.pojo.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data  // Lombok annotation for @Getter, @Setter, @ToString, @EqualsAndHashCode
@Builder  // Lombok annotation for builder pattern
@NoArgsConstructor  // No-argument constructor
@AllArgsConstructor  // All-argument constructor
@Document(collection = "cart")
public class Cart {

    @Id
    private String id;
    private String userId; // Reference to the User's ID
    private List<CartItem> items;

}
