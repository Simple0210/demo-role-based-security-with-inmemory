package uz.simplecode.rolebasedsecurityapp.mapper;

import uz.simplecode.rolebasedsecurityapp.entity.Group;
import uz.simplecode.rolebasedsecurityapp.entity.Student;
import uz.simplecode.rolebasedsecurityapp.payload.req.StudentReqDto;
import uz.simplecode.rolebasedsecurityapp.payload.res.StudentResDto;

public class StudentMapper {
    public static StudentResDto parseToDto(Student student) {
        return StudentResDto
                .builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .phoneNumber(student.getPhoneNumber())
                .group(GroupMapper.parseToResDto(student.getGroup()))
                .build();
    }

    public static Student parseToEntity(StudentReqDto studentReqDto, Group group) {
        return Student
                .builder()
                .firstName(studentReqDto.getFirstName())
                .lastName(studentReqDto.getLastName())
                .phoneNumber(studentReqDto.getPhoneNumber())
                .group(group)
                .build();
    }
}
