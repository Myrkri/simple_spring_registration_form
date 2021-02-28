package dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class UserDTO {
    private final String name;
    private final String password;
}
