package com.bigcorp.booking.dto;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantDtoTest {
	@Test
	public void getPrix() {
		RestaurantDto r = new RestaurantDto();
		PrixDto expected = new PrixDto(null);
		PrixDto actual = r.getPrix();

		assertEquals(expected, actual);
	}
}
