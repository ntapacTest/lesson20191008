package com.maup.lesson20191008.pojo;

import com.maup.lesson20191008.model.User;
import lombok.Data;

@Data
public class AddressPojo {
    private Long userId;
    private String address;
    private String zip;
}
