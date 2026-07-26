package com.jp.e_m_s.Exception;


public class EmployeeNotFoundException  extends RuntimeException{
    public EmployeeNotFoundException(String msg){
        super(msg);
    }
}
