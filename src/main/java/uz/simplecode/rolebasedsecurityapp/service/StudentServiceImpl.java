package uz.simplecode.rolebasedsecurityapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.simplecode.rolebasedsecurityapp.entity.Group;
import uz.simplecode.rolebasedsecurityapp.entity.Student;
import uz.simplecode.rolebasedsecurityapp.mapper.StudentMapper;
import uz.simplecode.rolebasedsecurityapp.payload.ApiResponse;
import uz.simplecode.rolebasedsecurityapp.payload.req.StudentReqDto;
import uz.simplecode.rolebasedsecurityapp.payload.res.StudentResDto;
import uz.simplecode.rolebasedsecurityapp.repository.GroupRepository;
import uz.simplecode.rolebasedsecurityapp.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentResDto>>> getAllStudent() {
        List<String> errors = new ArrayList<>();
        ApiResponse<List<StudentResDto>> response = new ApiResponse<>();
        try {
            response.setData(studentRepository.findAll().stream().map(StudentMapper::parseToDto).collect(Collectors.toList()));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            errors.add("Error: ");
            errors.add(e.getMessage());
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<StudentResDto>> getStudentById(UUID id) {
        List<String> errors = new ArrayList<>();
        ApiResponse<StudentResDto> response = new ApiResponse<>();
        try {
            Optional<Student> byId = studentRepository.findById(id);
            if (byId.isPresent()) {
                response.setData(StudentMapper.parseToDto(byId.get()));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.setSuccess(false);
                errors.add("Student not found");
                response.setErrors(errors);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            errors.add(e.getMessage());
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> addNewStudent(StudentReqDto studentReqDto) {
        List<String> errors = new ArrayList<>();
        ApiResponse<String> response = new ApiResponse<>();
        try {
            Optional<Group> byId = groupRepository.findById(studentReqDto.getGroupId());
            if (byId.isPresent()) {
                studentRepository.save(StudentMapper.parseToEntity(studentReqDto, byId.get()));
                response.setData("Student ma`lumotlari muvaffaqiyatli saqlandi!");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.setSuccess(false);
                errors.add(studentReqDto.getGroupId() + " - group topilmadi!");
                response.setErrors(errors);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            errors.add(e.getMessage());
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> editStudent(UUID id, StudentReqDto studentReqDto) {
        List<String> errors = new ArrayList<>();
        ApiResponse<String> response = new ApiResponse<>();
        try {
            Optional<Student> byId = studentRepository.findById(id);
            if (byId.isPresent()) {
                Optional<Group> byId1 = groupRepository.findById(studentReqDto.getGroupId());
                if (byId1.isPresent()) {
                    Student student = byId.get();
                    Group group = byId1.get();
                    student.setFirstName(studentReqDto.getFirstName());
                    student.setLastName(studentReqDto.getLastName());
                    student.setPhoneNumber(studentReqDto.getPhoneNumber());
                    student.setGroup(group);
                    studentRepository.save(student);
                    response.setData("Student ma`lumotlari muvaffaqiyatli taxrirlandi!");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                } else {
                    response.setSuccess(false);
                    errors.add(id + " - group topilmadi!");
                    response.setErrors(errors);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            } else {
                response.setSuccess(false);
                errors.add(id + " - student topilmadi!");
                response.setErrors(errors);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            errors.add(e.getMessage());
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteStudent(UUID id) {
        List<String> errors = new ArrayList<>();
        ApiResponse<String> response = new ApiResponse<>();
        try {
            Optional<Student> byId = studentRepository.findById(id);
            if (byId.isPresent()) {
                studentRepository.deleteById(id);
                response.setData("Student ma`lumotlari muvaffaqiyatli o`chirildi!");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.setSuccess(false);
                errors.add(id + " - student topilmadi!");
                response.setErrors(errors);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            errors.add("");
            response.setErrors(errors);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


}
