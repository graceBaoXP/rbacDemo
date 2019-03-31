package cn.grace.web.controller;

import cn.grace.exception.UsersException;
import cn.grace.pojo.Funs;
import cn.grace.pojo.Users;
import cn.grace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userLogin")
    public String userLogin(Users users, Model model, HttpServletRequest request){
        try {
            Users u=this.userService.userLogin(users.getUsername(),users.getUserpwd());
            List<Funs> funs=u.getFuns();
            for(Funs f:funs){
                System.out.println(f.getFunname()+" "+f.getFunurl());
            }
            HttpSession session=request.getSession();
            session.setAttribute("user",u);
        }catch (UsersException e){
            e.printStackTrace();
            model.addAttribute("msg",e.getMessage());
            return "/login";
        }
        return "redirect:/index";
    }
}
