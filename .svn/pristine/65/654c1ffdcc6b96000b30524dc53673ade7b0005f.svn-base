/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明: 系统布局
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.Viewport',
		{
			extend : 'Ext.container.Viewport',
			requires : [ 'BXFW.view.HeadView', 'BXFW.view.SysMenuView',
					'BXFW.view.KPIMenuView', 'BXFW.view.MainView',
					'BXFW.view.LifeInsuranceMenuView',
					'BXFW.view.ProxyPeopleMenuView' ],
			layout : {
				type : 'border'
			},
			// 初始化页面
			initComponent : function() {
				this.items = [ {// head头部信息
					region : 'north',
					height : 80,
					margins : '2 5 2 5',
					items : [{
							bodyStyle : {
								background : 'url('+_projectName+'/styles/images/login/login.jpg)no-repeat #1A94E6',
								backgroundSize : '100%',
								fontSize:'15px',
								textAlign:'right',
								color:'#fff',
								padding : '10 10 3 3'
							},
							
							height : 80,
							html : '公司名称：xxx  部门 ：xxx   职位： xxx  工号: xxx  操作人：xxx  &nbsp;&nbsp;&nbsp;<a href="'+_projectName+'/app/login.html">注销登录</a>'
				         }
					 ]
				}, {// 左侧菜单
					region : 'west',
					title : '系统菜单',
					split : true,
					width : 200,
					minWidth : 200,
					maxWidth : 400,
					collapsible : true,
					animCollapse : true,
					margins : '0 0 5 5',
					layout : 'accordion',
					items : [ {
						title : '系统管理',
						iconCls : 'settings',
						autoScroll : true,
						items : [ {
							xtype : 'sysmenuview'
						} ]
					}, {
						title : '代理人',
						iconCls : 'settings',
						items : [ {
							xtype : 'proxypeoplemenuview'
						} ]
					}, {
						title : '人身险',
						iconCls : 'settings',
						items : [ {
							xtype : 'lifeinsurancemenuview'
						} ]
					}, {
						title : 'KPI管理',
						iconCls : 'settings',
						items : [ {
							xtype : 'kpimenuview'
						} ]
					} ]
				}, {// 主页面控制
					id : 'mainTab',
					xtype : 'mainview',
					region : 'center',
					deferredRender : false,
					margins : '0 5 5 0',
					activeTab : 0
				} ];
				this.callParent();
			}
		});