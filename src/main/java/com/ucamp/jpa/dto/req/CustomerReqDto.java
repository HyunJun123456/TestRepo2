package com.ucamp.jpa.dto.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReqDto {
    @NotEmpty(message = "name은 null이 될 수 없습니다.")
    private String name;
    @NotEmpty(message = "email은 null이 될 수 없습니다.")
    private String email;
    private int age;
}
