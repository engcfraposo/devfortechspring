package com.devfortech.HelloWord.controller.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StandardError {

	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	
}
