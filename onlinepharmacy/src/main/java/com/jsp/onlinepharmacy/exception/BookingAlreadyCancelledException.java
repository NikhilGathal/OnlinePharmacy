package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookingAlreadyCancelledException extends RuntimeException {

	private String message;
}
