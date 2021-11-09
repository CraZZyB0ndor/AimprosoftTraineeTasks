package com.aimprosoft.utils;

import com.aimprosoft.models.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class RequestUtils {

    public static Integer getInt(String possNum) {
        if (possNum != null && !possNum.isEmpty()) {
            try {
                return Integer.valueOf(possNum);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static Date getDate(String date) {
        if (date != null && !date.isEmpty()) {
            try {
                return new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
            } catch (ParseException | NullPointerException e) {
                return null;
            }
        }
        return null;
    }

    public static java.sql.Date getEmployeeWithCorrectDate(Employee employee) {
        if (employee != null && employee.getStartWorkingDate() != null) {
            try {
                return new java.sql.Date(employee.getStartWorkingDate().getTime());
            } catch (NullPointerException e) {
                return null;
            }
        }
        return null;
    }
}