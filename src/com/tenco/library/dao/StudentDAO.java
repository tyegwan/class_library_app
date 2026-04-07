package com.tenco.library.dao;

import com.mysql.cj.xdevapi.Collection;
import com.tenco.library.dto.Student;
import com.tenco.library.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 학생 등록
    public void addStudent(Student student) throws SQLException {
        String sql = """
                INSERT INTO students(name,student_id) VALUES(? ,?)
                """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudentId());
            pstmt.executeUpdate();

        }


    }

    // 전체 학생 조회
    public List<Student> getAllStudents() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        String sql = """
                SELECT * FROM students ORDER BY id
                """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                studentList.add(mapToStudent(rs));
            }
        }
        return studentList;
    }


    // 학번으로 학생 조회 - 로그인
    public Student authenticateStudent(String studentId) throws SQLException {
        String sql = """
                SELECT * FROM students WHERE student_id = ?
                """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return mapToStudent(rs);
                }
            }
        }
        return null;
    }

    // ResultSet -> Student 변환 메소드
    private Student mapToStudent(ResultSet rs) throws  SQLException {
//        Student student = new Student();
//        student.setId(rs.getInt("id"));
//        student.setName(rs.getString("name"));
//        student.setStudentId(rs.getString("student_id"));
//        return student;

        return Student.builder()
                .id(rs.getInt(rs.getInt("id")))
                .name(rs.getString("name"))
                .studentId(rs.getString("student"))
                .build();
    }

    //테스트 코드
    public static void main(String[] args) throws SQLException {
        new Student("이길동", "12345");

        Student student = Student
                .builder()
                .studentId("20202020")
                .name("고길동")
                .build();

        StudentDAO studentDAO = new StudentDAO();

        //studentDAO.addStudent(student);

//        System.out.println(studentDAO.getAllStudents().toString());
        Student resultStudent = studentDAO.authenticateStudent("20230001");
        System.out.println(resultStudent);
    }

}
