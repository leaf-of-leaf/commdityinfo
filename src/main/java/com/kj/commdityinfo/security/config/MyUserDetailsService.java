package com.kj.commdityinfo.security.config;

import com.kj.commdityinfo.mapper.UserMapper;
import com.kj.commdityinfo.security.bean.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author kj
 * @Date 2020/4/28 16:31
 * @Version 1.0
 * 自定义认证流程
 * 获取对应用户的正确信息，用于之后判断
 */
@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        String sql = "select username,password,authority from " +
//                "authentication where username = ?";
//        List<MyUser> users = jdbcTemplate.query(sql, new Object[]{username}, new RowMapper<MyUser>() {
//            @Override
//            public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
//                MyUser user = new MyUser();
//                user.setUserName(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setAuthority(rs.getString("authority"));
//                return user;
//            }
//        });
        com.kj.commdityinfo.bean.User mybatisUser = userMapper.findUserAndRoleByUserName(username);

        if(mybatisUser == null){
            System.out.println("不存在此用户");
            throw new UsernameNotFoundException("不存在此用户");
        }

        MyUser user = new MyUser();
        user.setUserName(mybatisUser.getName());
        user.setPassword(bCryptPasswordEncoder.encode(mybatisUser.getPassword()));
        user.setAuthority(mybatisUser.getRole().getName());
        System.out.println(user);
//        if(users == null || users.size() != 1) {
//            System.out.println("不存在此用户");
//            throw new UsernameNotFoundException("不存在此用户");
//        }
//        MyUser user = users.get(0);
        User u = new User(user.getUserName(), user.getPassword(),
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthority()));

        return u;
    }

}
