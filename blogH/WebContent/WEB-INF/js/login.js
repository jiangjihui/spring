/**
 *	登陆页面js
 * @Author jjh
 */

;(function () {
	

	'use strict';

	// Placeholder 
	var placeholderFunction = function() {
		$('input, textarea').placeholder({ customClass: 'my-placeholder' });
	}
	
	// Placeholder 
	var contentWayPoint = function() {
		var i = 0;
		$('.animate-box').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('animated-fast') ) {
				
				i++;

				$(this.element).addClass('item-animate');
				setTimeout(function(){

					$('body .animate-box.item-animate').each(function(k){
						var el = $(this);
						setTimeout( function () {
							var effect = el.data('animate-effect');
							if ( effect === 'fadeIn') {
								el.addClass('fadeIn animated-fast');
							} else if ( effect === 'fadeInLeft') {
								el.addClass('fadeInLeft animated-fast');
							} else if ( effect === 'fadeInRight') {
								el.addClass('fadeInRight animated-fast');
							} else {
								el.addClass('fadeInUp animated-fast');
							}

							el.removeClass('item-animate');
						},  k * 200, 'easeInOutExpo' );
					});
					
				}, 100);
				
			}

		} , { offset: '85%' } );
	};
	// On load
	$(function(){
		placeholderFunction();
		contentWayPoint();

	});

}());

/*检查登陆信息是否输入完整 */
function login(form) {
	var name = $(form).children("input[name='name']").val() || $("#username").val();
	if (name == "" || name.length ==0) {
		return false;
	}
	var pwd = $("#password").val();
	if(pwd != "" && pwd.length != 0){
		$("#password").val(hex_sha1(pwd));
		return true;
	}
	return false;
}

/*注册信息校验 */
function signup(form) {
	var name = $(form).children("input[name='loginname']").val() || $("#name").val();
	if (name == "" || name.length ==0) {
		return false;
	}
	var pwd = $("#password").val();
	var repwd = $("#password").val();
	if(repwd != pwd) return false;
	
	if(pwd != "" && pwd.length != 0){
		$("#password").val(hex_sha1(pwd));
		$("#re-password").val(hex_sha1(pwd));
		return true;
	}
	return false;
}