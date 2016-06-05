/**
 * 用户信息管理
 * 
 * 包名路径:app/view
 * 
 * 文件说明:机构信息管理
 * 
 * 作者姓名:yaojj
 * 
 * 创建日期:2016年4月10日 下午10:04:08
 * 
 * 修改日期:
 * 
 */
Ext.define('BXFW.controller.CompanyListCrl', {
	extend : 'Ext.app.Controller',
	requires : [ 'BXFW.view.system.CompanyListView', 'BXFW.view.system.CompanyEditView',
			'BXFW.util.Base64Util' ],
	refs : [ {
		ref : 'companylistview',
		selector : 'companylistview'
	}, {
		ref : 'companyeditview',
		selector : 'companyeditview'
	} ],
	stores : [ 'CompanyListStore','OrgStore'],
	init : function() {
		this.control({
			'companylistview' : {
				render : this.initPanel,
				itemdblclick : this.itemdblclickEvent
			},
			'companylistview button[action=searchInfo]' : {
				click : this.searchInfo
			},
			'companylistview button[action=addComapny]' : {
				click : this.addCompany
			},
			'companyeditview button[action=saveCompanyInfo]' : {
				click : this.saveCompanyInfo
			},
			'companyeditview button[action=saveAddCompanyInfo]' : {
				click : this.saveAddCompanyInfo
			},
			'companyeditview button[action=cancelSaveCompany]' : {
				click : this.cancelSaveCompany
			},
			'companylistview actioncolumn' : {
				itemclick : this.itemclick
			},
			'companyeditview combobox[name=orgCode]' : {
				change : this.orgChange
			}
		});
	},
	/**
	 * 处理事件方法
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
		var _base64util = Ext.create('BXFW.util.Base64Util');
		var _companyListView;
		Ext.getCmp('mainTab').items.each(function(item) {
			if (item.items.length > 0 && item.items.get(0).xtype == 'companylistview') {
				_companyListView = item.items.get(0);
			}
		});
		//自定义隐藏列
		if(_companyListView.orgtype == 1){
			_companyListView.columns[1].hidden=true;
			_companyListView.columns[2].hidden=true;
			_companyListView.columns[3].hidden=true;
		}if(_companyListView.orgtype == 2){
			_companyListView.columns[2].hidden=true;
			_companyListView.columns[3].hidden=true;
		}if(_companyListView.orgtype == 3){
			_companyListView.columns[3].hidden=true;
		}
		return {
			base64util : _base64util,
			companyListView : _companyListView,
			orgType:_companyListView.orgtype
		};
	},
	/**
	 * 添加机构
	 * @param {} formData
	 */
	addCompany : function(btn) {
		var _self = this;
		var _addCompany = Ext.create('BXFW.view.system.CompanyEditView');
		var _orgType=_self.infoObj().orgType;
		if(_orgType == 1){
		  _self.infoOrgSelectData('orgCodeId1','',1,1);
		}if(_orgType == 2){
			_addCompany.height=280;
			Ext.getCmp('orgCodeId2').show();
			_self.infoOrgSelectData('orgCodeId1','',1,2);//查询所有机构
		}if(_orgType == 3 || _orgType == 4){
			_addCompany.height=300;
			Ext.getCmp('orgCodeId2').show();
			Ext.getCmp('orgCodeId3').show();
			_self.infoOrgSelectData('orgCodeId1','',1,2);//查询所有机构
			Ext.getCmp('orgCodeId3').fieldLabel=_orgType==3?'选    择    县':'营    业    部';
		}
		_addCompany.show();
	},
	/**
	 * 修改机构信息
	 * @param {} formData
	 */
	updateCompany : function(formData) {
		var _self = this;
		var _addCompany = Ext.create('BXFW.view.system.CompanyEditView');
		var _orgType=_self.infoObj().orgType;
		Ext.getCmp('orgCodeId1').hide();
		_addCompany.height=220;
		_addCompany.show();
		if (formData != null) {
			if(_orgType == 1){
				Ext.getCmp('orgIds').setValue(formData.id);
				Ext.getCmp('orgNameId').setValue(formData.orgName);
				Ext.getCmp('noteId').setValue(formData.note);
			}if(_orgType == 2){
				Ext.getCmp('orgIds').setValue(formData.id);
				Ext.getCmp('orgNameId').setValue(formData.cityOrgName);
				Ext.getCmp('noteId').setValue(formData.note);
			}if(_orgType == 3){
				Ext.getCmp('orgIds').setValue(formData.id);
				Ext.getCmp('orgNameId').setValue(formData.countyOrgName);
				Ext.getCmp('noteId').setValue(formData.note);
			}if(_orgType == 4){
				Ext.getCmp('orgIds').setValue(formData.id);
				Ext.getCmp('orgNameId').setValue(formData.hallOrgName);
				Ext.getCmp('noteId').setValue(formData.note);
			}
		}
	},
	/**
	 * 自定义查询机构信息
	 */
	searchInfo : function(btn) {
		var _self = this;
		var _parm={};
		btn.ownerCt.items.each(function(item) {
			if (item.name == 'searchOrgName') {
				_parm.searchOrgName=item.getValue();
			}
		});
		_self.gridLoad(_parm);
	},
	/**
	 * 保存机构
	 * @param {} btn
	 */
	saveCompanyInfo : function(btn) {
		this.ajaxCompanyInfo(btn, true);
	},
	/**
	 * 保存并继续添加
	 * @param {} btn
	 */
	saveAddCompanyInfo : function(btn) {
		this.ajaxCompanyInfo(btn, false);
	},
	/**
	 * 取消保存
	 * @param {} btn
	 */
	cancelSaveCompany : function(btn) {
		var _win = btn.up("window");
		_win.close();
	},
	/**
	 * 选择机构下拉框事件
	 * @param {} btn
	 */
	orgChange:function(btn){
		var _self = this;
		var _orgType=_self.infoObj().orgType;
		if(_orgType == 2 && btn.id == 'orgCodeId1'){//市机构
			var _topComboxValue=btn.getValue();
			_self.infoOrgSelectData('orgCodeId2',_topComboxValue,2,1);//查询常量表
		}if(_orgType == 3 && btn.id == 'orgCodeId1'){//营业部
			var _topComboxValue=btn.getValue();
			_self.infoOrgSelectData('orgCodeId2',_topComboxValue,2,2);//查询市机构列表
		}if(_orgType == 3 && btn.id == 'orgCodeId2'){//业务部
			var _topComboxValue=btn.getValue();
			_self.infoOrgSelectData('orgCodeId3',_topComboxValue,3,1);//查询营业部列表
		}if(_orgType ==4 && btn.id == 'orgCodeId1'){//营业部
			var _topComboxValue=btn.getValue();
			_self.infoOrgSelectData('orgCodeId2',_topComboxValue,2,2);//查询市机构列表
		}if(_orgType ==4 && btn.id == 'orgCodeId2'){//业务部
			var _topComboxValue=btn.getValue();
			_self.infoOrgSelectData('orgCodeId3',_topComboxValue,3,2);//查询营业部列表
		}
		var _orgName=Ext.getCmp('orgCodeId'+(_orgType==4?3:_orgType)).getRawValue();
		Ext.getCmp('orgNameId').setValue(_orgName);
	},
	/**
	 * 双击grid事件
	 * @param {} grid
	 * @param {} record
	 */
	itemdblclickEvent : function(grid, record) {
		var _self = this;
		var formData = record.data;
		_self.updateCompany(formData);
	},
	/**
	 * 操作栏中按钮事件
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
			_self.updateCompany(_formData);
		}
		if (node.action == 'griddelete') {
			var _formData = {
				orgId : grid.getStore().data.items[rowIndex].data.id,
				orgType : _self.infoObj().orgType
			};
			_self.delCompanyInfo(_formData);
		}
	},
	/**
	 * 删除机构
	 */
	delCompanyInfo : function(formData) {
		var _self = this;
		var _base64util = _self.infoObj().base64util;
		Ext.Ajax.request({
			url : _projectName+'/system/delCompanyInfo/'
					+ _base64util.base64(encodeURIComponent(JSON
							.stringify(formData))),
			method : 'DELETE',
			success : function(response, options) {
				_self.gridLoad();
			},
			failure : function(response, options) {
				Ext.MessageBox.alert('提示', '删除机构信息失败 ');
			}
		});
	},
	/**
	 * 保存机构
	 */
	ajaxCompanyInfo : function(btn, winstate) {
		var _self = this;
		var _win = btn.up("window");
		var _form = _win.getComponent('save-company');
		var _formData = _form.getValues();
		var _orgType=_self.infoObj().orgType;
		if(_formData.orgId == '0' || _formData.orgId == ''){//只有新增时候才验证此选项
			_formData.orgId = '0';
			if (Ext.getCmp('orgCodeId1').getValue() == null) {
				Ext.MessageBox.alert('提示', '请选择省份');
				return;
			}
			//判断市机构下面输入框
			if(_orgType == 2){
			    _formData.parentCode=Ext.getCmp('orgCodeId1').getValue();
			    if (Ext.getCmp('orgCodeId2').getValue() == null) {
					Ext.MessageBox.alert('提示', '请选择市');
					return;
				}
			}
			//判断营业厅下面输入框
			if(_orgType == 3){
			   _formData.parentCode=Ext.getCmp('orgCodeId2').getValue();
			    if (Ext.getCmp('orgCodeId2').getValue() == null) {
					Ext.MessageBox.alert('提示', '请选择市');
					return;
				}
				if (Ext.getCmp('orgCodeId3').getValue() == null) {
					Ext.MessageBox.alert('提示', '请选营业部');
					return;
				}
			}
			//业务部
			if(_orgType == 4){
			   _formData.parentCode=Ext.getCmp('orgCodeId3').getValue();
			    if (Ext.getCmp('orgCodeId2').getValue() == null) {
					Ext.MessageBox.alert('提示', '请选择市');
					return;
				}
				if (Ext.getCmp('orgCodeId3').getValue() == null) {
					Ext.MessageBox.alert('提示', '请选营业部');
					return;
				}
			}
		}
		if (_formData.orgName == '') {
			Ext.MessageBox.alert('提示', '机构名称不能为空');
			return;
		}
		_formData.orgCode=Ext.getCmp('orgCodeId'+(_orgType==4?3:_orgType)).getValue();
		_formData.orgType=_orgType;
		var _base64util = _self.infoObj().base64util;
		Ext.Ajax.request({
			url : _projectName+'/system/saveCompanyInfo/'
					+ _base64util.base64(encodeURIComponent(JSON.stringify(_formData))),
			method : 'POST',
			success : function(response, options) {
				_self.gridLoad();
				if (!winstate) {
					Ext.getCmp('orgIds').setValue('');
					Ext.getCmp('orgNameId').setValue('');
					Ext.getCmp('noteId').setValue('');
				} else {
					_win.close();
				}
			},
			failure : function(response, options) {
				Ext.MessageBox.alert('提示', '机构信息保存失败 ');
			}
		});
	},
	/**
	 * 公用查询，查询机构基本信息
	 */
	gridLoad : function(parms) {
		var _self = this;
		var _base64util = _self.infoObj().base64util;
		var _parmData = _base64util.base64(encodeURIComponent(JSON.stringify({
			orgType : _self.infoObj().orgType,
			orgName : parms==null?'':parms.searchOrgName
		})));
		var _companyListViewStore = this.infoObj().companyListView.store;
		_companyListViewStore.getProxy().url = _projectName+"/system/getCompanyInfoList/"
				+ _parmData;
		_companyListViewStore.load();
	},
	/**
	 * 初始化下拉框数据
	 * @param {} orgCodeId
	 * @param {} constantCode
	 * @param {} constanType 如果取常量值就按常量类型判断，如果取机构就判断取那张表
	 * @param {} dataType 代表是取常量数据还是去机构数据
	 */
	infoOrgSelectData:function(orgCodeId,constantCode,constanType,dataType){
		var _self = this;
		var _base64util = _self.infoObj().base64util;
		var _orgType=_self.infoObj().orgType;
		var _parmData = _base64util.base64(encodeURIComponent(JSON.stringify({
			constantCode : constantCode,
			constanType:constanType,
			companyCode:1,
			dataType:dataType
		})));
		//初始化store
	   if(Ext.getCmp(orgCodeId).store.storeId == 'ext-empty-store'){
	    	var storeData = new Ext.data.Store({
		        autoLoad: false,
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
	        Ext.getCmp(orgCodeId).store=storeData;
	        Ext.getCmp(orgCodeId).store.load();
	    }else{
	    	var _store=Ext.getCmp(orgCodeId).store;
	    	_store.getProxy().url = _projectName+"/system/getSysConstant/"+ _parmData;
		    _store.load();   
	    }
	}
});