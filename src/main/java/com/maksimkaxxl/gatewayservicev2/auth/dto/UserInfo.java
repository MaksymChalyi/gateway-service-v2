package com.maksimkaxxl.gatewayservicev2.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@ToString
public class UserInfo {

  private String email;
  private String name;

}
