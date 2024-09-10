package com.example.futurbe.dto.userDTO;

import com.example.futurbe.entitys.Role;

import java.util.List;

public class UserInfoResponse {
		Long id;
	 	String firstName;
		 String lastName;
		 String userName;
		 Role role;
		 String resumeURI;
		 Long resumeId ;

		 String email;
		 String profilePicURI;

		 String userOption;

	public UserInfoResponse(Long id,String firstName, String lastName, String userName,String email, Role role, String resumeURI, Long resumeId, String profilePicURI,String userOption) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
		this.userName=userName;
		this.role = role;
		this.resumeURI = resumeURI;
		this.resumeId = resumeId;
		this.profilePicURI = profilePicURI;
		this.userOption=userOption;
		this.id=id;
	}

	public UserInfoResponse(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getResumeURI() {
		return resumeURI;
	}

	public void setResumeURI(String resumeURI) {
		this.resumeURI = resumeURI;
	}

	public Long getResumeId() {
		return resumeId;
	}

	public void setResumeId(Long resumeId) {
		this.resumeId = resumeId;
	}

	public String getProfilePicURI() {
		return profilePicURI;
	}

	public void setProfilePicURI(String profilePicURI) {
		this.profilePicURI = profilePicURI;
	}

	public String getUserOption() {
		return userOption;
	}

	public void setUserOption(String userOption) {
		this.userOption = userOption;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
