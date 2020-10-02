package com.backend.core.service;

import com.backend.core.concert.Concert;
import com.backend.core.user.User;

public class AuthorizationService {
    public static boolean canUpdateConcert(User user, Concert concert){
        return user.getId().equals(concert.getPerformer().getId());
    }
}
