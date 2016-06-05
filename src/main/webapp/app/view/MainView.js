/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明: tab切换主页面
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.MainView', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.mainview',
	initComponent : function() {
		this.items = [ {
			id : 'menu-999-999',
			contentEl : 'center1',
			title : '工作区域',
			closable : false,
			autoScroll : true,
			margin : '5 0 0 0',
			layout : "column",
			items : [ {
				columnWidth : 1 / 2,
				padding : '5 5 5 5',
				items : [ {
					title : '本周工作安排',
					height : 260,
					border : true,
					html : 'sdfsfsafs'
				}, {
					title : '绩效考核',
					border : true,
					height : 260,
					html : 'sdfsfsafs'
				} ]
			}, {
				columnWidth : 1 / 2,
				padding : '5 5 5 5',
				items : [ {
					title : '今日计划',
					height : 260,
					border : true,
					html : 'sdfsafa'
				}, {
					title : '本月考核',
					border : true,
					height : 260,
					html : 'sdfsfsafs'
				} ]
			} ]
		} ];
		this.callParent();
	}
});