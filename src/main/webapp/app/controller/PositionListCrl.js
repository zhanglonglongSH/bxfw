/**
 * 用户信息管理
 * 
 * 包名路径:app/view
 * 
 * 文件说明:左侧菜单事件控制
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.controller.PositionListCrl', {
	extend : 'Ext.app.Controller',
	requires : [ 'BXFW.view.system.PositionListView', 
	'BXFW.view.system.PositionEditView',
	'BXFW.view.system.PositionAuthorizeListView' ],
	refs : [ {
		ref : 'positionlistview',
		selector : 'positionlistview'
	}, {
		ref : 'positioneditview',
		selector : 'positioneditview'
	}, {
		ref : 'positionauthorizelistview',
		selector : 'positionauthorizelistview'
	} ],
	stores : [ 'PositionListStore' ],
	init : function() {
		this.control({
			'positionlistview' : {
				render : this.initPanel,
				itemdblclick : this.itemdblclickEvent
			},
			'positionlistview button[action=searchInfo]' : {
				click : this.searchInfo
			},
			'positionlistview button[action=addPosition]' : {
				click : this.addPosition
			},
			'positioneditview button[action=savePositionInfo]' : {
				click : this.savePosition
			},
			'positioneditview button[action=saveAddPositionInfo]' : {
				click : this.saveAddPosition
			},
			'positioneditview button[action=cancelSavePosition]' : {
				click : this.cancelSavePosition
			},
			'positionlistview actioncolumn' : {
				itemclick : this.itemclick
			},
			'positioneditview combobox[name=orgCode]' : {
				change : this.orgChange
			}
		});
	},
	/**
	 * panle初始化
	 * @param {} obj
	 * @param {} eOpts
	 */
	initPanel : function(obj, eOpts) {
		var _self = this;
	    _self.gridLoad();
	},
	/**
	 * 初始化页面基础数据
	 * @return {}
	 */
	infoObj : function() {
		var _base64util = Ext.widget('base64util');
		var _positionlistview;
		Ext.getCmp('mainTab').items.each(function(item) {
			if (item.items.length > 0 && item.items.get(0).xtype == 'positionlistview') {
				_positionlistview = item.items.get(0);
			}
		});
		return {
			base64util : _base64util,
			positionlistview:_positionlistview,
			orgType:_positionlistview.orgtype
		};
	},
	/**
	 * 弹出添加职位信息窗口
	 * @param {} btn
	 */
	addPosition : function(btn) {
		var _self = this;
		var _addPosition = Ext.widget('positioneditview');
		_addPosition.show();
		_self.infoOrgData();
	},
	/**
	 * 
	 * @param {} formData
	 */
	updatePosition:function(formData){
		var _self = this;
		var _addPosition = Ext.widget('positioneditview');
		if (formData != null && formData.positionName != null) {
			_addPosition.height=220;
			Ext.getCmp('orgCodeId').hide();
			Ext.getCmp('deptCodeId').hide();
			Ext.getCmp('positionCodeId').setValue(formData.positionCode);
			Ext.getCmp('deptCodeId').setValue(formData.deptCode);
			Ext.getCmp('positionNameId').setValue(formData.positionName);
			Ext.getCmp('noteId').setValue(formData.note);
		}
		_addPosition.show();
	},
	/**
	 * 查询职位信息
	 * @param {} btn
	 */
	searchInfo:function(btn){
		var _self = this;
		var _parm={};
		btn.ownerCt.items.each(function(item) {
			if (item.name == 'searchDeptName') {
				_parm.searchDeptName=item.getValue();
			}
		});
		_self.gridLoad(_parm);
	},
	/**
	 * 保存职位信息
	 * @param {} btn
	 */
	savePosition : function(btn) {
		this.savePositionInfo(btn, true);
	},
	/**
	 * 保存职位信息
	 * @param {} btn
	 */
	saveAddPosition : function(btn) {
		this.savePositionInfo(btn, false);
	},
	/**
	 * 关闭窗口
	 * @param {} btn
	 */
	cancelSavePosition : function(btn) {
		var win = btn.up("window");
		win.close();
	},
	/**
	 * 双击表格事件处理
	 * @param {} grid
	 * @param {} record
	 */
	itemdblclickEvent : function(grid, record) {
		var _self = this;
		var formData = record.data;
		_self.updatePosition(formData);
	},
	/**
	 * 操作栏按钮事件处理
	 * @param {} column
	 * @param {} grid
	 * @param {} rowIndex
	 * @param {} colIndex
	 * @param {} node
	 * @param {} e
	 * @param {} record
	 */
	itemclick : function(column, grid, rowIndex, colIndex, node, e, record) {
		var _self = this;
		if (node.action == 'gridedit') {
			var _formData = grid.getStore().data.items[rowIndex].data;
			_self.addPosition(_formData);
		}
		if (node.action == 'griddelete') {
			var _formData = {
				positionCode : grid.getStore().data.items[rowIndex].data.positionCode
			};
			_self.delPositionInfo(_formData);
		}
	},
	/**
	 * 公司下拉框值改变事件
	 * @param {} btn
	 */
	orgChange:function(btn){
		var _self = this;
		_self.infoDeptData(btn.getValue());
	},
	/**
	 * 保存部门
	 * @param {} btn
	 * @param {} winstate
	 * @return {Boolean}
	 */
	savePositionInfo : function(btn, winstate) {
		var _self = this;
		var _win = btn.up("window");
		var _form = _win.getComponent('save-position');
		var _formData = _form.getValues();
		if (_formData.deptCode == '') {
			Ext.MessageBox.alert('提示', '请选择部门');
			return false;
		}
		if (_formData.positionName == '') {
			Ext.MessageBox.alert('提示', '职位名称不能为空');
			return false;
		}
		var _base64util = _self.infoObj().base64util;
		Ext.Ajax.request({
			url : _projectName+'/system/savePositionInfo/'
					+ _base64util.base64(encodeURIComponent(JSON
							.stringify(_formData))),
			method : 'POST',
			success : function(response, options) {
				_self.gridLoad();
				if (!winstate) {
					Ext.getCmp('deptCodeId').setValue('');
					Ext.getCmp('positionNameId').setValue('');
					Ext.getCmp('noteId').setValue('');
				} else {
					_win.close();
				}
			},
			failure : function(response, options) {
				Ext.MessageBox.alert('提示', '职位信息保存失败 ');
			}
		});
	},
	/**
	 * 删除部门信息 
	 * @param {} formData
	 */
	delPositionInfo : function(formData) {
		var _self = this;
		var _base64util = _self.infoObj().base64util;
		Ext.Ajax.request({
			url : _projectName+'/system/delPositionInfo/'
					+ _base64util.base64(encodeURIComponent(JSON
							.stringify(formData))),
			method : 'DELETE',
			success : function(response, options) {
				_self.gridLoad();
			},
			failure : function(response, options) {
				Ext.MessageBox.alert('提示', '删除部门信息失败 ');
			}
		});
	},
    /**
     * 初始化公司下拉框数据
     */
	infoOrgData:function(){
		var _self = this;
		var _base64util = _self.infoObj().base64util;
		var _parmData = _base64util.base64(encodeURIComponent(JSON.stringify({
			constanType:_self.infoObj().orgType,
			companyCode:1,
			dataType:2
		})));
		var storeData = new Ext.data.Store({
	        autoLoad: true,
	        model : "BXFW.model.OrgModel",
	        proxy: {
	        	type : 'rest',
	        	method: 'GET',
		        url:_projectName+'/system/getSysConstant/'+_parmData,
		        reader : {
					type : 'json',
					root : 'data'
				}
	        }
	    });
		Ext.getCmp('orgCodeId').store=storeData;
	},
	/**
	 * 初始化部门下拉框数据
	 * @param {} orgId
	 */
	infoDeptData:function(orgId){
		var _self = this;
		var _base64util = _self.infoObj().base64util;
		var _parmData = _base64util.base64(encodeURIComponent(JSON.stringify({
			orgCode : orgId,
			orgType : _self.infoObj().orgType
		})));
		//初始化store
	    if(Ext.getCmp('deptCodeId').store.storeId == 'ext-empty-store'){
	    	var storeData = new Ext.data.Store({
		        autoLoad: false,
		        model : "BXFW.model.DepartmentModel",
		        proxy: {
		        	type : 'rest',
		        	method: 'GET',
			        url:_projectName+'/system/getDeptInfoList/'+_parmData,
			        reader : {
						type : 'json',
						root : 'data'
					}
		        }
		    });
	        Ext.getCmp('deptCodeId').store=storeData;
	        Ext.getCmp('deptCodeId').store.load();
	    }else{
	    	var deptStore=Ext.getCmp('deptCodeId').store;
	    	deptStore.getProxy().url = _projectName+"/system/getDeptInfoList/"+ _parmData;
		    deptStore.load();   
	    }
	},
	/**
	 * 加载列表数据
	 */
	gridLoad : function(parms) {
		var _self = this;
		var _base64util = _self.infoObj().base64util;
		var _parmData = _base64util.base64(encodeURIComponent(JSON.stringify({
			orgType:_self.infoObj().orgType,
			deptName:parms==null?'':parms.searchDeptName
		})));
		var _positionlistviewStore = this.infoObj().positionlistview.store;
		_positionlistviewStore.getProxy().url = _projectName+"/system/getPositionInfoList/"
				+ _parmData;
		_positionlistviewStore.load();
	}
});