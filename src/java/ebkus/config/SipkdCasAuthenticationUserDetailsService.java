/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ebkus.model.Pengguna;
import ebkus.services.LoginServices;
import ebkus.util.SipkdHelpers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Zainab
 */
public class SipkdCasAuthenticationUserDetailsService implements AuthenticationUserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(SipkdCasAuthenticationUserDetailsService.class);
    @Autowired
    LoginServices loginServices;

    @Override
    public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {
        final String nameTahun = authentication.getName();
        final String nama = SipkdHelpers.splitString(nameTahun, "|", 0);
        final String password = authentication.getCredentials().toString();
        final Pengguna hasil = loginServices.loadUserByUsername(nama, password);

        log.debug(new StringBuilder().append(hasil).append(" ==TESSSSSSS  spdcas =  = ").append(nama).append("  = ").append(password).toString());
        //if (hasil != null && hasil.isEnabled() && name.equals(hasil.getUsername()) && hasil.getPassword().equals(password)) 
        if (hasil != null && hasil.isIsAktif() && Objects.equals(nama, hasil.getNamaPengguna())) {
            final List<SimpleGrantedAuthority> grantedAuths = new ArrayList<SimpleGrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority(hasil.getKodeGrup()));
            return new User(nameTahun, password, grantedAuths);
        } else {
            return null;
        }
    }

}
