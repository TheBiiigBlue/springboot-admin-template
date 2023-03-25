DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
                       user_id VARCHAR(32) NOT NULL   COMMENT '用户id' ,
                       user_name VARCHAR(255)    COMMENT '用户名称' ,
                       password VARCHAR(90)    COMMENT '登录密码' ,
                       gender VARCHAR(90)    COMMENT '性别' ,
                       birthday VARCHAR(90)    COMMENT '生日' ,
                       birth_month INT    COMMENT '生日月份' ,
                       phone VARCHAR(90)    COMMENT '联系电话' ,
                       create_time TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间' ,
                       update_time TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
                       PRIMARY KEY (user_id)
)  COMMENT = '用户表';
