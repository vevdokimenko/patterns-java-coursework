package org.robotdreams.patterns.coursework.exceptions;

public class ExecResult {
    private boolean success;
    private String message;

    protected ExecResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ExecResult success() {
        return new ExecResult(true, "");
    }

    public static ExecResult success(String message) {
        return new ExecResult(true, message);
    }

    public static ExecResult error() {
        return new ExecResult(false, "");
    }

    public static ExecResult error(String message) {
        return new ExecResult(false, message);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }
}
