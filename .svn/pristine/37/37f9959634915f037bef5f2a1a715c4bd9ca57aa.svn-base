/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明:省公司、市公司、营业部、业务部机构部门列表
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.system.DepartmentListView', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.departmentlistview',
	requires : [ 'Ext.data.*', 'Ext.grid.*', 'Ext.util.*',
			'Ext.toolbar.Paging', 'Ext.ux.ProgressBarPager' ],
	xtype : 'progress-bar-pager',
	stripeRows : true,
	height : 533,
	bodyPadding : 5,
	initComponent : function() {
		this.itemId = 'departmentlistview';
		this.store = 'DepartmentListStore';
		this.columns = [ {
			text : '公司名称',
			sortable : true,
			dataIndex : 'orgName'
		}, {
			text : '部门',
			sortable : true,
			dataIndex : 'deptName'
		}, {
			text : '备注',
			sortable : true,
			dataIndex : 'note'
		}, {
			xtype : 'actioncolumn',
			text : '操作',
			tdCls : 'action',
			items : [{
				iconCls : 'icon-edit',
				tooltip : '修改',
				action : 'gridedit',
				handler : function(grid, rowIndex, colIndex,node, e, record) {
					this.fireEvent('itemclick', this, grid,rowIndex, colIndex, node,e, record);
				}
			},'-',
			{
				iconCls : 'icon-delete',
				tooltip : '删除',
				action : 'griddelete',
				handler : function(grid, rowIndex, colIndex,node, e, record) {
					this.fireEvent('itemclick', this, grid,rowIndex, colIndex, node,e, record);
				}
			} ]
		} ];
		this.tbar = [ {
			text : '新增',
			xtype : 'button',
			iconCls : 'icon-add',
			action : "addDepartment"
		}, '-', {
			xtype : 'textfield',
			width : 150,
			itemId : 'searchOrgNameId',
			name : 'searchOrgName',
			emptyText : '请输入公司名称 '
		}, {
			text : '查询',
			xtype : 'button',
			iconCls : 'icon-info',
			action : "searchInfo"
		} ];
		this.callParent(arguments);
	}
});