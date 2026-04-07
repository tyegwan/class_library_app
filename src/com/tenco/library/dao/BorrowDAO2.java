package com.tenco.library.dao;

import com.tenco.library.dto.Borrow;
import com.tenco.library.util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 대출/반납 관련 SQL 을 실행하는 DAO
public class BorrowDAO2 {

    // 도서 대출 처리
    // 대출 가능 여부 확인 --> borrow 테이블에 기록 --- 북 테이블 으로 변경
    // try-with-resource 블록 문법 - 블록인 끝나는 순간 무조건 자원을 먼저 닫아 버림
    // 이게 트랜잭션 처리할 때는 값을 확인해서 commit 또는 rollback 해야 하기 때문에 사용하면 안됨
    // 즉, 직접 close() 처리 해야 함 - 트랜잭션 처리를 위해서.
    public void borrowBook(int bookId, int studentId) throws SQLException {
        Connection conn = null;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.print("Connecting to database");
                for (int i = 0; i < 5; i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread thread = new Thread(r);


    }

    // 테스트 코드 작성
    public static void main(String[] args) {
        // 샘플 데이터 - 20230001

    }
}
