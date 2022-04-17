package com.tcs.binding;

import lombok.Data;

@Data
public class UnlockForm {

	private String userEmail;
	private String tempPassword;
	private String newPassword;
}
