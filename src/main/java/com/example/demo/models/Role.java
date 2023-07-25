package com.example.demo.models;

import com.example.demo.security.Permission;
import com.sun.jdi.PrimitiveValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.Permission.*;

@RequiredArgsConstructor
public enum Role {
    SUPERADMIN
            (
            Set.of(
                    Super_ADMIN_ADD_ADMIN,
                    Super_ADMIN_ADD_ADMIN_TYPE,
                    Super_ADMIN_DELETE_ADMIN,
                    Super_ADMIN_UPDATE_ADMIN,
                    SUPER_ADMIN_VIEW_ROOMS,
                    SUPER_ADMIN_VIEW_AMENTIES,
                    SUPER_ADMIN_VIEW_PACKAGES,
                    SUPER_ADMIN_VIEW_DISCOUNTS,
                    SUPER_ADMIN_VIEW_SUBSCRIBERS,
                    SUPER_ADMIN_ADD_SESSION,
                    SUPER_ADMIN_ADD_PACKAGE,
                    SUPER_ADMIN_ADD_DISCOUNT,
                    SUPER_ADMIN_DELETE_SESSION,
                    SUPER_ADMIN_DELETE_PACKAGE,
                    SUPER_ADMIN_DELETE_DISCOUNT

            )

    )
    ,
    ADMIN
            (
            Set.of(
                    ADMIN_ADD_SESSION,
                    ADMIN_ADD_PACKAGE,
                    ADMIN_ADD_DISCOUNT,
                    ADMIN_DELETE_SESSION,
                    ADMIN_DELETE_PACKAGE,
                    ADMIN_DELETE_DISCOUNT

            )
    )
    ,
    USER
            (
            Set.of(

                    USER_FILL_INFO,
                    USER_UPDATE_INFO,
                    USER_VIEW_PACKAGES,
                    USER_SUBSCRIBE,
                    USER_VIEW_SUBSCRIPTION,
                    USER_APPLY_DISCOUNT,
                    USER_BOOK_SESSION,
                    USER_CANCEL_SESSION
            )
    )

    ;



    @Getter
        private  final Set<Permission> permissions;
        public List<SimpleGrantedAuthority> getAuthorities(){
            var authorities = getPermissions()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toList());
              authorities.add(new SimpleGrantedAuthority(("ROLE_" + this.name())));
              return authorities;

        }

}
