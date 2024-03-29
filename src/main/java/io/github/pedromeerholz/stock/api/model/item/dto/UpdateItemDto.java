package io.github.pedromeerholz.stock.api.model.item.dto;

public class UpdateItemDto {
    private String name;
    private String description;
    private String category;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
