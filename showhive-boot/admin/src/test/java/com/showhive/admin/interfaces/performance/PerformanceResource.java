package com.showhive.admin.interfaces.performance;

import com.showhive.admin.interfaces.BaseResourceTest;
import com.showhive.admin.interfaces.performance.dto.CreatePerformanceRequest;
import com.showhive.category.domain.Category;
import com.showhive.member.domain.Member;
import com.showhive.venue.domain.Venue;
import io.restassured.http.ContentType;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class PerformanceResource extends BaseResourceTest {
    private String accessToken;

    @BeforeEach
    void auth() {
        Member member = memberGenerator.generateAdmin("admin");
        accessToken = tokenManager.createAccessToken(member);
    }

    @DisplayName("공연을 생성할 수 있다.")
    @Test
    void create_performance() {
        Venue venue = venueGenerator.generateVenue("NOL 서경스퀘어 스콘 2관");
        Category category = categoryGenerator.generateNodeCategory("뮤지컬");

        String performanceInfo =
                """
                        <div class="prdContents detail">
                            <div class="content casting">
                                <h3 class="contentTitle">캐스팅</h3>
                                <a href="#" class="castingInfoBtn">캐스팅 일정조회</a>
                                <div class="expandalbeWrap castingBubble ">
                                    <div class="bubblesContainer">
                                        <div class="speechBubble" style="left: 63px; white-space: nowrap; max-width: fit-content;">
                                            <p>김수용 더 알아보기</p>
                                        </div>
                                    </div>
                                    <ul class="castingList">
                                        <li class="castingItem">
                                            <div class="castingTop">
                                                <a class="castingLink" href="#" rel="noopener">
                                                <div class="castingProfile">
                                                    <img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/07/01/0400040701_3736_015158.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">사무엘</div><div class="castingName">김수용</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/08/09/0400040809_10783_022.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">사무엘</div><div class="castingName">이경수</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/13/01/0400041301_31140_021.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">사무엘</div><div class="castingName">박유덕</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/08/03/0400040803_7473_021.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">H</div><div class="castingName">임강성</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/12/08/0400041208_29458_02231.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">H</div><div class="castingName">김지철</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/19/01/0400041901_44220_02119.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">H</div><div class="castingName">이석준</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/12/04/0400041204_28380_02231.600.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">헨리</div><div class="castingName">강찬</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/19/07/0400041907_44974_02.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">헨리</div><div class="castingName">이진우</div></div></li><li class="castingItem"><div class="castingTop"><a class="castingLink" href="#" rel="noopener"><div class="castingProfile"><img src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/24/09/0400042409_54680_02.gif" class="castingImage" alt="프로필 사진"></div></a><a class="castingHeartBtn " data-toggle="self" data-toast="cast" aria-checked="false" aria-label="티켓캐스트 등록/취소" role="checkbox" href="#"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/ic_cast_gray_heart.png" alt="cast_heart"></a></div><div class="castingInfo"><div class="castingActor">헨리</div><div class="castingName">강병훈</div></div></li></ul><a class="contentToggleBtn" data-toggle="expandalbeWrap" role="button" aria-label="여닫기" href="#"></a></div></div><div class="content"><h3 class="contentTitle">공연시간 정보</h3><div class="contentDetail">
                                                    <p class="contentDetailText">예매가능시간: 관람 2시간 전까지</p>
                                                    <ul class="contentDetailList">
                                                        <div>화, 목, 금 8시 / 수 4시, 8시 / 토. 일, 공휴일 2시, 6시 (월 공연 없음)
                                                            <br>* 11/30(일) 14시 공연만 있음 <br>
                                                            <br>※11/26(수) 16시 공연 단체관람으로 인해 일부 좌석 마감되었습니다.
                                                        </div>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="content">
                                                <h3 class="contentTitle">공지사항</h3><div class="contentDetail"><a href="https://ticket.interpark.com/Tiki/Mail/TPMail.asp?Where=will&amp;GPage=https://ticket.interpark.com/tiki/main/TPMail_Coupon.asp?Coupon=AE425DNB" target="_blank"><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/11/2025110717180003.jpg" alt="" style="width: 100%;"></a><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/11/2025111117533835.jpg" alt="" style="width: 100%;"><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/11/2025111414081927.jpg" alt="" style="width: 100%;"><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/9/2025091516103083.jpg" alt="" style="width: 100%;"></div></div><div class="content"><h3 class="contentTitle">할인정보</h3><div class="contentDetail"><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/11/2025111810403329.jpg" alt="" style="width: 100%;"><a href="http://events.interpark.com/exhibition?exhibitionCode=251105003#welcome" target="_blank"><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/11/2025111416202394.jpg" alt="" style="width: 100%;"></a><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/11/2025110514373564.jpg" alt="" style="width: 100%;"><img src="//ticketimage.interpark.com/250108882025/10/10/bec9292b.jpg" style="width: 100%;" alt=""><img src="https://ticketimage.interpark.com/Play/ITM/Data/Modify/2025/9/2025093015534951.jpg" alt="" style="width: 100%;"></div></div><div class="content description"><h3 class="contentTitle false">공연상세 / 캐스팅일정</h3><div class="contentDetail"><meta http-equiv="Content-Type" content="text/html; charset=euc-kr"><title>Untitled Document</title><p><strong><img src="//ticketimage.interpark.com/Play/image/etc/25/25010888-12.jpg" alt="" border="0"></strong></p><p><strong><img src="//ticketimage.interpark.com/Play/image/etc/25/25010888-20.jpg" alt="" border="0"></strong></p></div></div><div class="content gallery"><h3 class="contentTitle"><a href="http://www.playdb.co.kr/magazine/photogallary.asp" class="contentTitleLink">관련 이미지/영상 <span class="countNum">1</span></a></h3><div class="contentProvider"><span class="logo-playdb">PLAY DB</span>제공</div><div class="loadableWrap expandableWrap is-allLoaded is-toggled "><ul class="loadableList galleryList"><li class="galleryItem"><a href="#" class="galleryPlayWrap" data-popup="gallery" data-galleryid="vid0" role="button"><div class="galleryThumb is-video"><img src="//ticketimage.interpark.com/playdb/media/040011/25/11/0400112511_83673_M.wmv.jpg" data-src="//ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040011/25/11/0400112511_83673_M.wmv.png" alt="" class="galleryImage"></div><p class="galleryTitle" style="display: block; width: 100%;"> 뮤지컬 〈윌리엄과 윌리엄의 윌리엄들〉 2025</p></a></li></ul></div></div><div class="content performanceInfo"><div class="performanceInfoHeader"><div class="performanceInfoTitle"><span>뮤지컬 〈윌리엄과 윌리엄의 윌리엄들〉 2025</span><span>더 알아보기</span></div><span class="performanceInfoBtn">공연 홈 바로가기</span></div><div class="performanceInfoContent"><div class="peopleInfo"><img src="//ticketimage.interpark.com/TicketImage/uidev/cast/icon_character_20.png" alt="이미지" style="width: 2rem; height: 2rem;"><span>등장인물/출연진 모아보기</span></div></div></div><div class="content prdStat"><h3 class="contentTitle">예매자 통계</h3><div class="statWrap"><div class="statGender"><strong class="statTitle">성별</strong><div class="statGenderType is-male"><div class="statGenderName">남자</div><div class="statGenderValue">4.6<em class="unit">%</em></div></div><div class="statGenderType is-female"><div class="statGenderName">여자</div><div class="statGenderValue">95.4<em class="unit">%</em></div></div></div><div class="statAge"><strong class="statTitle">연령</strong><div class="statAgeBar"><div class="statAgeType"><div class="statAgePercent">8.6%</div><div class="statBack"><span class="statGraph" data-stat-bar="8.6" style="height: 8px;"></span></div><div class="statAgeName">10대</div></div><div class="statAgeType"><div class="statAgePercent">41.8%</div><div class="statBack"><span class="statGraph" data-stat-bar="41.8" style="height: 36px;"></span></div><div class="statAgeName">20대</div></div><div class="statAgeType"><div class="statAgePercent">31.9%</div><div class="statBack"><span class="statGraph" data-stat-bar="31.9" style="height: 28px;"></span></div><div class="statAgeName">30대</div></div><div class="statAgeType"><div class="statAgePercent">13.4%</div><div class="statBack"><span class="statGraph" data-stat-bar="13.4" style="height: 12px;"></span></div><div class="statAgeName">40대</div></div><div class="statAgeType"><div class="statAgePercent">3.5%</div><div class="statBack"><span class="statGraph" data-stat-bar="3.5" style="height: 4px;"></span></div><div class="statAgeName">50대</div></div></div></div></div></div></div>
                        """;
        CreatePerformanceRequest request = new CreatePerformanceRequest(
                "뮤지컬 〈윌리엄과 윌리엄의 윌리엄들〉 2025",
                venue.getId().getVenueId(),
                Duration.ofHours(2),
                (short) 10,
                "무이자 할부",
                performanceInfo,
                List.of(category.getId().id()),
                LocalDateTime.now().plusDays(10L),
                LocalDateTime.now().plusDays(30L)
        );

        given()
                .contentType(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .when()
                .body(request)
                .post("/admin/v1/performance")
                .then().statusCode(HttpStatus.OK.value());
    }
}
