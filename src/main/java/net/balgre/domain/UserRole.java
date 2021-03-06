package net.balgre.domain;

import lombok.Data;

/**
 * Created by user on 2017-04-07 오후 3:25
 * Prac / PACKAGE_NAME
 * No pain, No gain!
 * What :
 * Why :
 * How : 상품 - 로그인시 해당 API 사용 GET /api/v1/product/detail    GET /api/v1/product/list/{page}   GET /api/v1/product/list/{parent}/{page}
 * 위시리스트 - 로그인 필요 POST /api/v1/wish/add   GET /api/v1/wish/check   DELETE /api/v1/wish/delete   GET /api/v1/wish/list
 * 이벤트 - 로그인시 해당 API 사용 PUT /api/v1/event/attendance   GET /api/v1/event/attendance_list
 * 쿠폰 - 로그인 필요 POST /api/v1/coupon/my   POST /api/v1/coupon/my_coupon   PUT /api/v1/coupon/use
 * 회원 - POST /api/user/join
 *
 */

@Data
public class UserRole {

    private Id id;
    private String role;

}
