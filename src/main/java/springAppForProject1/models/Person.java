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
public class Person {
    private int personId;
    @NotEmpty(message = "Name must be not empty")
    @Size(min=2, max= 30, message = "Name mst be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Surname must be not empty")
    @Size(min=2, max= 30, message = "Surname must be between 2 and 30 characters")
    private String surname;
    @Min(value = 1920, message = "Year must must be higher than 1920")
    @Max(value = 2006, message = "Year must must be lower than 2006")
    @NotEmpty(message = "Year must be not empty")
    private int yearOfBirth;
}
