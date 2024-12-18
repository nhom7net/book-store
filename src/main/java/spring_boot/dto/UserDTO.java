package spring_boot.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    @Size(min = 3, message = "USERNAME_INVALID")
     String userName;

    @Size(min = 6, message = "INVALID_PASSWORD")
     String password;



}
