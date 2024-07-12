package com.thatmg393.ts4j.world.loader.base;

import com.thatmg393.ts4j.world.TerrariaWorld;

import java.util.function.Supplier;
import java.util.logging.Logger;

public interface BaseWorldLoader {
	public TerrariaWorld get();
	
	public default <T> T runAndForgetException(Supplier<T> f) {
		try {
			return f.get();
		} catch (Exception e) {
			Logger.getLogger("BaseWorldLoader").warning("Failed to execute lambda: " + e.getMessage());
			return null;
		}
	}

	public default <T> void assertOrLogNThrow(T newObj, T realObj, String reason) {
		if (newObj == null) return;
		if (!newObj.equals(realObj)) {
			Logger LOGGER = Logger.getLogger("BaseWorldLoader");
			LOGGER.severe("Assertion failed! Reason: " + reason);
			LOGGER.severe(newObj + " != " + realObj);
			throw new AssertionError(newObj + " != " + realObj);
		}
	}
}