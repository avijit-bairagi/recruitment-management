package com.akash.recruitment.dto;

public interface ApplicationCount {

    int getOpen();

    int getCompleted();

    int getUnreachable();

    int getRejected();

    int getAlreadyPurchased();
}
