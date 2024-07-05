package ebkus.ui.action;

import ebkus.model.LoginForm;
import ebkus.services.LoginServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author User
 */
@Controller
public class LoginAction {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
    @Autowired
    LoginServices loginServices;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(@ModelAttribute LoginForm loginForm) {
        return "login";
    }

    @RequestMapping(value = "/deny", method = RequestMethod.GET)
    public String denied() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        return "login";
    }

    @RequestMapping(value = "/expired", method = RequestMethod.GET)
    public String expired() {
//        Authentication auth = SecurityContextHolder.getContext()
//                .getAuthentication();
//        final Pengguna pengguna = loginServices.loadUserByUsername(((Principal) auth.getPrincipal()).getName(), null);
//        loginServices.deleteLoginStatus(pengguna);
        return "login";
    }
}
