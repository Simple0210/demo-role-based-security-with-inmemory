package uz.simplecode.rolebasedsecurityapp.payload.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class StudentResDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private GroupResDto group;
}
