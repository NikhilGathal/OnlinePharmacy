package com.jsp.onlinepharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestControllerAdvice
public class OnlinePharmacyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AdminIdNotFoundException(AdminIdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Admin Id Not Present");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> storeIdNotFoundException(
			MedicalStoreIdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Store Id Not Present");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AdminNotFoundException(AdminNotFoundWithThisEmail exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Admin Email is Not Present Invalid Email.....");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AdminPasswordNotValidException(
			AdminPasswordNotValidException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(" Invalid Password.....");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AddressIdNotFoundException(AddressIdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Id Not Present");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineIdNotFoundException(
			MedicineIdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Medicine ID IS NOT PRESENT");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineNameNotFoundException(
			MedicineNameNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Medicine NOT PRESENT in this name");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffIdNotFoundException(StaffIdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("STAFF ID IS NOT PRESENT");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFoundException(
			CustomerIdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Customer ID IS NOT PRESENT");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingIdNotFoundException(BookingIdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Booking ID IS NOT PRESENT");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingalreadycancelException(
			BookingAlreadyCancelledException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Booking Already Cancelled");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingalreadydeliveredException(
			BookingAlreadyDeliveredException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Booking Already Delivered");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingCantCancelException(BookingCantCancel exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Booking Already it is date");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
