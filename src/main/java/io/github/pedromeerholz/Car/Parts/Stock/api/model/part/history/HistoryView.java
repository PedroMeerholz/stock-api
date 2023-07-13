package io.github.pedromeerholz.Car.Parts.Stock.api.model.part.history;

import jakarta.persistence.*;

@Entity
@Table(name = "historyview")
public class HistoryView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historyid")
    private Long historyId;
    @Column(name = "partid")
    private Long partId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "category")
    private String category;
    @Column(name = "date")
    private String date;
    @Column(name = "enabled")
    private boolean enabled;

    public Long getHistoryId() {
        return historyId;
    }

    public Long getPartId() {
        return partId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
