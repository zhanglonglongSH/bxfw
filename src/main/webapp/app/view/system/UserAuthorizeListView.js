/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明:用户授权
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.system.UserAuthorizeListView', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.userauthorizelistview',
	requires : [ 'Ext.data.*', 'Ext.grid.*', 'Ext.util.*',
			'Ext.toolbar.Paging', 'Ext.ux.ProgressBarPager' ],
	xtype : 'progress-bar-pager',
	stripeRows : true,
	height : 533,
	initComponent : function() {
		this.store = 'UserStore';
		this.columns = [ {
			text : '省公司',
			dataIndex : 'companyName'
		}, {
			text : '市公司',
			dataIndex : 'companyName'
		}, {
			text : '营业部',
			dataIndex : 'companyName'
		}, {
			text : '业务部',
			dataIndex : 'companyName'
		}, {
			text : '部门',
			dataIndex : 'companyName'
		}, {
			text : '职位',
			dataIndex : 'companyName'
		}, {
			text : '姓名',
			dataIndex : 'companyName'
		}, {
			text : '内勤工号',
			dataIndex : 'companyName'
		}, {
			text : '状态',
			dataIndex : 'companyName'
		}, {
			text : '操作',
			dataIndex : 'companyName',
			renderer : function() {
				return '<a href="#"">权限设置</a>';
			}
		} ];
		this.tbar = [ {
			xtype : 'textfield',
			name : 'email',
			width : 200,
			emptyText : '输入工号',
			margin : '0 6 0 0'
		}, {
			xtype : 'textfield',
			name : 'email',
			width : 100,
			emptyText : '输入姓名',
			margin : '0 6 0 0'
		}, {
			text : '查询',
			xtype : 'button',
			action : "searchInfo"
		} ];
		this.callParent(arguments);
	}
});