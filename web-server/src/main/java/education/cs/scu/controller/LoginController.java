package education.cs.scu.controller;

import education.cs.scu.entity.ProbeUser;
import education.cs.scu.entity.User;
import education.cs.scu.entity.UserFlow;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.javautils.ExcelUtil;
import education.cs.scu.service.LoginService;
import education.cs.scu.service.ProbeUserService;
import education.cs.scu.service.ShopService;
import education.cs.scu.service.UserVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProbeUserService probeUserService;




    List<ProbeUser> probeUsers = new ArrayList<ProbeUser>();
    /**
     * 控制用户登登陆
     *
     * @param request    保存session
     * @param userName
     * @param password
     * @return 返回用户昵称
     * @throws Exception
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public User userLogin(HttpServletRequest request,
                          @RequestParam(value = "userName") String userName,
                          @RequestParam(value = "password") String password) throws Exception {
        HttpSession session = request.getSession();
        System.err.println("login session");
        System.err.println(session);

        User user = new User(userName, password);
        System.err.println(userName);
        User loginUser = loginService.doUserLogin(user);

        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            System.out.println("finish:" + loginUser.getNickName());
            return loginUser;
        } else {
            //返回一个空对象
            System.out.println("failed to login");
            return new User();
        }
    }

    /**
     * 控制验证码发送
     * 根据用户名发送验证码
     *
     * @param request
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "verifyCode", method = RequestMethod.GET)
    public User userVerify(HttpServletRequest request,
                           @RequestParam("userName") String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        String res = loginService.verifyCode(user);
        if (res.length() > 0) {
            return user;
        } else {
            return new User();
        }
    }

    @RequestMapping(value = "/userRegist", method = RequestMethod.GET)
    public void userRegist(HttpServletRequest request,
                           @RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "nickName") String nickName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(password);
        System.out.println(userName);
        loginService.doUserRegist(user);
    }
}
