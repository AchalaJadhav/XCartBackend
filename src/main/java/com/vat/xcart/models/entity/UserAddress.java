package com.vat.xcart.models.entity;


import com.vat.xcart.models.pojo.DetailAddress;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "user_address")
public class UserAddress {

    @Id
    private String id;

    private String userId;

    List<DetailAddress> userAddresses;


}
