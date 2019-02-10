package com.example.service;

import com.example.auth.Role;

public interface UserService extends BaseService {

	Role checkUserAndPassword(String name,String password);
}