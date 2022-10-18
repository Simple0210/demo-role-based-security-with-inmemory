package uz.simplecode.rolebasedsecurityapp.service;

import org.springframework.http.ResponseEntity;
import uz.simplecode.rolebasedsecurityapp.entity.Student;
import uz.simplecode.rolebasedsecurityapp.payload.ApiResponse;
import uz.simplecode.rolebasedsecurityapp.payload.req.StudentReqDto;
import uz.simplecode.rolebasedsecurityapp.payload.res.StudentResDto;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    ResponseEntity<ApiResponse<List<StudentResDto>>> getAllStudent();

    ResponseEntity<ApiResponse<StudentResDto>> getStudentById(UUID id);

    ResponseEntity<ApiResponse<String>> addNewStudent(StudentReqDto studentReqDto);

    ResponseEntity<ApiResponse<String>> editStudent(UUID id, StudentReqDto studentReqDto);

    ResponseEntity<ApiResponse<String>> deleteStudent(UUID id);


}
