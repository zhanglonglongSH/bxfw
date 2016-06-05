/**
 * 
 * 包名路径:app/view
 * 
 * 文件说明:上传文件公用view
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.view.commonview.UploadFilesView', {
	extend : 'Ext.window.Window',
	alias : 'widget.uploadfilesview',
	title : "附件上传",
	width : 300,
	modal : true,
	layout : "fit",
	bodyPadding : '10 10 0',
	defaults : {
		anchor : '100%',
		allowBlank : false,
		msgTarget : 'side',
		width : 200,
		labelWidth : 40
	},
	initComponent : function() {
		this.items = [ {
			itemId : "uploadForm",
			xtype : 'form',
			fileUpload : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '附件描述',
				width : 260,
				labelWidth : 60
			}, {
				xtype : 'filefield',
				fieldLabel : '选择文件',
				name : 'file',
				width : 260,
				labelWidth : 60,
				buttonText : '',
				buttonConfig : {
					iconCls : 'upload-icon'
				}
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
				action : 'uploadFilesSave',
				minWidth : 80,
				text : '上传'
			}, {
				action : 'cancelUploadFiles',
				minWidth : 80,
				text : '取消'
			} ]
		} ]
		this.callParent();
	}
});