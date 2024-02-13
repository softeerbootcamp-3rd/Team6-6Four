package com.softeer.team6four.domain.carbob.presentation;

import com.softeer.team6four.domain.carbob.application.AroundCarbobSearchService;
import com.softeer.team6four.domain.carbob.application.CarbobSearchService;
import com.softeer.team6four.domain.carbob.application.response.AroundCarbobListInfoSummary;
import com.softeer.team6four.domain.carbob.application.response.MyCarbobDetailInfo;
import com.softeer.team6four.domain.carbob.application.response.MyCarbobSummary;
import com.softeer.team6four.global.annotation.Auth;
import com.softeer.team6four.global.response.ResponseDto;
import com.softeer.team6four.global.response.SliceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/carbob")
@RequiredArgsConstructor
public class CarbobController {
    private final CarbobSearchService carbobSearchService;
    private final AroundCarbobSearchService aroundCarbobSearchService;

    @GetMapping(value = "/my")
    public ResponseDto<SliceResponse<MyCarbobSummary>> getMyCarbobList
        (
            @RequestParam MyCarbobSortType sortType,
            @RequestParam(required = false) Long lastCarbobId,
            @RequestParam(required = false) Long lastReservationCount,
            @PageableDefault(size = 6) Pageable pageable
        )
    {
        // TODO : UserContextHold 에서 userId 가져와야함
        Long userId = 1L;
        return carbobSearchService.findMyCarbobList(userId, sortType, lastCarbobId, lastReservationCount, pageable);
    }
    @Auth
    @GetMapping(value = "/main")
    public ResponseDto<List<AroundCarbobListInfoSummary>> getAroundCarbobInfoSummaryList
            (
                    @RequestParam(required = true) Double latitude,
                    @RequestParam(required = true) Double longitude
            )
    {
        return aroundCarbobSearchService.findAroundCarbobInfoSummaryList(latitude,longitude);
    }

    @GetMapping(value = "/detail/{carbobId}")
    public ResponseDto<MyCarbobDetailInfo> getMyCarbobDetail(@PathVariable Long carbobId) {
        // TODO : UserContextHold 에서 userId 가져와야함
        Long userId = 1L;
        return carbobSearchService.findMyCarbobDetailInfo(userId, carbobId);
    }
}
