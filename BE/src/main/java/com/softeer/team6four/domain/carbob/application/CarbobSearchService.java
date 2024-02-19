package com.softeer.team6four.domain.carbob.application;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softeer.team6four.domain.carbob.application.exception.CarbobNotFoundException;
import com.softeer.team6four.domain.carbob.application.exception.NotCarbobOwnerException;
import com.softeer.team6four.domain.carbob.application.response.MyCarbobDetailInfo;
import com.softeer.team6four.domain.carbob.application.response.MyCarbobSummary;
import com.softeer.team6four.domain.carbob.domain.Carbob;
import com.softeer.team6four.domain.carbob.domain.CarbobImage;
import com.softeer.team6four.domain.carbob.domain.CarbobImageRepository;
import com.softeer.team6four.domain.carbob.domain.CarbobRepository;
import com.softeer.team6four.domain.carbob.infra.CarbobRepositoryImpl;
import com.softeer.team6four.domain.carbob.presentation.MyCarbobSortType;
import com.softeer.team6four.domain.payment.application.PaymentSearchService;
import com.softeer.team6four.domain.reservation.application.ReservationSearchService;
import com.softeer.team6four.domain.reservation.application.SelfUseTime;
import com.softeer.team6four.global.exception.BusinessException;
import com.softeer.team6four.global.response.ErrorCode;
import com.softeer.team6four.global.response.SliceResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarbobSearchService {
	private final CarbobImageRepository carbobImageRepository;
	private final CarbobRepository carbobRepository;
	private final PaymentSearchService paymentSearchService;
	private final CarbobRepositoryImpl carbobRepositoryImpl;

	private final ReservationSearchService reservationSearchService;

	public SliceResponse<MyCarbobSummary> findMyCarbobList
		(
			Long userId, MyCarbobSortType sortType, Long lastCarbobId, Long lastReservationCount,
			Pageable pageable
		) {
		if (userId == null || sortType == null) {
			throw new BusinessException(ErrorCode.MISSING_REQUEST_PARAMETER);
		}
		Slice<MyCarbobSummary> myCarbobSummarySlice = carbobRepositoryImpl.findCarbobSummaryByUserId(
			userId, sortType, lastCarbobId, lastReservationCount, pageable);
		return SliceResponse.of(myCarbobSummarySlice);
	}

	public MyCarbobDetailInfo findMyCarbobDetailInfo(Long userId, Long carbobId) {
		Carbob carbob = carbobRepository.findById(carbobId)
			.orElseThrow(() -> new CarbobNotFoundException(ErrorCode.CARBOB_NOT_FOUND));

		if (!carbob.getHost().getUserId().equals(userId)) {
			throw new NotCarbobOwnerException(ErrorCode.NOT_CARBOB_OWNER);
		}

		String carbobImageUrl = getCarbobImageUrl(carbobId);
		SelfUseTime selfUseTime = reservationSearchService.getSelfUseTime(carbobId);
		Long totalIncomeByTargetId = paymentSearchService.getTotalIncomeByTargetId(carbobId);

		return CarbobMapper.mapMyCarbobDetailInfo(carbob, carbobImageUrl, selfUseTime,
			totalIncomeByTargetId);
	}

	private String getCarbobImageUrl(Long carbobId) {
		return carbobImageRepository.findCarbobImageByCarbob_CarbobId(carbobId)
			.orElseGet(() ->
				CarbobImage.builder()
					.imageUrl("DEFAULT_IMAGE_URL")
					.build())
			.getImageUrl();
	}
}
