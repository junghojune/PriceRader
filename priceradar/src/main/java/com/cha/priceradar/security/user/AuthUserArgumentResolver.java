package com.cha.priceradar.security.user;

import com.cha.priceradar.security.jwt.JwtProvider;
import com.cha.priceradar.security.jwt.TokenStatus;
import com.cha.priceradar.security.jwt.TokenableInfo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        Optional<String> token = jwtProvider.resolveToken(httpServletRequest);

        if (token.isPresent() && jwtProvider.validateAccessToken(token.get()).equals(TokenStatus.VALID)) {
            TokenableInfo tokenableInfo = jwtProvider.parseAccessToken(token.get());

            return AuthUserInfoImpl.builder()
                    .id(tokenableInfo.getId())
                    .userRole(tokenableInfo.getUserRole())
                    .email(tokenableInfo.getEmail())
                    .username(tokenableInfo.getUsername())
                    .token(token.get())
                    .build();
        }

        return null;
    }
}
