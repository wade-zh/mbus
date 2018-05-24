// *************************************************************************//
// ! This is main JS file that contains custom scripts used in this template*/
// *************************************************************************//
/**
	Navigation File

	01. Carousel
	02. Grid Masonry
	03. Fixed Header
	04. Slick Carousel
	05. Contact input
	06. Sorting
	07. Calculator
	08. Mobile Button
	00. Carousel Two

 */

$( document ).ready(function() {
	"use strict";

	// **********************************************************************//
	// 01. Carousel
	// **********************************************************************//
	$("#news-owl-carousel").owlCarousel({
		items: 4,
		pullDrag: true,
		margin: 10,
		loop: true,
		responsiveClass:true,
		touchDrag: true,
		lazyLoad:true,
		dots: true,
		responsive : {
			0:{
				items: 1
			},
			768:{
				items: 2
			},
			992:{
				items: 4
			}
		}
	});
	$(".main-slider-logo-block").owlCarousel({
		items: 5,
		pullDrag: true,
		loop: true,
		touchDrag: true,
		lazyLoad:true,
		dots: true,
		responsive : {
			0:{
				items: 2
			},
			480:{
				items: 2
			},
			768:{
				items: 3
			},
			992:{
				items: 5
			}
		}
	});

	// **********************************************************************//
	// 02. Grid Masonry
	// **********************************************************************//
	$('.grid').masonry({
		itemSelector: '.grid-item'
	});

	// **********************************************************************//
	// 03. Fixed Header
	// **********************************************************************//

	$(window).on( 'scroll', function(){
		var sc = $(window).scrollTop()
		var header = $('header');
		if (sc > 60) {
			header.addClass("fixed")
		} else {
			header.removeClass("fixed")
		}
	});

	// **********************************************************************//
	// 04. Slick Carousel
	// **********************************************************************//
	$('.team-slider').slick({
		slidesToShow: 4,
		centerMode: true,
		infinite: true,
		responsive: [
			{
				breakpoint: 1200,
				settings: {
					slidesToShow: 3,
				}
			},
			{
				breakpoint: 768,
				settings: {
					slidesToShow: 1,
				}
			}
		],
		nextArrow: '<div class="next"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="15" viewBox="0 0 38 28"><defs><path id="vl9xa" d="M1500.64 5486.14a1.21 1.21 0 0 1 0 1.7l-12.67 12.8a1.2 1.2 0 0 1-1.7 0 1.22 1.22 0 0 1 0-1.71l10.63-10.73h-32.7a1.2 1.2 0 0 1-1.2-1.2c0-.68.54-1.22 1.2-1.22h32.7l-10.63-10.72a1.22 1.22 0 0 1 0-1.72 1.2 1.2 0 0 1 1.7 0z"/></defs><g><g transform="translate(-1463 -5473)"><use fill="#20add0" xlink:href="#vl9xa"/></g></g></svg></div>',
		prevArrow: '<div class="prev"><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="15" viewBox="0 0 38 28"><defs><path id="nvcba" d="M1377.36 5486.14a1.21 1.21 0 0 0 0 1.7l12.67 12.8a1.2 1.2 0 0 0 1.7 0c.47-.47.47-1.24 0-1.71l-10.63-10.73h32.7a1.2 1.2 0 1 0 0-2.42h-32.7l10.63-10.72c.47-.48.47-1.24 0-1.72a1.2 1.2 0 0 0-1.7 0z"/></defs><g><g transform="translate(-1377 -5473)"><use fill="#20add0" xlink:href="#nvcba"/></g></g></svg></div>',
	});

	// **********************************************************************//
	// 05. Contact input
	// **********************************************************************//
	$('form.custom-form').find('input, textarea').on('keyup blur focus', function (e) {
		var $this = $(this),
		label = $this.prev('label');
		if (e.type === 'keyup') {
				if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.addClass('active highlight');
			}
		} else if (e.type === 'blur') {
			if( $this.val() === '' ) {
					label.removeClass('active highlight');
				} else {
					label.removeClass('highlight');
				}
		} else if (e.type === 'focus') {
		if( $this.val() === '' ) {
				label.removeClass('highlight');
				}
		else if( $this.val() !== '' ) {
				label.addClass('highlight');
			}
		}
	});

	// **********************************************************************//
	// 06. Sorting
	// **********************************************************************//
	$('.name').on('click', function(e){
		e.preventDefault();
		var sortAsc = $(this).hasClass('asc'),
			$table  = $('#table'),
			$rows   = $('tbody > tr', $table);

		$rows.sort(function(a, b) {
			var keyA = $('td',a).text();
			var keyB = $('td',b).text();
			if (sortAsc) {
				return (keyA > keyB) ? 1 : 0;
			} else {
				return (keyA < keyB) ? 1 : 0;
			}
		});

		$rows.each(function(index, row){
			$table.append(row);
		});
	})

	// **********************************************************************//
	// 07. Calculator
	// **********************************************************************//
	function updateResults() {
		var foo = parseFloat($('.power').val());
			foo = Math.round(foo + 13.99);
		$('.cost').val(foo);

		// Change Month Value
		var foo2 = parseFloat($('.cost-per').val());
			foo2 = Math.round(foo2 * 2);
		var $output_month = $('output.month');
			$output_month.val("$" + foo2 + "K");

		// Change Day Value
			foo = Math.round(foo * 2);
		var $output_day = $('output.day');
			$output_day.val(foo + "%");

		// Change Day2 Value
		var result_day = foo * 2;
		var $output_day = $('output.day2');
			$output_day.val("$" + result_day);

		// Change Week Value
		var result = foo / 2;
		var $output_week = $('output.week2');
			$output_week.val("$" + result + "K");

		// Change Month2 Value
		var result_month = foo + 2;
		var $output_month2 = $('output.month2');
			$output_month2.val("$" + result_month + "K");

		// Change Year2 Value
		var result_year = foo + 2.5;
		var $output_year = $('output.year2');
			$output_year.val("$" + result_year + "M");

		// Change Day2 Value
		var result_day2 = result_day * 2;
		var $output_day2 = $('output.day3');
			$output_day2.val("Ƀ" + result_day2);

		// Change Week2 Value
		var result_month2 = result + 2;
		var $output_week2 = $('output.week3');
			$output_week2.val("Ƀ" + result_month2);

		// Change Month2 Value
		var result_month3 = result_month + 2;
		var $output_month3 = $('output.month3');
			$output_month3.val("Ƀ" + result_month3);

		// Change Year3 Value
		var result_year2 = result_year + 2;
		var $output_year2 = $('output.year3');
			$output_year2.val("Ƀ" + result_year2);

		// Change Day4 Value
		var result_day3 = result_day2 * 2;
		var $output_day4 = $('output.day4');
			$output_day4.val("$" + result_day3);

		// Change Week4 Value
		var result_week4 = result_month2 * 2;
		var $output_week4 = $('output.week4');
			$output_week4.val("$" + result_week4);

		// Change Month4 Value
		var result_month4 = result_month3 * 2;
		var $output_month4 = $('output.month4');
			$output_month4.val("$" + result_month4);

		// Change Week4 Value
		var result_year4 = result_year2 * 2;
		var $output_year4 = $('output.year4');
			$output_year4.val("$" + result_year4);
	}

	// Global vars for Calculator
	var consumption = $('.consumption');
	var cost_per = $('.cost-per');
	var power = $('.power');

	// Change Consumption and Cost-per Value
	power.on('keyup', function(){
		var foo = parseFloat($(this).val());
			foo = Math.round(foo * 50);
			consumption.val(foo);

		var result = foo * 2;
			cost_per.val(result);
			updateResults();
	});

	// Change Power and Cost-per Value
	consumption.on('keyup', function(){
		var foo = parseFloat($(this).val());
			foo = Math.round(foo / 50);
			power.val(foo);

		var result = foo * 2;
			cost_per.val(result);
			updateResults();
	});

	// Change Consumption and Power Value
	cost_per.on('keyup', function(){
		var foo = parseFloat($(this).val());
			foo = Math.round(foo / 50);
			power.val(foo);

		var foo2 = parseFloat($(this).val());
			foo2 = Math.round(foo2 * 50);
			consumption.val(foo2);
			updateResults();
	});

	// **********************************************************************//
	// 08. Mobile Button
	// **********************************************************************//
	var menu = $('.header-block');
	$('.mobile-btn').on('click', function(){
		menu.toggle("slow");
		$(this).toggleClass('active');
	});

	$('.list-inline-item.top-sub-menu').on('click', function(){
		$(this).parent().find('.sub-menu').toggleClass('active');
		$(this).toggleClass('active');
	});

	// **********************************************************************//
	// 00. Carousel Two
	// **********************************************************************//
	var sync1 = $("#sync1");
	var sync2 = $("#sync2");
	var slidesPerPage = 3; //globaly define number of elements per page
	var syncedSecondary = true;

	sync1.owlCarousel({
		items : 1,
		slideSpeed : 2000,
		nav: true,
		autoplay: true,
		loop: true,
		responsiveRefreshRate : 200,

		navText: ['<img src="assets/img/slider-arrow-left.svg">', '<img src="assets/img/slider-arrow-right.svg">'],
	}).on('changed.owl.carousel', syncPosition);

	sync2
	.on('initialized.owl.carousel', function () {
		sync2.find(".owl-item").eq(0).addClass("current");
	})
	.owlCarousel({
		items : slidesPerPage,
		smartSpeed: 200,
		slideSpeed : 500,
		slideBy: slidesPerPage, //alternatively you can slide by 1, this way the active slide will stick to the first item in the second carousel
		responsiveRefreshRate : 100
	}).on('changed.owl.carousel', syncPosition2);

	function syncPosition(el) {
		//if you set loop to false, you have to restore this next line
		//var current = el.item.index;

		//if you disable loop you have to comment this block
		var count = el.item.count-1;
		var current = Math.round(el.item.index - (el.item.count/2) - .5);

		if(current < 0) {
			current = count;
		}
		if(current > count) {
			current = 0;
		}
		//end block
		sync2
			.find(".owl-item")
			.removeClass("current")
			.eq(current)
			.addClass("current");
		var onscreen = sync2.find('.owl-item.active').length - 1;
		var start = sync2.find('.owl-item.active').first().index();
		var end = sync2.find('.owl-item.active').last().index();

		if (current > end) {
			sync2.data('owl.carousel').to(current, 100, true);
		}
		if (current < start) {
			sync2.data('owl.carousel').to(current - onscreen, 100, true);
		}
	}
	function syncPosition2(el) {
		if(syncedSecondary) {
			var number = el.item.index;
			sync1.data('owl.carousel').to(number, 100, true);
		}
	}
	sync2.on("click", ".owl-item", function(e){
		e.preventDefault();
		var number = $(this).index();
		sync1.data('owl.carousel').to(number, 300, true);
	});

});
