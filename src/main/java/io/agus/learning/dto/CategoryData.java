package io.agus.learning.dto;

import javax.validation.constraints.NotNull;

public class CategoryData {
    @NotNull(message = "Category name is required")
    private String name;

    public CategoryData(String name) {
        this.name = name;
    }

    public CategoryData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
