package com.softeer.team6four.domain.reservation.application.response;

import com.google.firebase.database.annotations.NotNull;
import com.softeer.team6four.domain.reservation.application.ReservationTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public class ReservationApplicationInfo {
	private final @NotNull Long reservationId;
	private final @NotNull String carbobNickname;
	private final @NotNull ReservationTime reservationTime;
	private final @NotNull String address;
	private final @NotNull String guestNickname;
	private final @NotNull Integer totalFee;
	private @NotNull String rentalDate;
	private @NotNull String rentalTime;

	public ReservationApplicationInfo(Long reservationId, String carbobNickname, ReservationTime reservationTime,
		String address, String guestNickname, Integer totalFee) {
		this.reservationId = reservationId;
		this.carbobNickname = carbobNickname;
		this.reservationTime = reservationTime;
		this.address = address;
		this.guestNickname = guestNickname;
		this.totalFee = totalFee;
	}

	public void convertReservationTimeToStr() {
		log.info(this.reservationTime.toString());
		String[] timeSplit = this.reservationTime.toString().split(" ");
		this.rentalDate = timeSplit[0];
		this.rentalTime = timeSplit[1]+"-"+timeSplit[3];
	}

}
