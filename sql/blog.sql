CREATE TABLE IF NOT EXISTS `wp_commentmeta` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`id`),
  KEY `comment_id` (`comment_id`),
  KEY `id` (`meta_key`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT '章评论额外信息表';


CREATE TABLE IF NOT EXISTS `wp_comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `comment_post_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `comment_author` tinytext NOT NULL,
  `comment_author_email` varchar(100) NOT NULL DEFAULT '',
  `comment_author_url` varchar(200) NOT NULL DEFAULT '',
  `comment_author_IP` varchar(100) NOT NULL DEFAULT '',
  `comment_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_date_gmt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_content` text NOT NULL,
  `comment_karma` int(11) NOT NULL DEFAULT '0',
  `comment_approved` tinyint(20) NOT NULL DEFAULT '0' COMMENT '评论是否被批准 0: 未批准，1以批准',
  `comment_agent` varchar(255) NOT NULL DEFAULT '',
  `comment_type` varchar(20) NOT NULL DEFAULT '',
  `comment_parent` bigint(20) unsigned NOT NULL DEFAULT '0',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `comment_post_id` (`comment_post_id`),
  KEY `comment_approved_date_gmt` (`comment_approved`,`comment_date_gmt`),
  KEY `comment_date_gmt` (`comment_date_gmt`),
  KEY `comment_parent` (`comment_parent`),
  KEY `comment_author_email` (`comment_author_email`(10))
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 COMMENT '文章评论信息表';


CREATE TABLE IF NOT EXISTS `wp_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `link_url` varchar(255) NOT NULL DEFAULT '' COMMENT '每个链接的URL地址，varchar(255)值，形式为http://开头的地址。',
  `link_name` varchar(255) NOT NULL DEFAULT '' COMMENT '单个链接的名字，varchar(255)值',
  `link_image` varchar(255) NOT NULL DEFAULT '' COMMENT '链接可以被定义为使用图片链接，这个字段用于保存该图片的地址，为varchar(255)值',
  `link_target` varchar(25) NOT NULL DEFAULT '' COMMENT ' 链接打开的方式，有三种，_blank为以新窗口打开，_top为就在本窗口中打开并在最上一级，none为不选择，会在本窗口中打开',
  `link_description` varchar(255) NOT NULL DEFAULT '' COMMENT '链接的说明文字。用户可以选择显示在链接下方还是显示在title属性中',
  `link_visible` varchar(20) NOT NULL DEFAULT 'Y' COMMENT '该链接是否可以，枚举enum(’Y’,’N’)值，默认为Y，即可见',
  `link_owner` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '某个链接的创建人，为一int(11)值，默认是1。(应该对应的就是wp_users.ID)',
  `link_rating` int(11) NOT NULL DEFAULT '0' COMMENT ' 链接的等级，int(11)值。默认为0',
  `link_updated` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `link_rel` varchar(255) NOT NULL DEFAULT '' COMMENT '链接与定义者的关系，由XFN Creator设置',
  `link_notes` mediumtext NOT NULL COMMENT '链接的详细说明',
  `link_rss` varchar(255) NOT NULL DEFAULT '' COMMENT '该链接的RSS地址',
  PRIMARY KEY (`id`),
  KEY `link_visible` (`link_visible`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT '用于保存用户输入到Wordpress中的链接的表';

CREATE TABLE IF NOT EXISTS `wp_option` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `option_name` varchar(191) NOT NULL DEFAULT '',
  `option_value` longtext NOT NULL,
  `autoload` varchar(20) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`id`),
  UNIQUE KEY `option_name` (`option_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=156 COMMENT '用于保存Wordpress相关设置、参数的表。基本配置信息表，通常通过get_option来操作，该表通常作为插件存储数据的一个地方。是用来存储 WordPress 中所有全局选项的数据表';


CREATE TABLE IF NOT EXISTS `wp_postmeta` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  KEY `meta_key` (`meta_key`(191))
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 COMMENT '用于保存文章的元信息(meta)的表。文章额外数据表，例如文章浏览次数，文章的自定义字段等都存储在这里';


CREATE TABLE `wp_post` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_author` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '每篇文章的作者的编号，int(4)值，应该对应的是wp_users.ID',
  `post_date` datetime DEFAULT '0000-00-00 00:00:00' COMMENT ' 每篇文章发表的时间，datetime值。它是GMT时间加上时区偏移量的结果',
  `post_date_gmt` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '每篇文章发表时的GMT(格林威治)时间，datetime值',
  `post_content` longtext NOT NULL COMMENT '每篇文章的具体内容，longtext值。你在后台文章编辑页面中写入的所有内容都放在这里。',
  `post_title` text NOT NULL COMMENT ' 文章的标题',
  `post_excerpt` text NOT NULL COMMENT '文章摘要',
  `post_status` varchar(20) NOT NULL DEFAULT 'publish' COMMENT '文章当前的状态，枚举enum(’publish’,’draft’,’private’,’static’,’object’)值，publish为已 发表，draft为草稿，private为私人内容(不会被公开) ，static(不详)，object(不详)。默认为publish',
  `comment_status` varchar(20) NOT NULL DEFAULT 'open' COMMENT '评论设置的状态，也是枚举enum(’open’,’closed’,’registered_only’)值，open为允许评论，closed为不允 许评论，registered_only为只有注册用户方可评论。默认为open，即人人都可以评论',
  `ping_status` varchar(20) NOT NULL DEFAULT 'open' COMMENT 'ping状态，枚举enum(’open’,’closed’)值，open指打开pingback功能，closed为关闭。默认值是open',
  `post_password` varchar(20) NOT NULL DEFAULT '' COMMENT '文章密码，varchar(20)值。文章编辑才可为文章设定一个密码，凭这个密码才能对文章进行重新强加或修改',
  `post_name` varchar(200) NOT NULL DEFAULT '' COMMENT '文章名，varchar(200)值。这通常是用在生成permalink时，标识某篇文章的一段文本或数字，也即post slug',
  `to_ping` text COMMENT '强制该文章去ping某个URI',
  `pinged` text COMMENT ' 该文章被pingback的历史记录，text值，为一个个的URI',
  `post_modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '文章最后修改的时间，datetime值，它是GMT时间加上时区偏移量的结果',
  `post_modified_gmt` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '文章最后修改的GMT时间，datetime值',
  `post_content_filtered` longtext,
  `post_parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '文章的上级文章的ID，int(11)值，对应的是wp_posts.ID。默认为0，即没有上级文章',
  `guid` varchar(255) NOT NULL DEFAULT '' COMMENT '这是每篇文章的一个地址，varchar(255)值。默认是这样的形式: http://your.blog.site/?p=1，如果你形成permalink功能，则通常会是: 你的Wordpress站点地址+文章名',
  `menu_order` int(11) NOT NULL DEFAULT '0' COMMENT '不详',
  `post_type` varchar(20) NOT NULL DEFAULT 'post' COMMENT '文章类型，具体不详',
  `post_mime_type` varchar(100) NOT NULL DEFAULT '' COMMENT '不详',
  `comment_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论计数',
  PRIMARY KEY (`id`),
  KEY `post_name` (`post_name`(191)),
  KEY `type_status_date` (`post_type`,`post_status`,`post_date`,`id`),
  KEY `post_parent` (`post_parent_id`),
  KEY `post_author` (`post_author`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT=' 用于保存你所有的文章(posts)的相关信息的表。文章信息表，包括了日志、附件、页面等等信息，是WordPress最重要的一个数据表。';

CREATE TABLE IF NOT EXISTS `wp_termmeta` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `term_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`id`),
  KEY `term_id` (`term_id`),
  KEY `meta_key` (`meta_key`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT '分类与文章信息表（wp_posts）、链接表(wp_links)的关联表';

CREATE TABLE IF NOT EXISTS `wp_term` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '',
  `slug` varchar(200) NOT NULL DEFAULT '',
  `term_group` bigint(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `slug` (`slug`(191)),
  KEY `name` (`name`(191))
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 COMMENT '文章分类、链接分类、标签的信息表';


CREATE TABLE IF NOT EXISTS `wp_term_relationship` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `term_taxonomy_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `term_order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`term_taxonomy_id`),
  KEY `term_taxonomy_id` (`term_taxonomy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '分类与文章信息表（wp_post）、链接表(wp_link)的关联表';


CREATE TABLE IF NOT EXISTS `wp_term_taxonomy` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `term_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `taxonomy` varchar(32) NOT NULL DEFAULT '',
  `description` longtext NOT NULL,
  `parent` bigint(20) unsigned NOT NULL DEFAULT '0',
  `count` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `term_id_taxonomy` (`term_id`,`taxonomy`),
  KEY `taxonomy` (`taxonomy`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 COMMENT '分类信息表，区分wp_terms信息的分类类型，有category、link_category和tag三种分类类型';

CREATE TABLE IF NOT EXISTS `wp_usermeta` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `meta_key` (`meta_key`(191))
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 COMMENT '用于保存用户元信息(meta)的表';

INSERT INTO `wp_usermeta` (`id`, `user_id`, `meta_key`, `meta_value`) VALUES
  (1, 1, 'nickname', 'souvc'),
  (2, 1, 'first_name', ''),
  (3, 1, 'last_name', ''),
  (4, 1, 'description', ''),
  (5, 1, 'rich_editing', 'true'),
  (6, 1, 'comment_shortcuts', 'false'),
  (7, 1, 'admin_color', 'fresh'),
  (8, 1, 'use_ssl', '0'),
  (9, 1, 'show_admin_bar_front', 'true'),
  (10, 1, 'wp_capabilities', 'a:1:{s:13:"administrator";b:1;}'),
  (11, 1, 'wp_user_level', '10'),
  (12, 1, 'dismissed_wp_pointers', ''),
  (13, 1, 'show_welcome_panel', '1'),
  (14, 1, 'session_tokens', 'a:2:{s:64:"863288c6d33f046c578de0cafee38ec3b49a0ca4e078088b0065cbafcdd06d3c";a:4:{s:10:"expiration";i:1459817885;s:2:"ip";s:3:"::1";s:2:"ua";s:72:"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0";s:5:"login";i:1459645085;}s:64:"310725109de88bde9eb342337323927d2af5e6c98b5662f715dd1acc1697609f";a:4:{s:10:"expiration";i:1459830580;s:2:"ip";s:3:"::1";s:2:"ua";s:72:"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0";s:5:"login";i:1459657780;}}'),
  (15, 1, 'wp_dashboard_quick_press_last_post_id', '3'),
  (16, 1, 'managenav-menuscolumnshidden', 'a:5:{i:0;s:11:"link-target";i:1;s:11:"css-classes";i:2;s:3:"xfn";i:3;s:11:"description";i:4;s:15:"title-attribute";}'),
  (17, 1, 'metaboxhidden_nav-menus', 'a:2:{i:0;s:12:"add-post_tag";i:1;s:15:"add-post_format";}'),
  (18, 1, 'wp_user-settings', 'mfold=o'),
  (19, 1, 'wp_user-settings-time', '1459658009');

CREATE TABLE IF NOT EXISTS `wp_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT ,
  `user_login` varchar(60) NOT NULL DEFAULT '' COMMENT '用户的注册名称',
  `user_pass` varchar(255) NOT NULL DEFAULT '' COMMENT '用户密码，varchar(64)值，这是经过加密的结果。好象用的是不可逆的MD5算法',
  `user_nicename` varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `user_email` varchar(100) NOT NULL DEFAULT '' COMMENT '用户电邮地址',
  `user_url` varchar(100) NOT NULL DEFAULT '' COMMENT '用户网址',
  `user_registered` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '用户注册时间',
  `user_activation_key` varchar(255) NOT NULL DEFAULT '' COMMENT '用户激活码',
  `user_status` int(11) NOT NULL DEFAULT '0' COMMENT '用户状态，int(11)值，默认为0',
  `display_name` varchar(250) NOT NULL DEFAULT '' COMMENT '来前台显示出来的用户名字',
  PRIMARY KEY (`id`),
  KEY `user_login_key` (`user_login`),
  KEY `user_nicename` (`user_nicename`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 COMMENT '用于保存Wordpress使用者的相关信息的表';
