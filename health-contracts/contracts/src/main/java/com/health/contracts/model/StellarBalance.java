package com.health.contracts.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StellarBalance {

	@Getter
	@Setter
	@NoArgsConstructor
	public class Balance {
		String type;
		String ammount;
	}

}
