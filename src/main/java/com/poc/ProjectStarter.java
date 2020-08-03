package com.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.model.Customer;
import com.poc.model.Student;
import com.poc.service.ExcelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Log4j2
public class ProjectStarter implements CommandLineRunner {


    ObjectMapper objectMapper = new ObjectMapper();

    ExcelService excelService = new ExcelService();


    public static void main(String[] args) {
        SpringApplication.run(ProjectStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Create DUMMY data
        PodamFactory factory = new PodamFactoryImpl();
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Customer myPojo = factory.manufacturePojo(Customer.class);
            customers.add(myPojo);
        }
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student myPojo = factory.manufacturePojo(Student.class);
            students.add(myPojo);
        }
        excelService.writeSheetPerObjList("dummy.xlsx", customers,students);
    }
}
