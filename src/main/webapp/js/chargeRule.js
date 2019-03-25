var service = 'https://customerh5.yiminchuxing.com'
function chargeRule() {
	var administrativeDivisionCode = getUrlParam('administrativeDivisionCode');
	var orderBusinessType = getUrlParam('orderBusinessType');
	var useVehicvarime = getUrlParam('useVehicvarime');
	    useVehicvarime = useVehicvarime ? new Date(useVehicvarime).Format('yyyy-MM-dd') : null;
	var immediately = [];
	var appointment = [];
	var starAircraft = [];
	var endAircraft = [];
	var halfDayRent = [];
	var dailyRent = [];
	
	$.ajax({
		type: "POST",
		url: service + '/orderBusinessTypeChargeRule/semiLogin/list',
		xhrFields : {
			withCredentials : true
		},
		data: {
			administrativeDivisionCode: administrativeDivisionCode,
			orderBusinessType: orderBusinessType,
			useVehicvarime: useVehicvarime
		},
		success: function(res) {
			if(!res.success) {
				$('.container').hide();
				$('.friendly').show();
				return;
			}
			if(res.result[0].vehicvarype.orderBusinessType == 'OBT0001') {
				$('.orderBusinessChargeRuvarype').text('专车');
			} else if(res.result[0].vehicvarype.orderBusinessType == 'OBT0002') {
				$('.orderBusinessChargeRuvarype').text('快车');
			} else {
				$('.orderBusinessChargeRuvarype').text('出租车');
			}

			for(var i = 0; i < res.result.length; i++) {
				if(res.result[i].orderType == 'OT00001') {
					$('.rule-box1').show();
					immediately.push(res.result[i]);
				} else if(res.result[i].orderType == 'OT00002') {
					$('.rule-box2').show();
					appointment.push(res.result[i]);
				} else if(res.result[i].orderType == 'OT00003') {
					$('.rule-box3').show();
					starAircraft.push(res.result[i]);
				} else if(res.result[i].orderType == 'OT00004') {
					$('.rule-box4').show();
					endAircraft.push(res.result[i]);
				} else if(res.result[i].orderType == 'OT00005') {
					$('.rule-box5').show();
					halfDayRent.push(res.result[i]);
				} else if(res.result[i].orderType == 'OT00006') {
					$('.rule-box6').show();
					dailyRent.push(res.result[i]);
				}
			}
			//立即叫车
			var tr = document.getElementById('A_name');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr.appendChild(tdObj);
			}
			var tr_td = tr.getElementsByTagName('td');

			var tr1 = document.getElementById('A_platformServicePrice');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr1.appendChild(tdObj);
			}
			var tr1_td = tr1.getElementsByTagName('td');

			var tr2 = document.getElementById('A_minimumConsumerPrice');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr2.appendChild(tdObj);
			}
			var tr2_td = tr2.getElementsByTagName('td');

			var tr3 = document.getElementById('A_fixedPrice');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr3.appendChild(tdObj);
			}
			var tr3_td = tr3.getElementsByTagName('td');

			var tr4 = document.getElementById('A_longPrice');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr4.appendChild(tdObj);
			}
			var tr4_td = tr4.getElementsByTagName('td');

			var tr5 = document.getElementById('A_durationPrice');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr5.appendChild(tdObj);
			}
			var tr5_td = tr5.getElementsByTagName('td');

			var tr6 = document.getElementById('A_mileagePrice');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr6.appendChild(tdObj);
			}
			var tr6_td = tr6.getElementsByTagName('td');

			var tr66 = document.getElementById('A_waitting');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr66.appendChild(tdObj);
			}
			var tr66_td = tr66.getElementsByTagName('td');

			var tr77 = document.getElementById('A_cancel');
			for(var k = 0; k < immediately.length; k++) {
				var tdObj = document.createElement('td');
				tr77.appendChild(tdObj);
			}
			var tr77_td = tr77.getElementsByTagName('td');

			//预约		
			var tr7 = document.getElementById('B_name');
			for(var k = 0; k < appointment.length; k++) {
				var tdObj = document.createElement('td');
				tr7.appendChild(tdObj);
			}
			var tr7_td = tr7.getElementsByTagName('td');

			var tr8 = document.getElementById('B_platformServicePrice');
			for(var k = 0; k < appointment.length; k++) {
				var tdObj = document.createElement('td');
				tr8.appendChild(tdObj);
			}
			var tr8_td = tr8.getElementsByTagName('td');

			var tr9 = document.getElementById('B_minimumConsumerPrice');
			for(var k = 0; k < appointment.length; k++) {
				var tdObj = document.createElement('td');
				tr9.appendChild(tdObj);
			}
			var tr9_td = tr9.getElementsByTagName('td');

			var tr10 = document.getElementById('B_fixedPrice');
			for(var k = 0; k < appointment.length; k++) {
				var tdObj = document.createElement('td');
				tr10.appendChild(tdObj);
			}
			var tr10_td = tr10.getElementsByTagName('td');

			var tr11 = document.getElementById('B_longPrice');
			for(var k = 0; k < appointment.length; k++) {
				var tdObj = document.createElement('td');
				tr11.appendChild(tdObj);
			}
			var tr11_td = tr11.getElementsByTagName('td');

			var tr12 = document.getElementById('B_durationPrice');
			for(var k = 0; k < appointment.length; k++) {
				var tdObj = document.createElement('td');
				tr12.appendChild(tdObj);
			}
			var tr12_td = tr12.getElementsByTagName('td');

			var tr13 = document.getElementById('B_mileagePrice');
			for(var k = 0; k < appointment.length; k++) {
				var tdObj = document.createElement('td');
				tr13.appendChild(tdObj);
			}
			var tr13_td = tr13.getElementsByTagName('td');

			//接机			
			var trC1 = document.getElementById('C_name');
			for(var k = 0; k < starAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trC1.appendChild(tdObj);
			}
			var trC1_td = trC1.getElementsByTagName('td');

			var trC2 = document.getElementById('C_platformServicePrice');
			for(var k = 0; k < starAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trC2.appendChild(tdObj);
			}
			var trC2_td = trC2.getElementsByTagName('td');

			var trC3 = document.getElementById('C_minimumConsumerPrice');
			for(var k = 0; k < starAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trC3.appendChild(tdObj);
			}
			var trC3_td = trC3.getElementsByTagName('td');

			var trC4 = document.getElementById('C_fixedPrice');
			for(var k = 0; k < starAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trC4.appendChild(tdObj);
			}
			var trC4_td = trC4.getElementsByTagName('td');

			var trC5 = document.getElementById('C_longPrice');
			for(var k = 0; k < starAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trC5.appendChild(tdObj);
			}
			var trC5_td = trC5.getElementsByTagName('td');

			var trC6 = document.getElementById('C_durationPrice');
			for(var k = 0; k < starAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trC6.appendChild(tdObj);
			}
			var trC6_td = trC6.getElementsByTagName('td');

			var trC7 = document.getElementById('C_mileagePrice');
			for(var k = 0; k < starAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trC7.appendChild(tdObj);
			}
			var trC7_td = trC7.getElementsByTagName('td');

			//送机		
			var trD1 = document.getElementById('D_name');
			for(var k = 0; k < endAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trD1.appendChild(tdObj);
			}
			var trD1_td = trD1.getElementsByTagName('td');

			var trD2 = document.getElementById('D_platformServicePrice');
			for(var k = 0; k < endAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trD2.appendChild(tdObj);
			}
			var trD2_td = trD2.getElementsByTagName('td');

			var trD3 = document.getElementById('D_minimumConsumerPrice');
			for(var k = 0; k < endAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trD3.appendChild(tdObj);
			}
			var trD3_td = trD3.getElementsByTagName('td');

			var trD4 = document.getElementById('D_fixedPrice');
			for(var k = 0; k < endAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trD4.appendChild(tdObj);
			}
			var trD4_td = trD4.getElementsByTagName('td');

			var trD5 = document.getElementById('D_longPrice');
			for(var k = 0; k < endAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trD5.appendChild(tdObj);
			}
			var trD5_td = trD5.getElementsByTagName('td');

			var trD6 = document.getElementById('D_durationPrice');
			for(var k = 0; k < endAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trD6.appendChild(tdObj);
			}
			var trD6_td = trD6.getElementsByTagName('td');

			var trD7 = document.getElementById('D_mileagePrice');
			for(var k = 0; k < endAircraft.length; k++) {
				var tdObj = document.createElement('td');
				trD7.appendChild(tdObj);
			}
			var trD7_td = trD7.getElementsByTagName('td');

			//半日租		
			var trE1 = document.getElementById('E_name');
			for(var k = 0; k < halfDayRent.length; k++) {
				var tdObj = document.createElement('td');
				trE1.appendChild(tdObj);
			}
			var trE1_td = trE1.getElementsByTagName('td');

			var trE2 = document.getElementById('E_platformServicePrice');
			for(var k = 0; k < halfDayRent.length; k++) {
				var tdObj = document.createElement('td');
				trE2.appendChild(tdObj);
			}
			var trE2_td = trE2.getElementsByTagName('td');

			var trE3 = document.getElementById('E_minimumConsumerPrice');
			for(var k = 0; k < halfDayRent.length; k++) {
				var tdObj = document.createElement('td');
				trE3.appendChild(tdObj);
			}
			var trE3_td = trE3.getElementsByTagName('td');

			var trE4 = document.getElementById('E_fixedPrice');
			for(var k = 0; k < halfDayRent.length; k++) {
				var tdObj = document.createElement('td');
				trE4.appendChild(tdObj);
			}
			var trE4_td = trE4.getElementsByTagName('td');

			var trE5 = document.getElementById('E_longPrice');
			for(var k = 0; k < halfDayRent.length; k++) {
				var tdObj = document.createElement('td');
				trE5.appendChild(tdObj);
			}
			var trE5_td = trE5.getElementsByTagName('td');

			var trE6 = document.getElementById('E_durationPrice');
			for(var k = 0; k < halfDayRent.length; k++) {
				var tdObj = document.createElement('td');
				trE6.appendChild(tdObj);
			}
			var trE6_td = trE6.getElementsByTagName('td');

			var trE7 = document.getElementById('E_mileagePrice');
			for(var k = 0; k < halfDayRent.length; k++) {
				var tdObj = document.createElement('td');
				trE7.appendChild(tdObj);
			}
			var trE7_td = trE7.getElementsByTagName('td');

			//日租		
			var trF1 = document.getElementById('F_name');
			for(var k = 0; k < dailyRent.length; k++) {
				var tdObj = document.createElement('td');
				trF1.appendChild(tdObj);
			}
			var trF1_td = trF1.getElementsByTagName('td');

			var trF2 = document.getElementById('F_platformServicePrice');
			for(var k = 0; k < dailyRent.length; k++) {
				var tdObj = document.createElement('td');
				trF2.appendChild(tdObj);
			}
			var trF2_td = trF2.getElementsByTagName('td');

			var trF3 = document.getElementById('F_minimumConsumerPrice');
			for(var k = 0; k < dailyRent.length; k++) {
				var tdObj = document.createElement('td');
				trF3.appendChild(tdObj);
			}
			var trF3_td = trF3.getElementsByTagName('td');

			var trF4 = document.getElementById('F_fixedPrice');
			for(var k = 0; k < dailyRent.length; k++) {
				var tdObj = document.createElement('td');
				trF4.appendChild(tdObj);
			}
			var trF4_td = trF4.getElementsByTagName('td');

			var trF5 = document.getElementById('F_longPrice');
			for(var k = 0; k < dailyRent.length; k++) {
				var tdObj = document.createElement('td');
				trF5.appendChild(tdObj);
			}
			var trF5_td = trF5.getElementsByTagName('td');

			var trF6 = document.getElementById('F_durationPrice');
			for(var k = 0; k < dailyRent.length; k++) {
				var tdObj = document.createElement('td');
				trF6.appendChild(tdObj);
			}
			var trF6_td = trF6.getElementsByTagName('td');

			var trF7 = document.getElementById('F_mileagePrice');
			for(var k = 0; k < dailyRent.length; k++) {
				var tdObj = document.createElement('td');
				trF7.appendChild(tdObj);
			}
			var trF7_td = trF7.getElementsByTagName('td');

			//立即
			if(immediately.length > 0) {
				//车型
				for(var j = 1; j < tr_td.length; j++) {
					tr_td[j].innerHTML = immediately[j - 1].vehicvarype.vehicvarypeName;
				}
				//平台服务费
				var count1 = 0;
				var k = 0;
				for(var j = 1; j < tr1_td.length; j++) {
					tr1_td[j].innerHTML = '￥' + immediately[j - 1].platformServicePrice + '/单';
					if(immediately[j - 1].platformServicePrice == 0) {
						count1++;
					}

				}
				k = j - 1;
				if(count1 == k) {
					$(tr1).hide();
				}

				//最低消费
				var count2 = 0;
				var k2 = 0;
				for(var j = 1; j < tr2_td.length; j++) {
					tr2_td[j].innerHTML = '￥' + immediately[j - 1].minimumConsumerPrice + '/单';
					if(immediately[j - 1].minimumConsumerPrice == 0) {
						count2++;
					}
				}
				k2 = j - 1;
				if(count2 == k2) {
					$(tr2).hide();
				}

				//固定费
				for(var j = 1; j < tr3_td.length; j++) {
					tr3_td[j].innerHTML = '￥' + immediately[j - 1].fixedPrice + '(' + '包含' + immediately[j - 1].fixedPriceIncludeDuration + '分钟' + immediately[j - 1].fixedPriceIncludeMileage + '公里' + ')';
				}

				// 远途费
				for(var j = 1; j < tr4_td.length; j++) {
					tr4_td[j].innerHTML = '￥' + immediately[j - 1].longDistancePrice + '/公里' + '(超出' + immediately[j - 1].longDistanceStartMileage + '公里)';
				}
				//时长费
				for(var j = 1; j < tr5_td.length; j++) {
					tr5_td[j].innerHTML = '￥' + immediately[j - 1].durationPrice + '/分钟';
				}
				//里程费
				for(var j = 1; j < tr6_td.length; j++) {
					tr6_td[j].innerHTML = '￥' + immediately[j - 1].mileagePrice + '/公里';
				}
			}

			//预约
			if(appointment.length > 0) {
				//车型
				for(var j = 1; j < tr7_td.length; j++) {
					tr7_td[j].innerHTML = appointment[j - 1].vehicvarype.vehicvarypeName;
				}
				//平台服务费
				var count8 = 0;
				var k8 = 0;
				for(var j = 1; j < tr8_td.length; j++) {
					tr8_td[j].innerHTML = '￥' + appointment[j - 1].platformServicePrice + '/单';
					if(appointment[j - 1].platformServicePrice == 0) {
						count8++;
					}
				}
				k8 = j - 1;
				if(count8 == k8) {
					$(tr8).hide();
				}

				//最低消费
				var count9 = 0;
				var k9 = 0;
				for(var j = 1; j < tr9_td.length; j++) {
					tr9_td[j].innerHTML = '￥' + appointment[j - 1].minimumConsumerPrice + '/单';
					if(appointment[j - 1].minimumConsumerPrice == 0) {
						count9++
					}
				}
				k9 = j - 1;
				if(count9 == k9) {
					$(tr9).hide();
				}

				//固定费
				for(var j = 1; j < tr10_td.length; j++) {
					tr10_td[j].innerHTML = '￥' + appointment[j - 1].fixedPrice + '(' + '包含' + appointment[j - 1].fixedPriceIncludeDuration + '分钟' + appointment[j - 1].fixedPriceIncludeMileage + '公里' + ')';
				}
				// 远途费
				for(var j = 1; j < tr11_td.length; j++) {
					tr11_td[j].innerHTML = '￥' + appointment[j - 1].longDistancePrice + '/公里' + '(超出' + appointment[j - 1].longDistanceStartMileage + '公里)';
				}
				//时长费
				for(var j = 1; j < tr12_td.length; j++) {
					tr12_td[j].innerHTML = '￥' + appointment[j - 1].durationPrice + '/分钟';
				}
				//里程费
				for(var j = 1; j < tr13_td.length; j++) {
					tr13_td[j].innerHTML = '￥' + appointment[j - 1].mileagePrice + '/公里';
				}
			}

			//接机
			if(starAircraft.length > 0) {
				//车型
				for(var j = 1; j < trC1_td.length; j++) {
					trC1_td[j].innerHTML = starAircraft[j - 1].vehicvarype.vehicvarypeName;
				}
				//平台服务费
				var countC2 = 0;
				var kC2 = 0;
				for(var j = 1; j < trC2_td.length; j++) {
					trC2_td[j].innerHTML = '￥' + starAircraft[j - 1].platformServicePrice + '/单';
					if(starAircraft[j - 1].platformServicePrice == 0) {
						countC2++;
					}
				}
				kC2 = j - 1;
				if(countC2 == kC2) {
					$(trC2).hide();
				}

				//最低消费
				var countC3 = 0;
				var kC3 = 0;
				for(var j = 1; j < trC3_td.length; j++) {
					trC3_td[j].innerHTML = '￥' + starAircraft[j - 1].minimumConsumerPrice + '/单';
					if(starAircraft[j - 1].minimumConsumerPrice == 0) {
						countC3++;
					}
				}
				kC3 = j - 1;
				if(countC3 == kC3) {
					$(trC3).hide();
				}

				//固定费
				for(var j = 1; j < trC4_td.length; j++) {
					trC4_td[j].innerHTML = '￥' + starAircraft[j - 1].fixedPrice + '(' + '包含' + starAircraft[j - 1].fixedPriceIncludeDuration + '分钟' + starAircraft[j - 1].fixedPriceIncludeMileage + '公里' + ')';
				}
				// 远途费
				for(var j = 1; j < trC5_td.length; j++) {
					trC5_td[j].innerHTML = '￥' + starAircraft[j - 1].longDistancePrice + '/公里' + '(超出' + starAircraft[j - 1].longDistanceStartMileage + '公里)';
				}
				//时长费
				for(var j = 1; j < trC6_td.length; j++) {
					trC6_td[j].innerHTML = '￥' + starAircraft[j - 1].durationPrice + '/分钟';
				}
				//里程费
				for(var j = 1; j < trC7_td.length; j++) {
					trC7_td[j].innerHTML = '￥' + starAircraft[j - 1].mileagePrice + '/公里';
				}
			}

			//送机
			if(endAircraft.length > 0) {
				//车型
				for(var j = 1; j < trD1_td.length; j++) {
					trD1_td[j].innerHTML = endAircraft[j - 1].vehicvarype.vehicvarypeName;
				}
				//平台服务费
				var countD2 = 0;
				var kD2 = 0;
				for(var j = 1; j < trD2_td.length; j++) {
					trD2_td[j].innerHTML = '￥' + endAircraft[j - 1].platformServicePrice + '/单';
					if(endAircraft[j - 1].platformServicePrice == 0) {
						countD2++;
					}
				}
				kD2 = j - 1;
				if(countD2 == kD2) {
					$(trD2).hide();
				}

				//最低消费
				var countD3 = 0;
				var kD3 = 0;
				for(var j = 1; j < trD3_td.length; j++) {
					trD3_td[j].innerHTML = '￥' + endAircraft[j - 1].minimumConsumerPrice + '/单';
					if(endAircraft[j - 1].minimumConsumerPrice == 0) {
						countD3++;
					}
				}
				kD3 = j - 1;
				if(countD3 == kD3) {
					$(trD3).hide();
				}
				//固定费
				for(var j = 1; j < trD4_td.length; j++) {
					trD4_td[j].innerHTML = '￥' + endAircraft[j - 1].fixedPrice + '(' + '包含' + endAircraft[j - 1].fixedPriceIncludeDuration + '分钟' + endAircraft[j - 1].fixedPriceIncludeMileage + '公里' + ')';
				}
				// 远途费
				for(var j = 1; j < trD5_td.length; j++) {
					trD5_td[j].innerHTML = '￥' + endAircraft[j - 1].longDistancePrice + '/公里' + '(超出' + endAircraft[j - 1].longDistanceStartMileage + '公里)';
				}
				//时长费
				for(var j = 1; j < trD6_td.length; j++) {
					trD6_td[j].innerHTML = '￥' + endAircraft[j - 1].durationPrice + '/分钟';
				}
				//里程费
				for(var j = 1; j < trD7_td.length; j++) {
					trD7_td[j].innerHTML = '￥' + endAircraft[j - 1].mileagePrice + '/公里';
				}
			}

			//半日租
			if(halfDayRent.length > 0) {
				//车型
				for(var j = 1; j < trE1_td.length; j++) {
					trE1_td[j].innerHTML = halfDayRent[j - 1].vehicvarype.vehicvarypeName;
				}
				//平台服务费
				var countE2 = 0;
				var kE2 = 0;
				for(var j = 1; j < trE2_td.length; j++) {
					trE2_td[j].innerHTML = '￥' + halfDayRent[j - 1].platformServicePrice + '/单';
					if(halfDayRent[j - 1].platformServicePrice == 0) {
						countE2++;
					}
				}
				kE2 = j - 1;
				if(countE2 == kE2) {
					$(trE2).hide();
				}
				//最低消费
				var countE3 = 0;
				var kE3 = 0;
				for(var j = 1; j < trE3_td.length; j++) {
					trE3_td[j].innerHTML = '￥' + halfDayRent[j - 1].minimumConsumerPrice + '/单';
					if(halfDayRent[j - 1].minimumConsumerPrice == 0) {
						countE3++;
					}
				}
				kE3 = j - 1;
				if(countE3 == kE3) {
					$(trE3).hide();
				}
				//固定费
				for(var j = 1; j < trE4_td.length; j++) {
					trE4_td[j].innerHTML = '￥' + halfDayRent[j - 1].fixedPrice + '(' + '包含' + halfDayRent[j - 1].fixedPriceIncludeDuration + '分钟' + halfDayRent[j - 1].fixedPriceIncludeMileage + '公里' + ')';
				}
				// 远途费
				for(var j = 1; j < trE5_td.length; j++) {
					trE5_td[j].innerHTML = '￥' + halfDayRent[j - 1].longDistancePrice + '/公里' + '(超出' + halfDayRent[j - 1].longDistanceStartMileage + '公里)';
				}
				//时长费
				for(var j = 1; j < trE6_td.length; j++) {
					trE6_td[j].innerHTML = '￥' + halfDayRent[j - 1].durationPrice + '/分钟';
				}
				//里程费
				for(var j = 1; j < trE7_td.length; j++) {
					trE7_td[j].innerHTML = '￥' + halfDayRent[j - 1].mileagePrice + '/公里';
				}
			}

			//日租
			if(dailyRent.length > 0) {
				//车型
				for(var j = 1; j < trF1_td.length; j++) {
					trF1_td[j].innerHTML = dailyRent[j - 1].vehicvarype.vehicvarypeName;
				}
				//平台服务费
				var countF2 = 0;
				var kF2 = 0;
				for(var j = 1; j < trF2_td.length; j++) {
					trF2_td[j].innerHTML = '￥' + dailyRent[j - 1].platformServicePrice + '/单';
					if(dailyRent[j - 1].platformServicePrice == 0) {
						countF2++;
					}
				}
				kF2 = j - 1;
				if(countF2 == kF2) {
					$(trF2).hide();
				}
				//最低消费
				var countF3 = 0;
				var kF3 = 0;
				for(var j = 1; j < trF3_td.length; j++) {
					trF3_td[j].innerHTML = '￥' + dailyRent[j - 1].minimumConsumerPrice + '/单';
					if(dailyRent[j - 1].minimumConsumerPrice == 0) {
						countF3++;
					}
				}
				kF3 = j - 1;
				if(countF3 == kF3) {
					$(trF3).hide();
				}
				//固定费
				for(var j = 1; j < trF4_td.length; j++) {
					trF4_td[j].innerHTML = '￥' + dailyRent[j - 1].fixedPrice + '(' + '包含' + dailyRent[j - 1].fixedPriceIncludeDuration + '分钟' + dailyRent[j - 1].fixedPriceIncludeMileage + '公里' + ')';
				}
				// 远途费
				for(var j = 1; j < trF5_td.length; j++) {
					trF5_td[j].innerHTML = '￥' + dailyRent[j - 1].longDistancePrice + '/公里' + '(超出' + dailyRent[j - 1].longDistanceStartMileage + '公里)';
				}
				//时长费
				for(var j = 1; j < trF6_td.length; j++) {
					trF6_td[j].innerHTML = '￥' + dailyRent[j - 1].durationPrice + '/分钟';
				}
				//里程费
				for(var j = 1; j < trF7_td.length; j++) {
					trF7_td[j].innerHTML = '￥' + dailyRent[j - 1].mileagePrice + '/公里';
				}
			}
			$('.container').show();
		},
		error: function() {
			$('.container').hide();
			$('.friendly').show();
			return;
		}
	});
}

function waitingFee(orderBusinessTypeChargeRuleId) {
	$.ajax({
		type: "POST",
		url: service + '/orderBusinessTypeChargeRule/semiLogin/orderWaitDurationChargeRules',
		data: {
			orderBusinessTypeChargeRuleId: orderBusinessTypeChargeRuleId
		},
		success: function(res) {
			console.info(res)
		}
	});
}

function clickTable() {
	$('#wait').toggle();
	$('#wait1').toggle();
}

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null)
		return unescape(r[2]);
	return null;
}

Date.prototype.Format = function(fmt) {
	fmt = fmt || 'yyyy-MM-dd hh:mm:ss';
	var o = {
		"M+": this.getMonth() + 1, // 月份
		"d+": this.getDate(), // 日
		"h+": this.getHours(), // 小时
		"m+": this.getMinutes(), // 分
		"s+": this.getSeconds(), // 秒
		"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
		"S": this.getMilliseconds()
		// 毫秒
	};
	if(/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
			.substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) :
				(("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

window.onload = function() {
	chargeRule();
}
