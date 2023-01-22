package com.akash.recruitment.dto;

import lombok.Data;

@Data
public class ApplicationCountDTO {

    private int open;

    private int completed;

    private int unreachable;

    private int rejected;

    private int alreadyPurchased;
}
