package com.example.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.VO.Department;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.repositiry.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	private static String url = "http://DEPARTMENT-SERVICE/departments/";

	public ResponseTemplateVO getUserWithDepartment(Long userId) {

		ResponseTemplateVO templateVO = new ResponseTemplateVO();
		User user = userRepository.findByUserId(userId);
		Department department = restTemplate.getForObject(url + user.getDepartmentId(), Department.class);
		templateVO.setUser(user);
		templateVO.setDepartment(department);
		return templateVO;
	}

}
