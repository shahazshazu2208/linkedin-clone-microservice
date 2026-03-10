package com.linkedin.users_service.service;

import com.linkedin.users_service.dto.UserProfileDto;
import com.linkedin.users_service.model.UserProfile;
import com.linkedin.users_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileDto getProfile(Long userId) {
        UserProfile profile = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDto dto = new UserProfileDto();
        dto.setUserId(profile.getUserId());
        dto.setName(profile.getName());
        dto.setHeadline(profile.getHeadline());
        dto.setAbout(profile.getAbout());
        dto.setLocation(profile.getLocation());
        dto.setProfilePictureUrl(profile.getProfilePictureUrl());
        return dto;
    }

    public UserProfileDto updateProfile(Long userId, UserProfileDto dto) {
        UserProfile profile = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setName(dto.getName());
        profile.setHeadline(dto.getHeadline());
        profile.setAbout(dto.getAbout());
        profile.setLocation(dto.getLocation());
        userRepository.save(profile);
        return dto;
    }

    public UserProfileDto createProfile(UserProfileDto dto) {
        UserProfile profile = new UserProfile();
        profile.setUserId(dto.getUserId());
        profile.setName(dto.getName());
        profile.setHeadline(dto.getHeadline());
        profile.setAbout(dto.getAbout());
        profile.setLocation(dto.getLocation());
        profile.setProfilePictureUrl(dto.getProfilePictureUrl());
        userRepository.save(profile);
        return dto;
    }
}