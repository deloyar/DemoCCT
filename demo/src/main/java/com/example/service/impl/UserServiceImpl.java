package com.example.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.User;
import com.example.auth.Role;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Role checkUserAndPassword(String name, String password) {

		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
			User user = userRepository.findByName(name);
			if (user != null && user.getStatus()) {
				String dbPassword = user.getPassword();
				String inputShaPassword = DigestUtils.sha256Hex(password);
				if (dbPassword.equals(inputShaPassword)) {
					return user.getRole();
				}
			}
		}

		return null;
	}

}