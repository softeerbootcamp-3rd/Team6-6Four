package com.softeer.team6four.domain.carbob.infra;

import com.softeer.team6four.domain.carbob.domain.Carbob;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarbobQrCreateEvent {
	private final Carbob carbob;
}
