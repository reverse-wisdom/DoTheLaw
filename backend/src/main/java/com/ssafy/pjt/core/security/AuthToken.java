package com.ssafy.pjt.core.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}