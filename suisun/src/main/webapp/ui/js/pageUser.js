var Class = {
		create : function (){
			return function (){
				this.url = '';
				this.currentPage = 1;
				this.pageSize = 1;
				this.power = 3;  //查全部
				this.search = '';
				this.init.apply(this,arguments);
			};
		}
};

var pageUtil = Class.create();

pageUtil.prototype = {
		init : function (){
			this.url = arguments[0];
			this.currentPage = arguments[1];
			this.pageSize = arguments[2];
			this.power = arguments[3];
			this.search = arguments[4];
		},
		next : function (){
			
			this.currentPage = (Number(this.currentPage)+Number(1))<this.pageSize?(Number(this.currentPage)+Number(1)):this.pageSize;
			this.submit();
		},
		per : function (){
			this.currentPage = (Number(this.currentPage)-Number(1))>1?(Number(this.currentPage)-Number(1)):1;
			this.submit();
		},
		jump : function (){
			var p = document.getElementsByName('currentPage')[0].value;
			this.currentPage = (p>0&&p<=this.pageSize)?p:this.currentPage;
			this.submit();
		},
		submit : function (){
			if(this.power == undefined){
				location.href = this.url + '&currentPage=' + this.currentPage + '&power=2' + '&search='+this.search;
			} else {
				location.href = this.url + '&currentPage=' + this.currentPage + '&power='+this.power + '&search='+this.search;
			}
		}
};