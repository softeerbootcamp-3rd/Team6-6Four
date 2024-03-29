package com.softeer.team6four.domain.payment.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softeer.team6four.domain.payment.domain.PayType;
import com.softeer.team6four.domain.payment.domain.Payment;
import com.softeer.team6four.domain.payment.domain.PaymentRepository;
import com.softeer.team6four.domain.reservation.application.ReservationSearchService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentSearchService {
	private final ReservationSearchService reservationSearchService;
	private final PaymentRepository paymentRepository;

	public Long getTotalIncomeByTargetId(Long carbobId) {
		List<Long> usedReservationIdList = reservationSearchService.getUsedReservationIdListByCarbobId(carbobId);

		return paymentRepository.findPaymentsByPayTypeAndTargetIdIn(PayType.INCOME, usedReservationIdList)
			.stream()
			.mapToLong(Payment::getAmount)
			.sum();
	}
}
