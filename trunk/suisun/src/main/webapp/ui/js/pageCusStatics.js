var Class = {
	create : function() {
		return function() {
			this.url = '';
			this.currentPage = 1;
			this.pageSize = 1;
			this.albumType = ''; // 查全部
			this.album = '';
			this.orderBy = 3; // 打开次数
			this.init.apply(this, arguments);
		};
	}
};

var pageUtil = Class.create();

pageUtil.prototype = {
	init : function() {
		this.url = arguments[0];
		this.currentPage = arguments[1];
		this.pageSize = arguments[2];
		this.albumType = arguments[3]; // 平台: 0.所有平台 1.mobile 2.pad
		this.album = arguments[4];
		this.orderBy = arguments[5]; // 排序: 1.下载时间 2.最近查看时间 3.打开次数
	},
	next : function() {

		this.currentPage = (Number(this.currentPage) + Number(1)) < this.pageSize ? (Number(this.currentPage) + Number(1))
				: this.pageSize;
		this.submit();
	},
	per : function() {
		this.currentPage = (Number(this.currentPage) - Number(1)) > 1 ? (Number(this.currentPage) - Number(1))
				: 1;
		this.submit();
	},
	jump : function() {
		var p = document.getElementsByName('currentPage')[0].value;
		this.currentPage = (p > 0 && p <= this.pageSize) ? p : this.currentPage;
		this.submit();
	},
	submit : function() {
		location.href = this.url + '&currentPage=' + this.currentPage
				+ '&albumType=' + this.albumType + '&album=' + this.album + '&orderBy='+this.orderBy;
	}
};