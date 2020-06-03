# JirathBlog2.0
this project is a rebuilded one of blog system,use Spring Boot ,Shiro, a project for the one who finish learning SpringBoot and hope to use it to do something.
本项目是博客的重新构造，计划使用到前端的框架，后端的消息队列，安全框架Shiro，完成一个稳定运行的个人博客项目。
刚刚学完SpringBoot希望找一个入门项目的，可以来参考这个项目，觉得海星麻烦点个star。

## 开始使用

配置部分全部在配置文件完成，
### Shiro配置
 - 配置系统的状态码，默认给了6个，如有需要自行添加，对应MsgValueUtil，返回的状态码对应了接口返回对象中的code
 - 配置Shiro的盐值 盐值必须是固定的，否则没办法解析，考虑过用根据用户信息的动态盐值，但是没有必要做这个
 - 配置Shiro的身份名，与数据库有对应关系，其中0为默认，1位管理，2为拥有者
    - 在使用过程中，配置文件与Realm中身份是List型式传递的
    - 数据库的0-n,对应配置文件的第一个到最后一个
    - 登录高级会自动开启低级的身份 
### 博客功能配置 
 - 博客每页数量， blog-num-every-page: (yourNum)
### 博客跨域设置（重要）
由于jar包运行，必然涉及到跨域相关，在后端配置如下：
使用通配跨域，利用SpringMVC的api，在conf->cors中配置，
````java
@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:63343")
                        .allowCredentials(true)
                        .allowedMethods("*")
                        .maxAge(3600);
            }
        };
    }
````
Mapping 为开放的接口
Origins为对应的前端请求源地址,在配置文件中修改
Credentials开启：ajaxPOST提交需要将SessionId附加在cookie中
Methods:允许的方式
maxAge:最大连接时间
allowHeaders暂时未用
前端ajax格式：
````javascript
 function addPsg(data) {
                    $.ajax({
                        //请求方式
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                        //地址
                        url: "http://localhost/admin/blog/add",
                        //允许ajaxPost将SessionId传在cookie中
                        xhrFields: {
                            withCredentials: true
                        },
                        //数据，json字符串
                        data: data,
                        success: function (msg) {
                            console.info(msg);
                            //跳转
                            if (msg.code == 100) {
                                var num=msg.data.blogId;
                                window.location="./blog.html?id="+num;
                            } else {
                                window.location = "./error.html";
                            }
                        },
                        failed: function(){
                            window.alert("提交失败");
                        }
                    });
                }
````
 ## 目前进度
 大体完成，等待前端做完对接接口，接口文档待完成
 修改了dao层，查询文章输出为倒序，修补了修改文章的漏洞
 
 #### 2020年3月8日17:27:01
 完成了博客最后的配置，准备尝试在未来添加dubbo或springcloud的微服务来接入更多功能
 
 #### 2020年6月3日17:58:20
 砍去登录与注册功能，使用邮箱替代，增加了留言功能。增加了系统接口。需要完善：评论删除，前端对接