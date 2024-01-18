package com.ducky.expensetracker.request;

import jakarta.validation.constraints.NotNull;

public class Participant {

    @NotNull(message = "Provide a valid participant name")
    private String name;
    private boolean hasPaid;


}
