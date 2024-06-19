package com.itbiye.controller;


import com.itbiye.pojo.*;
import com.itbiye.service.AdminService;
import com.itbiye.service.ArticleService;
import com.itbiye.service.UserService;
import com.itbiye.utils.JwtUtil;
import com.itbiye.utils.Md5Util;
import com.itbiye.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author youmu
 */
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String adminId, @Pattern(regexp = "^\\S{5,16}$") String password) {

        //查询用户
        Admin a = adminService.findByAdminId(adminId);
        if (a == null) {
            //没有占用
            //注册
            adminService.register(adminId, password);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String adminId, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        Admin loginAdmin = adminService.findByAdminId(adminId);
        //判断该用户是否存在
        if (loginAdmin == null) {
            return Result.error("用户名错误");
        }

        //判断密码是否正确  loginAdmin对象中的password是密文
        if (Md5Util.getMD5String(password).equals(loginAdmin.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginAdmin.getId());
            claims.put("adminId", loginAdmin.getAdminId());
            String token = JwtUtil.genToken(claims);
            //把token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/adminInfo")
    public Result<Admin> AdminInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        //根据用户名查询用户
       /* Map<String, Object> map = JwtUtil.parseToken(token);
        String adminId = (String) map.get("adminId");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String adminId = (String) map.get("adminId");
        Admin admin = adminService.findByAdminId(adminId);
        return Result.success(admin);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated Admin admin) {
        adminService.update(admin);
        return Result.success();
    }



    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token) {
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用adminService根据用户名拿到原密码,再和old_pwd比对
        Map<String,Object> map = ThreadLocalUtil.get();
        String adminId = (String) map.get("adminId");
        Admin loginAdmin = adminService.findByAdminId(adminId);
        if (!loginAdmin.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        //newPwd和rePwd是否一样
        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }

        //2.调用service完成密码更新
        adminService.updatePwd(newPwd);
        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    @Autowired
   private UserService userService;

    //查询用户列表
    @GetMapping("/userlist")
   public Result <PageBean<User>> GetUser(
                         Integer pageNum,
                         Integer pageSize
    ) {
            PageBean<User> pb =  userService.uerList(pageNum,pageSize);
            return Result.success(pb);

   }

   @DeleteMapping("/userlist")
   public Result UserDelete(Integer id){
        userService.deleteById(id);
        return Result.success();
   }

    @Autowired
    private ArticleService articleService;

    @PostMapping("/manage")
    public Result adminsadd(@RequestBody @Validated Article article) {
        articleService.adminadd(article);
        return Result.success();
    }

    @GetMapping("/manage")
    public Result<PageBean<Article>> adminslist(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.adminslist(pageNum,pageSize,state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article c = articleService.findById(id);
        return Result.success(c);
    }

    @PutMapping("/manage")
    public Result adminsupdate(@RequestBody @Validated(Article.Update.class) Article article){
        articleService.adminsupdate(article);
        return Result.success();
    }


    @DeleteMapping("/manage")
    public Result delete(Integer id){
        articleService.deleteById(id);
        return Result.success();
    }




}
