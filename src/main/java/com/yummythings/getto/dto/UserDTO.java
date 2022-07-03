package com.yummythings.getto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yummythings.getto.domain.Member;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public class UserDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    private Set<AuthorityDTO> authorityDtoSet;

    public static UserDTO from(Member member) {
        if(member == null) return null;

        return UserDTO.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .authorityDtoSet(member.getAuthorities().stream()
                        .map(authority -> AuthorityDTO.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
