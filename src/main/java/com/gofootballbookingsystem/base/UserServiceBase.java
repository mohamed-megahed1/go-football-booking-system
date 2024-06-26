package com.gofootballbookingsystem.base;

public interface UserServiceBase {
    public boolean isUsernameExists(String username);
    public boolean isEmailExists(String email);

    public boolean isIdExists(Long id);
}
