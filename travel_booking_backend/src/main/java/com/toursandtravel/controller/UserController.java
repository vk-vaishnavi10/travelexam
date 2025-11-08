package com.toursandtravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.toursandtravel.dto.CommonApiResponse;
import com.toursandtravel.dto.RegisterUserRequestDto;
import com.toursandtravel.dto.UserLoginRequest;
import com.toursandtravel.dto.UserLoginResponse;
import com.toursandtravel.dto.UserResponseDto;
import com.toursandtravel.resource.UserResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:30082" })  // for local + frontend service
public class UserController {

    @Autowired
    private UserResource userResource;

    // ✅ Admin Registration API
    @PostMapping("/admin/register")
    @Operation(summary = "API to register Admin")
    public ResponseEntity<CommonApiResponse> registerAdmin(@RequestBody RegisterUserRequestDto request) {
        return userResource.registerAdmin(request);
    }

    // ✅ Customer / Tour Guide Registration API
    @PostMapping("/register")
    @Operation(summary = "API to register Customer or Tour Guide user")
    public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto request) {
        return this.userResource.registerUser(request);
    }

    // ✅ Login API
    @PostMapping("/login")
    @Operation(summary = "API to login any user")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        return userResource.login(userLoginRequest);
    }

    // ✅ Fetch users by role (Admin, Customer, Tour Guide)
    @GetMapping("/fetch/role-wise")
    @Operation(summary = "API to get users by role")
    public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role)
            throws JsonProcessingException {
        return userResource.getUsersByRole(role);
    }

    // ✅ Delete Tour Guide
    @DeleteMapping("/tourGuide/delete")
    @Operation(summary = "API to delete a Tour Guide and all its tours")
    public ResponseEntity<CommonApiResponse> deleteGuide(@RequestParam("guideId") Integer guideId) {
        return userResource.deleteGuide(guideId);
    }

    // ✅ Fetch User by ID
    @GetMapping("/fetch/user-id")
    @Operation(summary = "API to get user details by User ID")
    public ResponseEntity<UserResponseDto> fetchUserById(@RequestParam("userId") int userId) {
        return userResource.getUserById(userId);
    }

}
