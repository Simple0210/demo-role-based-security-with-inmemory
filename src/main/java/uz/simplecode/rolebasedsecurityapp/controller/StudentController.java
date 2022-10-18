package uz.simplecode.rolebasedsecurityapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.simplecode.rolebasedsecurityapp.payload.ApiResponse;
import uz.simplecode.rolebasedsecurityapp.payload.req.StudentReqDto;
import uz.simplecode.rolebasedsecurityapp.payload.res.StudentResDto;
import uz.simplecode.rolebasedsecurityapp.service.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentService studentService;


  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }


  @GetMapping("/get")
  @Operation(summary = "Get all Students", description = "Barcha studentlar ro'yxatini ko'rish")
  public ResponseEntity<ApiResponse<List<StudentResDto>>> getAllStudent() {
    return studentService.getAllStudent();
  }


  @GetMapping("/get/{id}")
  @Operation(summary = "Get Student by id", description = "Id bo'yicha studentni ko'rish")
  public ResponseEntity<ApiResponse<StudentResDto>> getStudentById(@PathVariable UUID id) {
    return studentService.getStudentById(id);
  }


  @PostMapping("/save")
  @Operation(summary = "Add new Student", description = "Yangi studentni saqlash")
  public ResponseEntity<ApiResponse<String>> addNewStudent(@RequestBody StudentReqDto studentReqDto) {
    return studentService.addNewStudent(studentReqDto);
  }


  @PutMapping("/edit/{id}")
  @Operation(summary = "Edit Student", description = "Student ma'lumotlarini o'zgartirish")
  public ResponseEntity<ApiResponse<String>> editStudent(@PathVariable UUID id, @RequestBody StudentReqDto studentReqDto) {
    return studentService.editStudent(id, studentReqDto);
  }


  @DeleteMapping("/delete/{id}")
  @Operation(summary = "Delete Student", description = "Student ma'lumotlarini o'chirish")
  public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable UUID id) {
    return studentService.deleteStudent(id);
  }
}
