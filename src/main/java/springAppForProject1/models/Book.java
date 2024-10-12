package springAppForProject1.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookId;
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Author must be not empty")
    @Size(min = 2, max = 30, message = "Author must be between 2 and 100 characters")
    private String author;
    @Min(value = 1500, message = "Year must must be higher than 1500")
    @Max(value = 2024, message = "Year must must be lower than 2024")
    private int year;
}
