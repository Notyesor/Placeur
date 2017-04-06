/*
 * TODO Copyright
 */

package com.artamonov.placeur.dto;

import java.util.Date;
import java.util.UUID;

public class RegisterInfo {

    String login;
    String password;
    String nickname;
    String name;
    String surname;
    String mail;
    Date birthday;
    UUID city;

    public RegisterInfo() {

    }

    public static RegisterInfo deserialize(String serializedRegisterInfo) {
        return new RegisterInfo();
    }
}
