package uz.simplecode.rolebasedsecurityapp.payload.req;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class StudentReqDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UUID groupId;
}
