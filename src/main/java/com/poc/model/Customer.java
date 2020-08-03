package com.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @ExcelFieldName("id")
    public String id;
    @ExcelFieldName("contact_name")
    public String contactName;
    @ExcelFieldName("contact_email")
    public String contactEmail;
    @ExcelFieldName("contact_company")
    public String contactCompany;

}
