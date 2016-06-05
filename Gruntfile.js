module.exports = function(grunt) {
    grunt.initConfig({
    	timestamp : new Date().getTime(),
    	randomPath : '<%= pkg.version %>-<%=timestamp %>',
        pkg: grunt.file.readJSON('package.json'),
        jshint: {
            files : ['Gruntfile.js']
        },
        clean : {
            dist : ['./src/main/webapp/dist/**']
        },
        requirejs: {
            main: {
                options: {
                    baseUrl: './src/main/webapp/scripts',
                    out: './src/main/webapp/dist/js/<%= randomPath %>-main.min.js',
                    optimizeCss: 'standard',
                    mainConfigFile: 'src/main/webapp/scripts/main.js',
                    name: 'main',
                    excludeShallow: [
                        'jquery',
                        'backbone',
                        'bootstrap',
                        'underscore',
                        'domReady',
                        'text',
                        'ajaxfileupload',
                        'bootstrap_table',
                        'bootstrap_table_zh',
                        'bootstrap_select',
                        'bootstrap_select_zh',
                        'base64',
                        'datetimepicker',
                        'datetimepicker_zh',
                        'fileinput',
                        'fileinput_zh',
                        'ue_config',
                        'ue',
                        'jquery_dragsort',
                        'qrcode',
                        'jquery_cookie',
                        'zeroClipboard',
                        'sco_message',
                        'tableExport',
                        'bootstrap_table_export',
                        'fileSaver'
                    ]
                }
            },
            login: {
                options: {
                    baseUrl: './src/main/webapp/scripts',
                    out: './src/main/webapp/dist/js/<%= randomPath %>-login.min.js',
                    optimizeCss: 'standard',
                    mainConfigFile: 'src/main/webapp/scripts/login.js',
                    name: 'login',
                    excludeShallow: [
                        'jquery',
                        'bootstrap',
                        'underscore',
                        'backbone',
                        'text',
                        'validation',
                        'validation_zh',
                        'base64',
                        'jquery_cookie',
                        'sco_message',
                        'swiper'
                    ]
                }
            },
            resetpwd: {
                options: {
                    baseUrl: './src/main/webapp/scripts',
                    out: './src/main/webapp/dist/js/<%= randomPath %>-resetpwd.min.js',
                    optimizeCss: 'standard',
                    mainConfigFile: 'src/main/webapp/scripts/resetpwd.js',
                    name: 'resetpwd',
                    excludeShallow: [
                        'jquery',
                        'bootstrap',
                        'underscore',
                        'backbone',
                        'text',
                        'domReady',
                        'validation',
                        'validation_zh',
                        'base64',
                        'jquery_cookie',
                        'sco_message'
                    ]
                }
            },
            register: {
                options: {
                    baseUrl: './src/main/webapp/scripts',
                    out: './src/main/webapp/dist/js/<%= randomPath %>-register.min.js',
                    optimizeCss: "standard",
                    mainConfigFile: 'src/main/webapp/scripts/register.js',
                    name: 'register',
                    excludeShallow: [
                        'jquery',
                        'bootstrap',
                        'underscore',
                        'backbone',
                        'text',
                        'validation',
                        'validation_zh',
                        'sha1',
                        'jquery_cookie',
                        'sco_message'
                    ]
                }
            }
        },
        uglify : {
            static_target : {
                files: [
                    {src: './src/main/webapp/rely/ueditor/ueditor.all.js', dest: './src/main/webapp/rely/ueditor/ueditor.all.min.js'}
                ]
            },
            Constant: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/Constant.min.js': ['./src/main/webapp/mobile/scripts/models/Constant.js']
                }
              },
              Alert: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/Alert.min.js': ['./src/main/webapp/mobile/scripts/utils/Alert.js']
                }
              },
              ChartUtil: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/ChartUtil.min.js': ['./src/main/webapp/mobile/scripts/utils/ChartUtil.js']
                }
              },
              CommonUtil: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/CommonUtil.min.js': ['./src/main/webapp/mobile/scripts/utils/CommonUtil.js']
                }
              },
              sha1: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/sha1.min.js': ['./src/main/webapp/mobile/scripts/utils/sha1.js']
                }
              },
              share: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/share.min.js': ['./src/main/webapp/mobile/scripts/utils/share.js']
                }
              },
              AnnouncementView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-AnnouncementView.min.js': ['./src/main/webapp/mobile/scripts/views/AnnouncementView.js']
                }
              },
              BottomView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/BottomView.min.js': ['./src/main/webapp/mobile/scripts/views/BottomView.js']
                }
              },
              HomeView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-HomeView.min.js': ['./src/main/webapp/mobile/scripts/views/HomeView.js']
                }
              },
              ListView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-ListView.min.js': ['./src/main/webapp/mobile/scripts/views/ListView.js']
                }
              },
              ProductView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-ProductView.min.js': ['./src/main/webapp/mobile/scripts/views/ProductView.js']
                }
              },
              RegisterView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-RegisterView.min.js': ['./src/main/webapp/mobile/scripts/views/RegisterView.js']
                }
              },
              SingleView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-SingleView.min.js': ['./src/main/webapp/mobile/scripts/views/SingleView.js']
                }
              },
              RoadshowView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-RoadshowView.min.js': ['./src/main/webapp/mobile/scripts/views/RoadshowView.js']
                }
              },
              ActivityView: {
                options: {
                    report: "min",
                    footer:'\n/*! <%= pkg.name %> 最后修改于： <%= grunt.template.today("yyyy-mm-dd") %> */'//添加footer
                },
                files: {
                    './src/main/webapp/mobile/scripts/dist/<%= randomPath %>-ActivityView.min.js': ['./src/main/webapp/mobile/scripts/views/ActivityView.js']
                }
              }
        },
        'string-replace' : {
            main:{
                src:'./src/main/webapp/views/index.html',
                dest:'./',
                options:{
                    replacements:[{
                        pattern:'../scripts/main',
                        replacement:'../dist/js/<%= randomPath %>-main.min'
                    }]
                }
            },
            login:{
                src:'./src/main/webapp/login.html',
                dest:'./',
                options:{
                    replacements:[{
                        pattern:'./scripts/login',
                        replacement:'./dist/js/<%= randomPath %>-login.min'
                    }]
                }
            },
            resetpwd:{
                src:'./src/main/webapp/views/resetpwd.html',
                dest:'./',
                options:{
                    replacements:[{
                        pattern:'../scripts/resetpwd',
                        replacement:'../dist/js/<%= randomPath %>-resetpwd.min'
                    }]
                }
            },
            register:{
                src:'./src/main/webapp/views/register.html',
                dest:'./',
                options:{
                    replacements:[{
                        pattern:'../scripts/register-none',
                        replacement:'../dist/js/<%= randomPath %>-register.min'
                    }]
                }
            },
            util:{
                src:'./src/main/webapp/mobile/scripts/*/*.min.js',
                dest:'./',
                options:{
                    replacements:[{
                        pattern:'utils/CommonUtil',
                        replacement:'dist/CommonUtil.min'
                    },{
                        pattern:'models/Constant',
                        replacement:'dist/Constant.min'
                    },{
                        pattern:'views/BottomView',
                        replacement:'dist/BottomView.min'
                    },{
                        pattern:'utils/sha1',
                        replacement:'dist/sha1.min'
                    },{
                        pattern:'utils/share',
                        replacement:'dist/share.min'
                    },{
                        pattern:'utils/ChartUtil',
                        replacement:'dist/ChartUtil.min'
                    },{
                        pattern:'utils/Alert',
                        replacement:'dist/Alert.min'
                    }]
                }
            },
            views:{
                src:'./src/main/webapp/mobile/views/templates/*.ftl',
                dest:'./',
                options:{
                    replacements:[{
                        pattern:'../mobile/scripts/views/AnnouncementView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-AnnouncementView.min.js'
                    },{
                        pattern:'../mobile/scripts/views/SingleView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-SingleView.min.js'
                    },{
                        pattern:'../mobile/scripts/views/HomeView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-HomeView.min.js'
                    },{
                        pattern:'../mobile/scripts/views/ListView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-ListView.min.js'
                    },{
                        pattern:'../mobile/scripts/views/ProductView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-ProductView.min.js'
                    },{
                        pattern:'../mobile/scripts/views/RegisterView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-RegisterView.min.js'
                    },{
                        pattern:'../mobile/scripts/views/RoadshowView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-RoadshowView.min.js'
                    },{
                        pattern:'../mobile/scripts/views/ActivityView.js',
                        replacement:'../mobile/scripts/dist/<%= randomPath %>-ActivityView.min.js'
                    }]
                }
            }
        }
    });
    
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-requirejs');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-string-replace');
  
    grunt.registerTask('default',['jshint','clean','requirejs','uglify','string-replace']);
};
