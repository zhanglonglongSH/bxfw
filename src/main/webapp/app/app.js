/* 动态加载依赖的前提 */
var _projectName='/bxfw';
Ext.Loader.setConfig({
   enabled: true
});
Ext.application({
    autoCreateViewport: true,
    name: 'BXFW',
    appFolder: _projectName+'/app',
    controllers: ['SysMenuCrl','LifeInsuranceMenuCrl','ProxyPeopleMenuCrl','KPIMenuCrl','CompanyListCrl','DepartmentListCrl','PositionListCrl','UserCrl','InsuranceTypeListCrl','InsuranceCompanyListCrl'],
    launch: function() {
        Ext.tip.QuickTipManager.init();
    },
    findTab: function(tabPanel,  record) {
    	
    },
    activateTab: function(tabPanel, targetTab) {
	
    },
    widget: function(tabPanel, controllerName, widgetName, record, cfg) {
	
    }
});
