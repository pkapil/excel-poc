package com.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    @ExcelFieldName("id")
    public String id;
    @ExcelFieldName("student_name")
    public String studentName;
    @ExcelFieldName("student_email")
    public String studentEmail;
    @ExcelFieldName("student_company")
    public String studentCompany;

}
