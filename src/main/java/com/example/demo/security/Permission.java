package com.example.demo.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    //sadmin
    Super_ADMIN_ADD_ADMIN("super_admin:add_admin"),
    Super_ADMIN_ADD_ADMIN_TYPE("super_admin:add_admin_type"),
    Super_ADMIN_DELETE_ADMIN("super_admin:delete_admin"),
    Super_ADMIN_UPDATE_ADMIN("super_admin:update_admin"),
    SUPER_ADMIN_VIEW_ROOMS("super_admin:view_rooms"),
    SUPER_ADMIN_VIEW_AMENTIES("super_admin:view_amenities"),
    SUPER_ADMIN_VIEW_PACKAGES("super_admin:view_packages"),
    SUPER_ADMIN_VIEW_DISCOUNTS("super_admin:view_discounts"),
    SUPER_ADMIN_VIEW_SUBSCRIBERS("super_admin:view_subscribers"),
    SUPER_ADMIN_ADD_SESSION("super_admin:add_session"),
    SUPER_ADMIN_ADD_PACKAGE("super_admin:add_package"),
    SUPER_ADMIN_ADD_DISCOUNT("super_admin:add_package"),
    SUPER_ADMIN_DELETE_SESSION("super_admin:delete_session"),
    SUPER_ADMIN_DELETE_PACKAGE("super_admin:delete_package"),
    SUPER_ADMIN_DELETE_DISCOUNT("super_admin:delete_package"),
    //admin
    ADMIN_ADD_SESSION("admin:add_session"),
    ADMIN_ADD_PACKAGE("admin:add_package"),
    ADMIN_ADD_DISCOUNT("admin:add_package"),
    ADMIN_DELETE_SESSION("admin:delete_session"),
    ADMIN_DELETE_PACKAGE("admin:delete_package"),
    ADMIN_DELETE_DISCOUNT("admin:delete_package"),
    //user
    USER_FILL_INFO("user:fill_info"),
    USER_UPDATE_INFO("user:update_info"),
    USER_VIEW_PACKAGES("user:view_package"),
    USER_SUBSCRIBE("user_subscriber"),
    USER_APPLY_DISCOUNT("user_apply_discount"),
    USER_BOOK_SESSION("user_book_session"),
    USER_CANCEL_SESSION("user_cancel_session")
    ;
//log out and delete account
    @Getter
    private final String permission;
}
