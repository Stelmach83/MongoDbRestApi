package dev.stelmach.homeworkapi.exception;

import org.springframework.validation.BindingResult;

public class EmployeeModelException extends RuntimeException {

    private BindingResult bindingResult;

    public EmployeeModelException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
