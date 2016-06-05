/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明: 部门管理
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.system.PositionEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.positioneditview',
	title : "新增",
	width : 400,
	height : 260,
	modal : true,
	layout : "fit",
	initComponent : function() {
		this.items = [ {
			itemId : 'save-position',
			xtype : 'form',
			bodyPadding : 5,
			defaults : {
				anchor : '100%',
				labelWidth : 100
			},
			items : [ {
				xtype : 'fieldset',
				title : '添加职位',
				items : [ {
					xtype : 'textfield',
					hidden : true,
					id : 'positionCodeId',
					name : 'positionCode',
					value : '0'
				}, {
					id : 'orgCodeId',
					name : 'orgCode',
					fieldLabel : '选择公司',
					xtype : 'combobox',
					labelWidth : 80,
					width : 200,
					queryMode : 'local',
					displayField : 'constanName',
					valueField : 'constantCode',
					emptyText : '请选择'
				}, {
					id : 'deptCodeId',
					name : 'deptCode',
					fieldLabel : '选择部门',
					xtype : 'combobox',
					labelWidth : 80,
					width : 200,
					queryMode : 'local',
					displayField : 'deptName',
					valueField : 'deptCode',
					emptyText : '请选择'
				}, {
					xtype : 'textfield',
					labelWidth : 80,
					width : 300,
					id : 'positionNameId',
					name : 'positionName',
					fieldLabel : '职位名称',
					allowBlank : false
				}, {
					xtype : 'textarea',
					id : 'noteId',
					name : 'note',
					labelWidth : 80,
					width : 300,
					fieldLabel : '备注信息'
				} ]
			} ]
		} ];
		this.dockedItems = [ {
			xtype : 'toolbar',
			dock : 'bottom',
			ui : 'footer',
			layout : {
				pack : 'center'
			},
			items : [ {
				iconCls : 'icon-save',
				action : 'savePositionInfo',
				minWidth : 40,
				text : '保存'
			}, {
				iconCls : 'icon-save',
				action : 'saveAddPositionInfo',
				minWidth : 80,
				text : '保存继续添加'
			}, {
				iconCls : 'icon-reset',
				action : 'cancelSavePosition',
				minWidth : 40,
				text : '取消'
			} ]
		} ]
		this.callParent();
	}
});