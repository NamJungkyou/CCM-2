$.extend($.fn, {
	dropdownMultiCheckbox : function(){
		/* 드롭다운 selectbox */
		var topParent = this;
		
		/* 전체선택 input */
		this.find("dd>ul").prepend("<li><input type='checkbox' class='allmulitchk'>전체선책</li>");
		this.setCheckedCnt();
		
		//
		this.find("dt").on('click',function(){
			$(".dropdownBox dt").not(this).nextAll().filter("dd").hide();
			
			$(this).nextAll().filter("dd").slideToggle("fast");
		});
		
		this.find(".allmultichk").on('click',function(e){
			var chk = $(this).is(':checked');
			if(chk){
				$(this).parent().nextAll().find("input[type='checkbox'][class!='allmultichk']").attr('checked',true);
			}
			else{
				$(this).parent().nextAll().find("input[type='checkbox'][class!='allmultichk']").attr('checked',false);
			}
			topParent.setCheckedCnt();
		});
		
		this.find("dd input[type='checkbox'][class!='allmultichk']").on('click',function(e){
			topParent.setCheckedCnt();
		});
		
		$('body').mousedown(function(e){
			$('.dropdownBox').each(function(){
				if($(this).find("dd").css('display')=='block'){
					if(!$(this).has(e.target).length){
						$(this).find("dd").hide();
					}
				}
			});
		});
	},
	
	setCheckedCnt : function(){
		var chk_cnt = this.find("dd input[type='checkbox'][class!='allmultichk']:checked").length;
		var all_cnt = this.find("dd input[type='checkbox'][class!='allmultichk']").length;
		if(chk_cnt == all_cnt){
			this.find(".multiCheckValues").html("전체선택");
			this.find("input[type='checkbox'][class='allmultichk']").attr('checked',true);
		}
	}
});