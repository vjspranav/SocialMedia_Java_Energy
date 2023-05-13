package com.socialmedia.controller;

import com.socialmedia.service.AuthService;

public class LogoutCommand implements Command {
    private final AuthService authService;

    public LogoutCommand(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void execute() {
        authService.logout();
    }
}
